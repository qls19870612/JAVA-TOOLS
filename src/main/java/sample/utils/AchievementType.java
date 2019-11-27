package sample.utils;


/**
 *
 * 创建人  liangsong
 * 创建时间 2018/09/23 11:28
 */
public enum AchievementType {
    HERO_LEVEL,

    FEATHER_LEVEL,

    GOD_LEVEL,

    MAGIC_LEVEL,

    REIN_LEVEL,

    ACTIVITY_FEATHER,

    ACTIVITY_FS,

    WARE_QUALITY_EQUIP(false, false);


    private boolean needCountProgress;
    private final boolean isLine;

    /**
     * 默认构造函数，必需是线性类型的
     */
    AchievementType() {
        needCountProgress = false;
        isLine = true;
    }

    AchievementType(boolean isLine, boolean needCountProgress) {

        this.needCountProgress = needCountProgress;
        this.isLine = isLine;
    }


    /**
     * 是否是线性的(上一个达成后，下一个才开始)
     * @return
     */
    public boolean getIsLine() {
        return isLine;
    }

    public boolean getNeedCountProgress() {
        return this.needCountProgress;
    }
}