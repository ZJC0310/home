package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    /**
     * 读取文本文件内容并将其转换为字符串返回。
     *
     * @param txtPath 文件路径
     * @return 文件内容的字符串表示
     */
    public static String readTxt(String txtPath) {
        StringBuilder str = new StringBuilder();
        String strLine;
        File file = new File(txtPath);
        // 使用带缓冲的输入流读取文件内容
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            // 按行读取文件内容并拼接为字符串
            while ((strLine = bufferedReader.readLine()) != null) {
                str.append(strLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 将字符串内容写入指定的文件中。文件不存在时会自动创建，内容追加到文件末尾。
     *
     * @param content 要写入的字符串内容
     * @param txtPath 文件路径
     */
    public static void writeTxt(String content, String txtPath) {
        File file = new File(txtPath);
        // 使用FileWriter写入字符串到文件，追加模式开启
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);
            fileWriter.write(System.lineSeparator()); // 添加系统默认的换行符
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将double类型的值写入指定的文件中。
     * 通过调用writeTxt方法，将double转换为字符串后进行写入。
     *
     * @param value 要写入的double值
     * @param txtPath 文件路径
     */
    public static void writeTxt(double value, String txtPath) {
        writeTxt(Double.toString(value), txtPath); // 调用上面的方法，将double转换为String
    }

    /**
     * 清空指定文件的内容。不会删除文件，只是将文件内容置空。
     *
     * @param txtPath 文件路径
     */
    public static void clearFile(String txtPath) {
        // 覆盖写入空字符串来清空文件
        try (FileWriter fileWriter = new FileWriter(txtPath)) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定文件夹中所有文件的绝对路径，并返回路径列表。
     *
     * @param folderPath 文件夹路径
     * @return 文件路径的列表
     */
    public static List<String> getFilePaths(String folderPath) {
        List<String> filePaths = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        // 遍历文件夹中的文件，并将每个文件的绝对路径添加到列表中
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    filePaths.add(file.getAbsolutePath());
                }
            }
        }
        return filePaths;
    }
}
