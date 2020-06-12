package sample.fxml.controllers;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.File;
import java.io.IOException;

import javafx.scene.input.MouseEvent;
import sample.ITab;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;
import sample.utils.XlsDoubleColCompareUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/04 18:57
 */
public class XlsDoubleColCompareController implements ITab {
    public FileSelector xlsFileSelector;

    public void onStartCompareBtnClick(MouseEvent mouseEvent) throws IOException {
        if (!xlsFileSelector.isExistsFile()) {
            AlertBox.showAlert("请先设置文件路径");
            return;
        }
        XlsDoubleColCompareUtils.compare(new File(xlsFileSelector.getPath()));
    }

    @Override
    public void onSelect() {

    }

    @Override
    public void onAppClose() {

    }
}
