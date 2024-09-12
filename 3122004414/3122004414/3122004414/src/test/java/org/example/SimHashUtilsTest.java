package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimHashUtilsTest {

    @Test
    void testGetSimHash() {
        // 测试字符串
        String text1 = "这是一个测试文本";
        String text2 = "这是另一个测试文本";

        // 计算 SimHash 值
        String simHash1 = SimHash.getSimHash(text1);
        String simHash2 = SimHash.getSimHash(text2);

        // 验证 SimHash 值不为空
        assertNotNull(simHash1);
        assertNotNull(simHash2);

        // 验证两个不同文本的 SimHash 值不相等
        assertNotEquals(simHash1, simHash2);
    }

    @Test
    void testGetHammingDistance() {
        // 测试字符串
        String text1 = "这是一个测试文本";
        String text2 = "这是另一个测试文本";

        // 计算 SimHash 值
        String simHash1 = SimHash.getSimHash(text1);
        String simHash2 = SimHash.getSimHash(text2);

        // 计算 Hamming 距离
        int hammingDistance = HammingUtils.getHammingDistance(simHash1, simHash2);

        // 验证 Hamming 距离为正整数
        assertTrue(hammingDistance > 0);

        // 验证 Hamming 距离在合理范围内（假设小于128）
        assertTrue(hammingDistance >= 0 && hammingDistance <= 128);
    }

    @Test
    void testSimilarityCalculation() {
        // 测试字符串
        String text1 = "这是一个测试文本";
        String text2 = "这是另一个测试文本";

        // 计算 SimHash 值
        String simHash1 = SimHash.getSimHash(text1);
        String simHash2 = SimHash.getSimHash(text2);

        // 计算 Hamming 距离
        int hammingDistance = HammingUtils.getHammingDistance(simHash1, simHash2);

        // 计算相似度
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 验证相似度在合理范围内（0到1之间）
        assertTrue(similarity >= 0.0 && similarity <= 1.0);

        // 验证相似度不是1（因为文本不同）
        assertNotEquals(1.0, similarity);
    }

    @Test
    void testIdenticalTexts() {
        // 测试相同的文本
        String text = "这是一个测试文本";

        // 计算 SimHash 值
        String simHash1 = SimHash.getSimHash(text);
        String simHash2 = SimHash.getSimHash(text);

        // 计算 Hamming 距离和相似度
        int hammingDistance = HammingUtils.getHammingDistance(simHash1, simHash2);
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 验证 Hamming 距离为0，相似度为1
        assertEquals(0, hammingDistance);
        assertEquals(1.0, similarity);
    }

    @Test
    void testCompletelyDifferentTexts() {
        // 测试完全不同的文本
        String text1 = "这是一个测试文本";
        String text2 = "这是完全不同的文本内容";

        // 计算 SimHash 值
        String simHash1 = SimHash.getSimHash(text1);
        String simHash2 = SimHash.getSimHash(text2);

        // 计算 Hamming 距离和相似度
        int hammingDistance = HammingUtils.getHammingDistance(simHash1, simHash2);
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 验证 Hamming 距离大于0，相似度小于1
        assertTrue(hammingDistance > 0);
        assertTrue(similarity < 1.0);
    }
}
