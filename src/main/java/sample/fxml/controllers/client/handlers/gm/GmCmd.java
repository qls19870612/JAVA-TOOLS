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
public class GmCmd {
    public final String cmdName;
    public final String comment;
    public final String lowerCmdName;
    private String commentPinYin;
    public ArrayList<CmdParam> params = new ArrayList<>();

    public GmModule getCurrShowParent() {
        return currShowParent;
    }

    public void setCurrShowParent(GmModule currShowParent) {
        this.currShowParent = currShowParent;
    }

    private GmModule currShowParent;

    public GmCmd(String cmdName, String comment) {
        this.cmdName = cmdName;
        this.lowerCmdName = cmdName.toLowerCase();
        this.comment = comment;
        try {
            this.commentPinYin = PinyinHelper.toHanYuPinyinString(comment, Utils.outputFormat, "", true).toLowerCase();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            this.commentPinYin = comment;
        }
    }

    public void addCmdParams(CmdParam param) {
        this.params.add(param);

    }


    public String getCommentPinYin() {
        return commentPinYin;
    }


}