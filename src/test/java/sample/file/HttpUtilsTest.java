package sample.file;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static sample.file.HttpUtils.FileDown;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/28 14:12
 */
public class HttpUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtilsTest.class);

    @Test
    public void test() {
        //待下载文件地址
        String fileUrl = "http://182.92.228.160:80/otrue-cn/healthmanager/chatfiles/22ecda70-ad43-11e5-9531-d7e3b2ec0d8e";
        String LOCAL_PATH = "E:/ceshi/";
        String type = "jpg";
        Boolean aBoolean = FileDown(LOCAL_PATH, fileUrl, type);
        logger.debug("test aBoolean:{}", aBoolean);
    }
}