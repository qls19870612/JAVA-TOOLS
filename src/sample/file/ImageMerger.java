package sample.file;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 把两张图片合并
 * @version 2018-2-27 上午11:12:09
 *
 */
public class ImageMerger {
    private static final Logger logger = LoggerFactory.getLogger(ImageMerger.class);

    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public BufferedImage modifyImagetogeter(BufferedImage a, BufferedImage b) {

        try {
            int w = b.getWidth();
            int h = b.getHeight();
            Graphics2D g = a.createGraphics();
            g.rotate(0.5);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5F));
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


            int srcW = a.getWidth();
            int srcH = a.getHeight();
            int xInter = srcW / 20;
            int yInter = srcH / 20;
            int offsetX = xInter / 2;
            double maxLen = Math.sqrt(srcW * srcW + srcH * srcH);//对角线长
            int minInter = Math.min(xInter, yInter);
            int segment = (int) (maxLen / (minInter)) + 1;
            int halfOffsetY = segment / 2 * yInter;
            for (int i = 0; i < segment; i++) {

                int offset = (i % 2) * offsetX;
                for (int j = 0; j < segment; j++) {
                    int x = j * xInter + offset;
                    int y = i * yInter - halfOffsetY;
                    g.drawImage(b, x, y, w, h, null);
                }
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return a;
    }

    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        ImageMerger tt = new ImageMerger();
        String baseUrl = "D:\\Desktop\\testImage\\";
        BufferedImage a = tt.loadImageLocal(baseUrl + "e.jpg");
        BufferedImage b = tt.loadImageLocal(baseUrl + "b.png");

        tt.writeImageLocal(baseUrl + "c.jpg", tt.modifyImagetogeter(a, b));
        //将多张图片合在一起
        System.out.println("success");
    }
}