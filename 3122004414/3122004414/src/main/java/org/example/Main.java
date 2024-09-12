package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 主文件路径
        String origTxtPath = "C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig.txt";

        // 需要比较的多个文件路径
        List<String> comparisonFiles = new ArrayList<>();
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig_0.8_add.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig_0.8_del.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig_0.8_dis_1.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig_0.8_dis_10.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\orig_0.8_dis_15.txt");

        // 输出文件路径
        String outputTxtPath = "C:\\Users\\12892\\Desktop\\java\\zjc\\src\\main\\resources\\output.txt";

        // 读取主文件内容
        String mainContent = IOUtils.readTxt(origTxtPath);

        // 生成主文件的SimHash值
        String mainSimHash = SimHash.getSimHash(mainContent);

        // 清空输出文件
        IOUtils.clearFile(outputTxtPath);

        // 对比每个文件并输出相似度
        for (String filePath : comparisonFiles) {
            String content = IOUtils.readTxt(filePath);
            String simHash = SimHash.getSimHash(content);
            int hammingDistance = HammingUtils.getHammingDistance(mainSimHash, simHash);
            double similarity = HammingUtils.getSimilarity(hammingDistance);

            // 输出相似度到文件和屏幕
            String result = "文件: " + filePath + " 相似度: " + similarity;
            IOUtils.writeTxt(result, outputTxtPath);
            System.out.println(result);
        }
    }
}
