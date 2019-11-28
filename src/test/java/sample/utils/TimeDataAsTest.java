package sample.utils;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTimeConstants;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/28 14:05
 */
public class TimeDataAsTest {
    private static final Logger logger = LoggerFactory.getLogger(TimeDataAsTest.class);

    @Test
    public void test() {
        String strTime = "[*][*][*][00:01-01:00]";
        ITimeData data;


        data = new TimeDataAs(strTime);
        testTime(data, "2019-09-07 19:04:59");
        testTime(data, "2019-09-07 19:05:00");
        testTime(data, "2019-09-07 19:05:01");
        testTimeData(data);


    }

    private void testTimeData(ITimeData data) {
        long baseTime = TimeUtils.FORMATTER2.parseMillis("2019-09-07 00:00:00");

        for (int i = 0; i < 100; i++) {
            long ctime = baseTime + (RandomUtils.nextInt(240) - 120) * DateTimeConstants.MILLIS_PER_SECOND;

            DurationTime before = data.getBeforeTime(ctime);
            DurationTime next = data.getNextTime(ctime);

            String baseTimeStr = TimeUtils.printTime2(ctime);
            logger.debug("testTimeData baseTimeStr:{}", baseTimeStr);
            logger.debug("testTimeData before:     {}", before);
            logger.debug("testTimeData next:       {}", next);
            logger.debug("testTimeData ================");
        }

    }

    private void testTime(ITimeData data, String baseTime) {
        long ctime = TimeUtils.FORMATTER2.parseMillis(baseTime);

        DurationTime next = data.getNextTime(ctime);

        DurationTime before = data.getBeforeTime(ctime);

        System.out.println("====================");
    }

}