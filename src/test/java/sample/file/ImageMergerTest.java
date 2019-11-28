package sample.file;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/28 10:39
 */
public class ImageMergerTest {
    private static final Logger logger = LoggerFactory.getLogger(ImageMergerTest.class);

    @Test
    public void test() throws Exception {
        long l = System.currentTimeMillis();
        ImageMerger tt = new ImageMerger();
        String baseUrl = "D:\\Desktop\\testImage\\";
        BufferedImage a = tt.loadImageLocal(baseUrl + "a.jpg");
        BufferedImage b = tt.loadImageLocal(baseUrl + "d.png");
        //        BufferedImage b = ImageIO.read(new URL("https://img-blog.csdnimg.cn/20190927151101105.png"));
        tt.writeImageLocal(baseUrl + "c.jpg", tt.modifyImagetogeter(a, b));
        //将多张图片合在一起
        System.out.println("success");

        logger.info("test currentTimeMillis:{}", System.currentTimeMillis() - l);
    }
}