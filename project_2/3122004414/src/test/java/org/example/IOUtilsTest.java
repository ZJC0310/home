package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsTest {

    private static final String OUTPUT_FILE_PATH = "C:\\Users\\12892\\Desktop\\java\\papertest\\test_output.txt";

    @BeforeEach
    void setUp() {
        // 清空测试文件内容
        IOUtils.clearFile(OUTPUT_FILE_PATH);
    }

    @Test
    void testReadTxt() {
        String txtPath = "C:\\Users\\12892\\Desktop\\java\\papertest\\orig.txt";
        String content = IOUtils.readTxt(txtPath);
        assertNotNull(content);  // 验证内容不为空
        assertFalse(content.isEmpty());  // 验证内容非空字符串
    }

    @Test
    void testWriteTxt() {
        double similarity = 0.85;
        // 使用 writeTxt(String, String) 方法
        IOUtils.writeTxt(Double.toString(similarity), OUTPUT_FILE_PATH);

        // 读取文件内容进行验证
        String content = IOUtils.readTxt(OUTPUT_FILE_PATH);
        assertEquals("0.85", content.trim());  // 验证内容是否正确
    }
}
