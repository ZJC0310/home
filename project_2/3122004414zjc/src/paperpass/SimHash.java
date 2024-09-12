package paperpass;

import org.ansj.splitWord.analysis.BaseAnalysis;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class SimHash {

    // 用MD5获取hash值
    public static String getHash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new BigInteger(1, messageDigest.digest(str.getBytes(StandardCharsets.UTF_8))).toString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 传入字符串，计算它的SimHash值，以字符串形式输出
     * @param str 传入String类型
     * @return 返回str的SimHash值
     */
    public static String getSimHash(String str) {
        // 用数组表示特征向量, 取128位, 从 0 1 2 位开始表示从高位到低位
        int[] v = new int[128];

        // 分词（使用了开源中文分词器Ansj）
        String analysisedText = BaseAnalysis.parse(str).toStringWithOutNature();
        String[] wordsArr = analysisedText.split(",");
        List<String> keywordList = Arrays.asList(wordsArr);
        int size = keywordList.size();

        // 获取各个关键字对应的hash值，此时返回的hash值是一串二进制字符串
        int i = 0;
        for (String keyword : keywordList) {
            StringBuilder hash = new StringBuilder(getHash(keyword));
            // hash值可能少于128位，在低位以0补齐
            hash = new StringBuilder(String.format("%128s", hash).replace(' ', '0'));

            // 加权、合并
            for (int j = 0; j < v.length; j++) {
                int weight = 10 - (i / Math.max(1, size / 10));

                if (hash.charAt(j) == '1') {
                    v[j] += weight;
                } else {
                    v[j] -= weight;
                }
            }
            i++;
        }

        // 降维
        StringBuilder simHash = new StringBuilder();
        for (int j = 0; j < v.length; j++) {
            if (v[j] > 0) {
                simHash.append("1");
            } else {
                simHash.append("0");
            }
        }
        return simHash.toString();
    }
}
