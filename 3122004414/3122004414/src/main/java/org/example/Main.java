package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 主文件路径 - 需要比较的主文件路径
        String origTxtPath = "C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig.txt";

        // 需要比较的多个文件路径列表
        List<String> comparisonFiles = new ArrayList<>();
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig_0.8_add.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig_0.8_del.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig_0.8_dis_1.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig_0.8_dis_10.txt");
        comparisonFiles.add("C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\orig_0.8_dis_15.txt");

        // 输出文件路径 - 相似度结果将写入此文件
        String outputTxtPath = "C:\\Users\\12892\\Desktop\\java\\3122004414\\src\\main\\resources\\output.txt";

        // 读取主文件的内容
        String mainContent = IOUtils.readTxt(origTxtPath);

        // 生成主文件的SimHash值
        String mainSimHash = SimHash.getSimHash(mainContent);

        // 清空输出文件内容，确保写入的结果不会追加到已有内容之后
        IOUtils.clearFile(outputTxtPath);

        // 遍历所有待比较的文件，计算与主文件的相似度
        for (String filePath : comparisonFiles) {
            // 读取比较文件的内容
            String content = IOUtils.readTxt(filePath);

            // 生成比较文件的SimHash值
            String simHash = SimHash.getSimHash(content);

            // 计算主文件和比较文件的海明距离
            int hammingDistance = HammingUtils.getHammingDistance(mainSimHash, simHash);

            // 根据海明距离计算相似度
            double similarity = HammingUtils.getSimilarity(hammingDistance);

            // 生成相似度结果字符串
            String result = "文件: " + filePath + " 相似度: " + similarity;

            // 将结果写入输出文件
            IOUtils.writeTxt(result, outputTxtPath);

            // 在控制台打印结果
            System.out.println(result);
        }
    }
}
