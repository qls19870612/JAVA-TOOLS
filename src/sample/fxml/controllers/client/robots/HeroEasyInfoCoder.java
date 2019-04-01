package sample.fxml.controllers.client.robots;

/**
 *
 * @author lml
 * @create 2016 10 19 下午5:11
 */
public class HeroEasyInfoCoder {

    public static int decodeHead(int easyInfo) {
        return (easyInfo >>> 16) & 0b1_111;
    }

    public static int decodeVip(int easyInfo) {
        return (easyInfo >>> 21) & 0b1111;
    }

    public static boolean decodeIsMale(int easyInfo) {
        return (easyInfo & 0b1) != 0;
    }

    public static int decodeGetLevel(int easyInfo) {
        return (easyInfo >>> 1) & 0b1111_1111_1111;
    }

    public static int decodeRaceId(int easyInfo) {
        int race = (easyInfo >>> 13) & 0b111;
        return race;
    }

    public static int decodeGetReinLevel(int easyInfo) {
        return easyInfo >>> 24 & 0b111;
    }
}
