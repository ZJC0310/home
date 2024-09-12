package paperpass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    // 读取文件内容并返回字符串
    public static String readTxt(String txtPath) {
        StringBuilder str = new StringBuilder();
        String strLine;
        File file = new File(txtPath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((strLine = bufferedReader.readLine()) != null) {
                str.append(strLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    // 将结果写入文件
    public static void writeTxt(String content, String txtPath) {
        File file = new File(txtPath);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);
            fileWriter.write(System.lineSeparator()); // 使用系统默认的换行符
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 清空文件内容
    public static void clearFile(String txtPath) {
        try (FileWriter fileWriter = new FileWriter(txtPath)) {
            fileWriter.write(""); // 写入空字符串来清空文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取文件夹中所有文件的路径
    public static List<String> getFilePaths(String folderPath) {
        List<String> filePaths = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

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
