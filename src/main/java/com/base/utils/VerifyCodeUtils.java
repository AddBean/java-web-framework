package com.base.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/27.
 */
public class VerifyCodeUtils {
    // 验证码图片的宽度。
    public static int width = 60;
    // 验证码图片的高度。
    public static int height = 20;
    // 验证码字符个数
    public static int codeCount = 4;
    public static int x = 20;
    // 字体高度
    public static int fontHeight;
    public static int codeY=0;
    public static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static class VerifyCode {
        public String code;
        public BufferedImage buffImg;

        public VerifyCode(String code, BufferedImage buffImg) {
            this.code = code;
            this.buffImg = buffImg;
        }

    }

    public static VerifyCode getVerifyCodeImage(int width, int height, int fontHeight, int codeCount) {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);// 设置字体。
        g.setColor(Color.BLACK);// 画边框。
        g.drawRect(0, 0, width
                - 1, height - 1);
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);// 得到随机产生的验证码数字。
            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(strRand, i*width/codeCount, height);
            randomCode.append(strRand);// 将产生的四个随机数组合在一起。
        }
        // 将四位数字的验证码保存到Session中。
        return new VerifyCode(randomCode.toString(),buffImg);
    }
}
