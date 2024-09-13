package org.example;

public class Main {
    public static void main(String[] args) {
        // 检查命令行参数数量
        if (args.length < 3) {
            System.out.println("请提供以下三个参数：\n1. 论文原文的文件路径\n2. 抄袭版论文的文件路径\n3. 输出结果文件的路径");
            return;
        }

        // 从命令行参数中获取文件路径
        String origTxtPath = args[0];  // 论文原文的文件路径
        String plagiarizedTxtPath = args[1];  // 抄袭版论文的文件路径
        String outputTxtPath = args[2];  // 输出结果文件的路径


        // 读取文件内容
        String originalText = IOUtils.readTxt(origTxtPath);
        String plagiarizedText = IOUtils.readTxt(plagiarizedTxtPath);

        // 生成 SimHash 值
        String originalSimHash = SimHash.getSimHash(originalText);
        String plagiarizedSimHash = SimHash.getSimHash(plagiarizedText);

        // 计算海明距离
        int hammingDistance = HammingUtils.getHammingDistance(originalSimHash, plagiarizedSimHash);

        // 计算相似度
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 输出相似度到文件
        IOUtils.writeTxt(String.format("相似度: %.2f", similarity), outputTxtPath);

        // 控制台输出相似度
        System.out.printf("相似度: %.2f%n", similarity);
    }
}
