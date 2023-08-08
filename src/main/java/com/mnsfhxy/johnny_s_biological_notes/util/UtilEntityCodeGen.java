package com.mnsfhxy.johnny_s_biological_notes.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilEntityCodeGen {
    static String name = "Jelly";  // 这里先写死一个示例名称

    // 指定包名和路径
    static String packageName = "com.mnsfhxy.johnny_s_biological_notes.entity." + name.toLowerCase();
   static String packagePath = "I:\\CODE\\MINECRAFT\\johnny-s-biological-notes\\src\\main\\java\\com\\mnsfhxy\\johnny_s_biological_notes\\entity\\"+name.toLowerCase();

    public static void main(String[] args) {
        // 输入生物名字


        // 创建包目录
        File packageDir = new File(packagePath);
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }

        // 创建四个类文件
        String entityClassName = "Entity" + name;
        String animationClassName = "Animation" + name;
        String rendererClassName = "Renderer" + name;
        String modelClassName = "Model" + name;
        try {
            createJavaClassFile(packageName, entityClassName);
            createJavaClassFile(packageName, animationClassName);
            createJavaClassFile(packageName, rendererClassName);
            createJavaClassFile(packageName, modelClassName);
            System.out.println("Java 包创建成功！");
        } catch (IOException e) {
            System.err.println("创建 Java 类文件失败：" + e.getMessage());
        }
    }

    /**
     * 创建 Java 类文件
     *
     * @param packageName 包名
     * @param className   类名
     * @throws IOException
     */
    private static void createJavaClassFile(String packageName, String className) throws IOException {
        String filePath = packagePath  + File.separator + className + ".java";
        File file = new File(filePath);
        if (file.exists()) {
            System.err.println(className + " 类文件已存在，跳过创建！");
            return;
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("package " + packageName + ";\n\n");
        fileWriter.write("public class " + className + " {\n\n}");
        fileWriter.flush();
        fileWriter.close();
    }
}
