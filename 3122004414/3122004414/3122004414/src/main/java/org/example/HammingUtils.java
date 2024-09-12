package org.example;

public class HammingUtils {

    /**
     * 计算两串SimHash值之间的海明距离（即不同位的个数）。
     * 海明距离表示两个二进制字符串中不同位的数量，数值越小相似度越高。
     *
     * @param simHash1 第一个SimHash值
     * @param simHash2 第二个SimHash值
     * @return 两个SimHash值之间的海明距离
     * @throws IllegalArgumentException 如果两个SimHash长度不一致，则抛出异常
     */
    public static int getHammingDistance(String simHash1, String simHash2) {
        // 检查两个SimHash值的长度是否一致
        if (simHash1.length() != simHash2.length()) {
            throw new IllegalArgumentException("SimHash lengths do not match.");
        }

        int distance = 0;
        // 遍历每个字符，比较对应位置的位是否相同
        for (int i = 0; i < simHash1.length(); i++) {
            // 如果当前位置的字符不同，海明距离加1
            if (simHash1.charAt(i) != simHash2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * 根据海明距离计算两个SimHash值的相似度。相似度在0到1之间，越接近1表示越相似。
     * 通过公式计算相似度，并将结果四舍五入保留两位小数。
     *
     * @param distance 海明距离
     * @return 相似度值，范围为0.00到1.00之间
     */
    public static double getSimilarity(int distance) {
        // 通过海明距离计算相似度
        double similarity = 1.0 - (double) distance / 128;
        // 将相似度四舍五入保留两位小数
        return Math.round(similarity * 100.0) / 100.0;
    }
}
