package paperpass;

import java.io.*;

public class IOUtils {

    // 读取文件内容并返回字符串
    public static String readTxt(String txtPath) {
        StringBuilder str = new StringBuilder();
        String strLine;
        // 将 txt文件按行读入 str中
        File file = new File(txtPath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            // 字符串拼接
            while ((strLine = bufferedReader.readLine()) != null) {
                str.append(strLine);
            }
        } catch (IOException e) {
            // 打印异常信息
            e.printStackTrace();
        }
        return str.toString();
    }

    // 将相似度写入文件
    public static void writeTxt(double sim, String txtPath) {
        File file = new File(txtPath);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(Double.toString(sim));
            fileWriter.write(System.lineSeparator()); // 使用系统默认的换行符
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}