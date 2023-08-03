package com.ghj.itextpdf.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author guohuanjun1
 * @date 2023/7/24 15:44
 */

public class FileUtil {
    public static void startModify(String path, String target, String replacement) {
        File file = new File(path);
        if (file.exists()) {
            modifyFileContent(file, target, replacement);
        } else {
            System.out.println("文件 {} 不存在");
        }
    }
    /**
     * 修改文件中的内容
     * @param file 文件
     * @param target 被替换的字符串
     * @param replacement 替换字符串
     */
    public static void modifyFileContent(File file, String target, String replacement) {
        System.out.println("开始修改文件：{}, 替换 {} 为 {}");
        StringBuilder sb = new StringBuilder();
        //记录修改的行数
        int cnt = 0;
        //记录替换所在的行
        int rowLine = 0;
        //换行符
        String enter = System.getProperty("line.separator");

        //printWriter原本也想放在 try-with 中，少写点代码，
        //但是一个文件不能同时读写，pw 和 br 对同一个文件操作的结果时，文件的内容被清空！！！
        //不妨试下，将 pw 申明在 try-with 中，看下运行结果。
        PrintWriter pw = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            for (line = br.readLine(); line != null; line = br.readLine()) {
                rowLine++;
                if (line.contains(target)) {
                    line = line.replace(target, replacement);
                    System.out.println("替换所在行：{}");
                    cnt++;
                }
                //数据暂存在 StringBuilder 中
                if (rowLine == 1) {
                    sb.append(line);
                } else {
                    sb.append(enter).append(line);
                }
            }
            pw = new PrintWriter(new FileWriter(file));
            pw.print(sb);
        } catch (FileNotFoundException e) {
            System.exit(1);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        System.out.println("修改文件：{} 结束,一共替换" + cnt + "行数据");
    }
}
