package sample.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/28 14:09
 */
public class TimeDataTest {
    private static final Logger logger = LoggerFactory.getLogger(TimeDataTest.class);

    @Test
    public void test() {
        String strTime = "[*][*][*][00:01-01:00]";
        TimeData data;


        data = TimeData.parse(strTime);
        testTime(data, "2019-09-07 19:04:59");
        testTime(data, "2019-09-07 19:05:00");
        testTime(data, "2019-09-07 19:05:01");
        testTimeData(data);


    }

    private static void testTimeData(TimeData data) {
        //        long baseTime = TimeUtils.FORMATTER2.parseMillis("2019-09-07 00:00:00");
        long baseTime = TimeUtils.FORMATTER2.parseMillis("19-09-07 00:01:53");

        for (int i = 0; i < 1; i++) {
            //            long ctime = baseTime + (RandomUtils.nextInt(240) - 120) * DateTimeConstants.MILLIS_PER_SECOND;
            long ctime = baseTime;
            DurationTime before = data.getRealBeforeTime(ctime);
            DurationTime next = data.getRealNextTime(ctime);

            String baseTimeStr = TimeUtils.printTime2(ctime);
            logger.debug("testTimeData baseTimeStr:{}", baseTimeStr);
            logger.debug("testTimeData before:     {}", before);
            logger.debug("testTimeData next:       {}", next);
            logger.debug("testTimeData ================");
            Date date;

        }

    }

    private static void testTime(ITimeData data, String baseTime) {
        long ctime = TimeUtils.FORMATTER2.parseMillis(baseTime);

        DurationTime next = data.getNextTime(ctime);

        DurationTime before = data.getBeforeTime(ctime);

        System.out.println("====================");
    }
}