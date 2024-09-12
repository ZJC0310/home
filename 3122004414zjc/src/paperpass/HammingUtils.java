package paperpass;

public class HammingUtils {

    /**
     * 通过比较差异的位数得到两串文本的差异，称之为“海明距离”
     * @param simHash1
     * @param simHash2
     * @return 海明距离
     */
    public static int getHammingDistance(String simHash1, String simHash2) {
        if (simHash1.length() != simHash2.length()) {
            throw new IllegalArgumentException("SimHash lengths do not match.");
        }

        int distance = 0;
        for (int i = 0; i < simHash1.length(); i++) {
            if (simHash1.charAt(i) != simHash2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    // 通过海明距离计算相似度
    public static double getSimilarity(int distance) {
        return 0.01 * (100 - (double) distance * 100 / 128);
    }
}
