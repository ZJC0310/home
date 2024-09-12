package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MainTest {

    @Test
    void testComparison() {
        // 输入文件路径
        String origTxtPath = "C:\\Users\\12892\\Desktop\\java\\papertest\\orig.txt";

        // 需要比较的多个文件路径
        List<String> comparisonFiles = new ArrayList<>();
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_add.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_del.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_dis_1.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_dis_10.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_dis_15.txt");

        // 输出文件路径
        String outputTxtPath = "C:\\Users\\12892\\Desktop\\java\\papertest\\output.txt";

        // 清空输出文件
        IOUtils.writeTxt(0.0, outputTxtPath); // 清空文件

        // 读取原始文本内容
        String origContent = IOUtils.readTxt(origTxtPath);

        // 进行文件比较
        for (String comparisonFile : comparisonFiles) {
            // 读取比较文件内容
            String comparisonContent = IOUtils.readTxt(comparisonFile);

            // 生成 SimHash 值
            String origSimHash = SimHash.getSimHash(origContent);
            String comparisonSimHash = SimHash.getSimHash(comparisonContent);

            // 计算 Hamming 距离和相似度
            int hammingDistance = HammingUtils.getHammingDistance(origSimHash, comparisonSimHash);
            double similarity = HammingUtils.getSimilarity(hammingDistance);

            // 输出到控制台
            System.out.println("Comparing with: " + comparisonFile);
            System.out.println("SimHash of original text: " + origSimHash);
            System.out.println("SimHash of comparison text: " + comparisonSimHash);
            System.out.println("Hamming Distance: " + hammingDistance);
            System.out.println("Similarity: " + similarity);
            System.out.println();

            // 写入相似度到输出文件
            IOUtils.writeTxt(similarity, outputTxtPath); // 追加到文件
        }
    }
}
