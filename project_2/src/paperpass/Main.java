package paperpass;

public class Main {

    public static void main(String[] args) {
        // 输入文件路径
        String txtPath1 = "C:\\Users\\12892\\Desktop\\java\\papertest\\orig.txt";
        String txtPath2 = "C:\\Users\\12892\\Desktop\\java\\papertest\\orig_0.8_add.txt";
        String outputTxtPath = "C:\\Users\\12892\\Desktop\\java\\papertest\\output.txt";

        // 读取文件内容
        String content1 = IOUtils.readTxt(txtPath1);
        String content2 = IOUtils.readTxt(txtPath2);

        // 生成SimHash值
        String simHash1 = SimHash.getSimHash(content1);
        String simHash2 = SimHash.getSimHash(content2);

        // 计算海明距离
        int hammingDistance = HammingUtils.getHammingDistance(simHash1, simHash2);

        // 计算相似度
        double similarity = HammingUtils.getSimilarity(hammingDistance);

        // 输出相似度到文件
        IOUtils.writeTxt(similarity, outputTxtPath);

        // 控制台打印结果
        System.out.println("文本1的SimHash: " + simHash1);
        System.out.println("文本2的SimHash: " + simHash2);
        System.out.println("海明距离: " + hammingDistance);
        System.out.println("相似度: " + similarity);
    }
}
