package sample.fxml.controllers.client.robots;

import com.google.common.base.Preconditions;

import app.protobuf.client.CommonContent.RaceId;

import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_HEAD;
import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_LEVEL;
import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_MALE;
import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_RACE;
import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_REIN;
import static sample.fxml.controllers.client.robots.HeroEasyInfoCoder.EasyInfoType.EASY_VIP;


/**
 *
 * @author lml
 * @create 2016 10 19 下午5:11
 */
public class HeroEasyInfoCoder {


    public enum EasyInfoType {
        /**
         * 注意注意注意注意注意注意注意注意注意注意注意注意
         * 这里的bitCount不能修改如果修改，需要 同步users表中的easy_info字段
         */
        EASY_MALE(1, null),

        EASY_LEVEL(12, EASY_MALE),

        EASY_RACE(3, EASY_LEVEL),

        EASY_HEAD(5, EASY_RACE),

        EASY_VIP(4, EASY_HEAD),

        EASY_REIN(3, EASY_VIP),;

        private final int bitCount;//此字段位数
        private final int startBit;//此字段起始位 索引，从0开始，最大32（如果超出，考虑换long类型
        private final int cutHighBit;//此字段切除高位的掩码
        public final String searchConditionValue;

        EasyInfoType(int bitCount, EasyInfoType prevType) {

            this.bitCount = bitCount;
            this.cutHighBit = (1 << this.bitCount) - 1;
            if (prevType != null) {

                this.startBit = prevType.bitCount + prevType.startBit;
            } else {

                this.startBit = 0;
            }
            Preconditions.checkArgument(this.bitCount + this.startBit <= 32, "easy_info 溢出,请换long");
            searchConditionValue = "(easy_info >>" + startBit + ")&" + cutHighBit;
        }

        public int getCombineValue(int value) {
            return (value & cutHighBit) << startBit;
        }

        public int decodeCombineValue(int easyInfo) {
            return (easyInfo >> startBit) & cutHighBit;
        }
    }


    public static int decodeHead(int easyInfo) {
        return EASY_HEAD.decodeCombineValue(easyInfo);
    }

    public static int decodeVip(int easyInfo) {
        return EASY_VIP.decodeCombineValue(easyInfo);
    }

    public static boolean decodeIsMale(int easyInfo) {
        return EASY_MALE.decodeCombineValue(easyInfo) == 1;
    }

    public static int decodeGetLevel(int easyInfo) {
        return EASY_LEVEL.decodeCombineValue(easyInfo);
    }

    public static RaceId decodeRaceId(int easyInfo) {
        return RaceId.valueOf(EASY_RACE.decodeCombineValue(easyInfo));
    }

    public static int decodeGetReinLevel(int easyInfo) {
        return EASY_REIN.decodeCombineValue(easyInfo);
    }


}
