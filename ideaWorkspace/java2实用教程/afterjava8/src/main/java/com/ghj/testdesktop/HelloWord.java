package com.ghj.testdesktop;

import javax.swing.*;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/11/8 10:27
 */
public class HelloWord {
    private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        //设置窗口的位置
        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小
        frame.setSize(1500, 1500);
        // 添加 "Hello World" 标签
        JLabel label = new JLabel("Hello 歇福");
        label.setSize(1000, 1000);
        frame.getContentPane().add(label);
        frame.getContentPane().add(label);
        frame.getContentPane().add(label);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
