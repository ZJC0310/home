package org.example;

import org.ansj.splitWord.analysis.BaseAnalysis;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class SimHash {

    /**
     * 用MD5获取字符串的哈希值，返回一个二进制字符串表示
     * @param str 传入的字符串
     * @return 返回字符串的MD5哈希值（以二进制表示）
     */
    public static String getHash(String str) {
        try {
            // 使用MD5算法生成哈希值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 将字符串转换为字节并生成哈希，再转换为二进制字符串
            return new BigInteger(1, messageDigest.digest(str.getBytes(StandardCharsets.UTF_8))).toString(2);
        } catch (Exception e) {
            // 出现异常时，打印错误并返回原始字符串
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 传入字符串，计算其SimHash值，并返回SimHash的二进制表示
     * @param str 传入的字符串
     * @return 返回计算出的SimHash值（128位的二进制字符串）
     */
    public static String getSimHash(String str) {
        // 创建一个128位的数组，用来表示SimHash的特征向量
        int[] v = new int[128];

        // 对字符串进行分词处理（使用Ansj中文分词工具）
        String analysisedText = BaseAnalysis.parse(str).toStringWithOutNature();  // 分词后去掉词性
        String[] wordsArr = analysisedText.split(",");  // 将分词结果分解为单词数组
        List<String> keywordList = Arrays.asList(wordsArr);  // 将数组转为List
        int size = keywordList.size();  // 获取单词的数量

        // 对每个关键词计算哈希值并进行加权合并
        int i = 0;
        for (String keyword : keywordList) {
            // 生成关键词的MD5哈希值并确保长度为128位
            StringBuilder hash = new StringBuilder(getHash(keyword));
            hash = new StringBuilder(String.format("%128s", hash).replace(' ', '0'));  // 不足128位用0填充

            // 对哈希值进行加权和累加
            for (int j = 0; j < v.length; j++) {
                int weight = 10 - (i / Math.max(1, size / 10));  // 设置加权系数
                // 如果哈希值为1，累加权重；如果为0，减去权重
                if (hash.charAt(j) == '1') {
                    v[j] += weight;
                } else {
                    v[j] -= weight;
                }
            }
            i++;
        }

        // 通过降维将特征向量转换为SimHash二进制字符串
        StringBuilder simHash = new StringBuilder();
        for (int j = 0; j < v.length; j++) {
            // 如果特征值大于0，位置为1；否则为0
            if (v[j] > 0) {
                simHash.append("1");
            } else {
                simHash.append("0");
            }
        }

        // 返回128位的SimHash值
        return simHash.toString();
    }
}
