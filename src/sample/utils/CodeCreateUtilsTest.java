package sample.utils;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.collection.IntArrayList;
import game.sink.util.RandomNumber;
import sample.datas.vo.FieldInfo;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 2018/7/10/010 18:05
 */
public class CodeCreateUtilsTest {

    private boolean mainThreadFlag;
    private static final Logger logger = LoggerFactory.getLogger(CodeCreateUtilsTest.class);

    @Test
    public void test() {
        String src = "@InitReq(cfgKeys = \"q_scene\", desc = \"地图\", max = 1, cfgSplitSep = \";\")";
        Matcher matcher = CodeCreateUtils.paramsPattern.matcher(src);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

    }

    @Test
    public void test1() {
        String src = " public final int[] qCenterY = defaultInt();";
        Pattern fieldPattern = Pattern.compile("(?:\\S+\\s+)+?(\\S+)\\s*=");
        Matcher matcher = fieldPattern.matcher(src);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }

    @Test
    public void test2() {
        String src = "\n" + "\n" + "    @InitReq(cfgKeys = \"q_scene\", desc = \"地图\", max = 2)\n" + "    public final RaceId qScene = defaultObj();";
        Pattern fieldPattern = Pattern.compile("(?:\\S+\\s+)+?(\\S+)\\s*=");
        Matcher matcher = fieldPattern.matcher(src);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

    }

    @Test
    public void test3() {
        String str = "depot_max_size";
        String aft = StringUtils.toUpLowerString(str);
        System.out.println(aft);
    }

    @Test
    public void testFieldClass() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = FieldInfo.class.getConstructors();
        Constructor<?> con = constructors[0];
        Object[] arr = new Object[]{"a", "b", "c"};
        Object field = con.newInstance(arr);
        //        Object field = con.newInstance("a", "b", "c");
        System.out.println("field:" + field);
    }

    @Test
    public void testPow() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i:" + i);
        int a = getClosestPowerOf2(i);
        System.out.println("a:" + a);
        long updateInterval = TimeUnit.SECONDS.toMillis(30);
        System.out.printf("testPow.updateInterval:%s", updateInterval);
        System.out.println();
    }

    public static int getClosestPowerOf2(int x) {
        --x;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        ++x;
        return x;
    }

    @Test
    public void testThread() throws InterruptedException {
        System.out.printf("testThread.1:%s", 1);
        System.out.println();
        mainThreadFlag = true;
        final Thread mainThread = Thread.currentThread();
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("run.3:%s", 3);
                System.out.println();
                callMainThread(1, mainThread);
            }
        }, "sub1");
        subThread.start();
        //        subThread.join();
        System.out.printf("testThread.2:%s", 2);
        System.out.println();

        while (mainThreadFlag) {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        System.out.printf("testThread.4:%s", 4);
        System.out.println();
    }

    private void callMainThread(int i, Thread mainThread) {

        mainThreadFlag = false;
        String name = Thread.currentThread().getName();
        System.out.printf("callMainThread.name:%s", name);
        System.out.println();
    }

    @Test
    public void testArrayList() {
        ArrayList<String> strings1 = new ArrayList<>();
        ArrayList<String> strings2 = new ArrayList<>();
        strings1.add("a");
        strings1.add("b");
        strings1.add("c");
        strings2.add("1");
        strings2.add("2");
        String[] empty = new String[]{"@", "#", "$", "%", "*"};
        String[] aArr = strings1.toArray(empty);
        String[] bArr = strings2.toArray(empty);
        System.out.printf("testArrayList. aArr.length:%s\n", aArr.length);
        System.out.printf("testArrayList.bArr.length:%s\n", bArr.length);

    }

    @Test
    public void testCeil() {
        long totalPrice = 100;
        int discount = 88;
        float v = discount / 100f;
        System.out.printf("testCeil.v:%s\n", v);
        float v1 = totalPrice * v;
        long realPrice = (long) Math.ceil(v1);
        System.out.printf("testCeil.realPrice:%s\n", realPrice);
    }

    @Test
    public void testFloat() {

        System.out.printf("testFloat.Float.MAX_VALUE:%s\n", Float.MAX_VALUE);
        float f = 9999999999999.33302f;
        System.out.printf("testFloat.f:%s\n", f);
        float f1 = 132432.3564f;
        System.out.printf("testFloat.f1:%s\n", f1);
        double d = 1343534434342432.3564d;
        System.out.printf("testFloat.d:%s\n", d);
        long a = 10;
        int b = 10000;
        double ab = Math.ceil(a / (double) b);
        System.out.printf("testFloat.ab:%s\n", ab);

    }

    @Test
    public void testClassToString() {
        String string = this.getClass().toString();
        System.out.printf("testClassToString.string:%s\n", string);
        String string1 = this.getClass().toGenericString();
        System.out.printf("testClassToString.string1:%s\n", string1);
        String name = this.getClass().getPackage().getName();
        System.out.printf("testClassToString.name:%s\n", name);
    }

    @Test
    public void getSystemInfo() {
        Properties properties = System.getProperties();
        for (Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
            logger.debug("{}:{}", objectObjectEntry.getKey(), objectObjectEntry.getValue());
        }
    }

    @Test
    public void nioTest() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("config/config.xml", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer totalBuff = ByteBuffer.allocate((int) inChannel.size());
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.flip();
            totalBuff.put(buf);
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
        byte[] array = totalBuff.array();
        String s = new String(array);
        String string = array.toString();
        logger.debug("string:\n{}", string);
        logger.debug("s:\n{}", s);
    }

    @Test
    public void testBytes() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putInt(100);
        byteBuffer.putShort((short) 1);
        byte byte1 = 'a';
        byteBuffer.put(byte1);
        //        byteBuffer.flip();
        //        byteBuffer.putInt(99);
        byteBuffer.flip();
        int anInt = byteBuffer.getInt();
        logger.debug("first:{}", anInt);

        short aShort = byteBuffer.getShort();
        logger.debug("short:{}", aShort);
        byte[] dst = new byte[byteBuffer.remaining()];
        byteBuffer.get(dst);
        logger.debug("dst:{}", new String(dst));
    }

    @Test
    public void testPath() throws FileNotFoundException {
        Path path = Paths.get("abc/efg");
        //        FileInputStream fileInputStream = new FileInputStream(path.toString());
        logger.debug("path:{}", path);
        logger.debug("path:{}", path.toAbsolutePath());
        File[] files = File.listRoots();

        for (int i = 0; i < files.length; i++) {
            logger.debug("filePath:{}", files[i].getPath());
        }
        String pathSeparator = File.pathSeparator;
        logger.debug("pathSeparator:{}", pathSeparator);
        logger.debug("File.separator:{}", File.separator);
    }

    @Test
    public void testUpper() {
        String string = StringUtils.toUpCase("adf__YdsfBrown", "_");
        System.out.printf("testUpper.string:%s\n", string);
    }

    @Test
    public void testDate() {
        LocalTime localTime = LocalTime.fromMillisOfDay(0);
        Date now = new Date();
        Date lastTime = new Date(now.getTime() - TimeUnit.HOURS.toMillis(15) - TimeUnit.MINUTES.toMillis(30));

        int doDailyResetCount = getDoDailyResetCount(now.getTime(), lastTime.getTime(), localTime);
        logger.debug("testDate doDailyResetCount:{}", doDailyResetCount);

        Date lastTimeZero = new Date(lastTime.getYear(), lastTime.getMonth(), lastTime.getDate());
        Date todayNightZero = new Date(lastTimeZero.getTime() + TimeUnit.DAYS.toMillis(1) - 1);
        long l = todayNightZero.getTime() - lastTimeZero.getTime();
        long pastDay = l / TimeUnit.DAYS.toMillis(1);
        logger.debug("testDate pastDay:{}", pastDay);

    }

    /**
     * 判断某一个操作，自上次执行的时间点lastOpTime之后，当前时间点currentTime经过了 多少次重置时间点resetTime
     *
     * <p>
     */
    public static int getDoDailyResetCount(long currentTime, long lastOpTime, LocalTime resetTime) {
        if (currentTime <= lastOpTime) {
            return 0;
        }

        long resetMillisOfDay = resetTime.getMillisOfDay();
        return Days.daysBetween(new LocalDate(lastOpTime - resetMillisOfDay), new LocalDate(currentTime - resetMillisOfDay)).getDays();
    }

    @Test
    public void testFor() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(3);
        //        for (Integer integer : arrayList) {
        //            arrayList.remove(integer);
        //        }
        int iLen = arrayList.size();
        for (int i = 0; i < iLen; i++) {
            if (arrayList.size() > 0) {
                arrayList.remove(i);
                i--;

            }
        }
        for (int i = iLen - 1; i > -1; i--) {
            arrayList.remove(i);
        }
    }

    @Test
    public void testStringSplit() {
        String s = "";
        String[] split = s.split("#");
        logger.debug("testStringSplit split.length:{}", split.length);
    }

    @Test
    public void testMath() {
        int targetRangeMin = 50;
        int targetRangeMax = 50;
        boolean isRandomAngle = false;
        int masterToTargetAngle = 180;
        int range = RandomNumber.randomRange(targetRangeMin, targetRangeMax);
        float angle;
        if (isRandomAngle) {
            angle = RandomNumber.randomRange(-180, 180);
        } else {
            angle = masterToTargetAngle;
        }

        double radians = (angle / 180 * Math.PI);
        CodeCreateUtilsTest creator = this;

        double originX = 0;
        double originY = 0;
        double distance = Point2D.distance(creator.getBlockX(), creator.getBlockY(), originX, originY);
        double targetToMastRadian = 0;
        if (distance != 0) {
            targetToMastRadian = Math.atan2(originY - creator.getBlockY(), originX - creator.getBlockX());
            radians += targetToMastRadian;

        }
        double xDis = Math.round(Math.cos(radians) * range);
        double yDis = Math.round(Math.sin(radians) * range);
        logger.debug("testMath targetRangeMin:{},targetRangeMax:{}", targetRangeMin, targetRangeMax);
        logger.debug("testMath isRandomAngle:{}", isRandomAngle);
        logger.debug("testMath range:{}", range);
        logger.debug("testMath masterToTargetAngle:{}, angle:{}", masterToTargetAngle, angle);
        logger.debug("testMath radians:{}", radians);
        logger.debug("testMath originX:{},originY:{}", originX, originY);
        logger.debug("testMath distance:{}", distance);
        logger.debug("testMath targetToMastRadian:{}", targetToMastRadian);

        logger.debug("newMonster xDis:{},yDis:{}", xDis, yDis);
        originX += xDis;
        originY += yDis;
        logger.debug("testMath originX:{},originY:{}", originX, originY);
    }

    private double getBlockY() {
        return 0;
    }

    private double getBlockX() {
        return 0;
    }

    /**
     * 传说中的彩票随机器
     */
    public static void randomRoundIndex(Random random, IntArrayList indexList, int maxCount, int count) {
        if (maxCount < count) {
            throw new IllegalArgumentException("randomRoundIndex error. maxCount < count");
        }

        indexList.clear();
        for (int i = 0; i < maxCount; i++) {
            indexList.add(i);
        }

        int maxIdx = maxCount;
        for (int i = 0; i < count; i++) {
            int idx = random.nextInt(maxIdx--);
            if (idx > 0) {
                indexList.set(i, indexList.set(idx + i, indexList.get(i)));
            }
        }
    }

    /**
     * 传说中的彩票随机器
     */
    public static void randomRoundIndex1(Random random, IntArrayList indexList, int maxCount, int count) {
        if (maxCount < count) {
            throw new IllegalArgumentException("randomRoundIndex error. maxCount < count");
        }

        indexList.clear();
        for (int i = 0; i < maxCount; i++) {
            indexList.add(i);
        }

        int maxIdx = maxCount;
        for (int i = 0; i < count; i++) {
            int idx = random.nextInt(maxIdx--);
            if (idx > 0) {
                indexList.set(i, indexList.set(idx + i, indexList.get(i)));
            }
        }
    }


    @Test
    public void testRandom() {
        Random random = new Random((new Date().getTime() >>> 1) + 9580);
        IntArrayList indexArr = new IntArrayList();
        randomRoundIndex(random, indexArr, 50, 50);
        for (Integer index : indexArr.toArray()) {
            logger.debug("testRandom index:{}", index);
        }
    }

    @Test
    public void testArrInit() {
        int[] arr1 = new int[1];
        for (int i : arr1) {
            logger.debug("testArrInit i:{}", i);
        }
        long[][] arr2 = new long[3][3];
        for (long[] ints : arr2) {
            for (long anInt : ints) {
                logger.debug("testArrInit anInt:{}", anInt);
            }
        }

    }

    @Test
    public void testHashCode() {
        People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());

        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);

        People jack = new People("Jack", 12);
        Integer x = hashMap.get(jack);
        System.out.println(x);
    }

    class People {
        public String getName() {
            return name;
        }

        private String name;
        private int age;

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        //        @Override
        //        public int hashCode() {
        //            return name.hashCode() + age;
        //        }

        @Override
        public boolean equals(Object obj) {
            // TODO Auto-generated method stub
            return this.name.equals(((People) obj).name) && this.age == ((People) obj).age;
        }
    }

    @Test
    public void testArray() {
        ArrayList<Integer>[] arrayLists = new ArrayList[5];
        arrayLists[1] = new ArrayList<>();
        arrayLists[1].add(1);
        int count = 0;
        for (ArrayList<Integer> arrayList : arrayLists) {
            count++;
            System.out.printf("testArray.count:%s\n", count);
            System.out.printf("testArray.arrayList == null:%s\n", arrayList == null);
        }
    }

    @Test
    public void testHashMap() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer integer = map.get(0);
        System.out.printf("testHashMap.integer:%s\n", integer);
    }

    @Test
    public void testEnum() {
        AchievementType adf = AchievementType.valueOf("HERO_LEVEL");
        System.out.printf("testEnum.adf:%s\n", adf);
    }

    @Test
    public void testPattern() {
        Pattern computeFlagPattern = Pattern.compile("([+\\-*/]|\\(\\s*int\\s*\\)|\\(\\s*long\\s*\\))");
        Matcher matcher = computeFlagPattern.matcher("asdf()");
        System.out.printf("testPattern.matcher.find():%s\n", matcher.find());
        matcher = computeFlagPattern.matcher("asdf()");
        System.out.printf("testPattern.matcher.find():%s\n", matcher.find());
        matcher = computeFlagPattern.matcher("asdf(long)");
        System.out.printf("testPattern.matcher.find():%s\n", matcher.find());
        matcher = computeFlagPattern.matcher("asdf(int)");
        System.out.printf("testPattern.matcher.find():%s\n", matcher.find());
        matcher = computeFlagPattern.matcher("asdf( int )");
        System.out.printf("testPattern.matcher.find():%s\n", matcher.find());
    }

    @Test
    public void testDouble() {
        double d = Double.MIN_VALUE;
        logger.debug("testDouble d:{}", d);
        double d1 = 0.000003;
        d = d + d1;
        logger.debug("testDouble d:{}", d);
    }

    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        People zhangShan = new People("zhangshan", 1);
        int COUNT = 1000000;
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (i++ < COUNT) {
            String a = zhangShan.getName();
        }
        logger.debug("testField System.currentTimeMillis() - startTime:{}", System.currentTimeMillis() - startTime);
        Method name = zhangShan.getClass().getMethod("getName");

        startTime = System.currentTimeMillis();
        i = 0;
        while (i++ < COUNT) {
            String a = (String) name.invoke(zhangShan);
        }
        logger.debug("testField System.currentTimeMillis() - startTime:{}", System.currentTimeMillis() - startTime);
    }
}


















