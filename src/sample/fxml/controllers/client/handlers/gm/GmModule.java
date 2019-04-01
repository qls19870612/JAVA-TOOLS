package sample.fxml.controllers.client.handlers.gm;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;

import sample.utils.Utils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:52
 */
public class GmModule {
    public final String moduleName;
    public ArrayList<GmCmd> cmds = new ArrayList<>();

    public String getPinyinString() {
        return pinyinString;
    }

    private String pinyinString;

    public GmModule(String moduleName) {
        this.moduleName = moduleName;
        try {
            pinyinString = PinyinHelper.toHanYuPinyinString(moduleName, Utils.outputFormat, "", true).toLowerCase();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            pinyinString = moduleName.toLowerCase();
        }
    }

    public void addGm(GmCmd cmd) {
        cmds.add(cmd);
    }
}
