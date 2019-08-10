package com.zhanglei;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyFrame extends JFrame {
	// 获取显示器的宽度和高度
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    // 设置程序界面的宽度、高度
    int frameWidth = screenWidth / 2;
    int frameHeight = screenHeight / 2;
    
    // 创建两个按钮（同意、拒绝），设置其宽度、高度、初始位置（当前位置）
    JButton buttonYes = new JButton("同意");
    JButton buttonNo = new JButton("拒绝");
    int buttonWidth = 100;
    int buttonHeight = 50;
    int btnNowX = frameWidth / 3 * 2;
    int btnNowY = frameHeight / 2;
    
    // 创建图片标签、表白文字标签
    ImageIcon icon = new ImageIcon(this.getClass().getResource("/image/赵云01.jpg"));
    JLabel labelImage = new JLabel(icon);
    JLabel labelRequest = new JLabel("小姐姐做我女朋友吧");
    
    // 创建面板的层管理器，设置不同组件显示在不同的图层
    JLayeredPane layeredPane = new JLayeredPane();

    public MyFrame() {
        this.setTitle("I love u");
        this.setSize(frameWidth, frameHeight);
        this.setLocation(screenWidth / 4   , screenHeight / 4);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLayeredPane(layeredPane);

        init();
        buttonManager();
        play();

    }

    /**
     * 初始化程序界面
     */
    public void init() {
        buttonYes.setBounds(frameWidth / 3, frameHeight / 2, buttonWidth, buttonHeight);
        buttonNo.setBounds(btnNowX, btnNowY, buttonWidth, buttonHeight);
        labelRequest.setBounds(frameWidth / 3, frameHeight / 3, 200, 50);
        labelRequest.setFont(new Font("黑体", Font.BOLD, 20));
        labelRequest.setForeground(Color.RED);
        labelImage.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        
        // 图层管理器设置上下图层的位置
        layeredPane.add(labelImage, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonYes, JLayeredPane.MODAL_LAYER);
        layeredPane.add(buttonNo, JLayeredPane.MODAL_LAYER);
        layeredPane.add(labelRequest, JLayeredPane.MODAL_LAYER);
    }
    
    /**
     * 为按钮和关闭窗口添加点击和鼠标滑过事件
     */
    public void buttonManager() {
    	// 点击关闭界面事件
    	this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "关不掉");
                return;
            }
        });

        // 为拒绝按钮添加事件
        buttonNo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            /* 鼠标划过事件 */
            @Override
            public void mouseEntered(MouseEvent e) {
                // 设置拒绝按钮的位置为随机x,y
                double btnRandomX = Math.random() * frameWidth;
                double btnRandomY = Math.random() * frameHeight;

                // 如果拒绝按钮的随机位置不满足要求（1.在之前范围之内；2.超过界面边界），则重新随机
                while (btnRandomX > btnNowX && btnRandomX < btnNowX + buttonWidth
                        && btnRandomY > btnNowY && btnRandomY < btnNowY + buttonHeight
                        || btnRandomX > frameWidth * 0.9 || btnRandomY > frameHeight * 0.9) {
                    btnRandomX = Math.random() * frameWidth;
                    btnRandomY = Math.random() * frameHeight;
                }
                btnNowX = (int)btnRandomX;
                btnNowY = (int)btnRandomY;
                buttonNo.setBounds(btnNowX, btnNowY, buttonWidth, buttonHeight);

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // 为同意按钮添加点击事件
        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "你做了一个明智的选择！");
                System.exit(0);
            }
        });
    }
    
    // 播放音乐
    public void play()  {
    	InputStream input = this.getClass().getResourceAsStream("/music/Kalimba.mp3");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
            Player player = new Player(bufferedInputStream);
            player.play();
        }catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
