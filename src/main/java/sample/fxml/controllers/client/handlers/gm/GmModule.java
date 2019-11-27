package sample.fxml.controllers.client.handlers.gm;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Comparator;

import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:52
 */
public class GmModule {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_CUSTOM = 0;
    public static Comparator<? super GmModule> moduleSort = new Comparator<GmModule>() {
        @Override
        public int compare(GmModule o1, GmModule o2) {
            if (o1.type != o2.type) {
                return o1.type - o2.type;
            }
            return o1.pinyinString.compareTo(o2.pinyinString);
        }
    };
    public final String moduleName;
    protected int type;
    public ArrayList<GmCmd> gmCmds = new ArrayList<>();

    public String getPinyinString() {
        return pinyinString;
    }

    private String pinyinString;

    public GmModule(String moduleName) {
        this.type = GmModule.TYPE_NORMAL;
        this.moduleName = moduleName;
        try {
            pinyinString = PinyinHelper.toHanYuPinyinString(moduleName, Utils.outputFormat, "", true).toLowerCase();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            pinyinString = moduleName.toLowerCase();
        }
    }

    public void addGm(GmCmd cmd) {
        gmCmds.add(cmd);
    }
}
