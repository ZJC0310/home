package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HammingUtilsTest {

    @Test
    void testGetHammingDistance() {
        // 测试的 SimHash 值
        String simHash1 = "11010101010101010101010101010101";
        String simHash2 = "11010101010101010101010101010001";  // 只有最后一位不同

        // 计算 Hamming 距离
        int distance = HammingUtils.getHammingDistance(simHash1, simHash2);

        // 验证 Hamming 距离是否为1（因为只有1位不同）
        assertEquals(1, distance);
    }

    @Test
    void testGetSimilarity() {
        // 测试的 Hamming 距离
        int hammingDistance = 1;

        // 计算相似度
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 验证相似度是否与预期值相符
        // 128 是默认的哈希值长度，因此计算公式为 0.01 * (100 - 1 * 100 / 128)
        assertEquals(0.99, similarity, 0.00001);  // 允许有微小的浮点误差
    }
}
