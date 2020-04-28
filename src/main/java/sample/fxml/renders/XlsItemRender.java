package sample.fxml.renders;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import sample.datas.vo.XlsInfo;
import sample.fxml.controllers.XLS2LUAController;
import sample.fxml.componet.AlertBox;
import sample.utils.Xls2LuaUtils;
import sample.utils.Xls2TsUtils;

import static sample.utils.Xls2TxtUtils.createTxt;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 9:59
 */
public class XlsItemRender extends ListCell<XlsInfo> {
    private XlsInfo item;
    private Label label;
    private HBox hbox;

    public XlsItemRender() {
        super();
    }

    @Override
    protected void updateItem(final XlsInfo item, boolean empty) {
        super.updateItem(item, empty);
        this.item = item;
        if (item != null) {
            if (label == null) {
                hbox = new HBox();
                //                System.out.println("getListView().getPrefWidth():" + getListView().getPrefWidth());
                hbox.prefWidth(getListView().getPrefWidth());
                hbox.prefHeight(30);
                label = new Label();
                label.setPrefWidth(300);

                Button btn = new Button("生成Lua");
                btn.setPrefSize(100, 26);
                btn.setAlignment(Pos.CENTER);
                final XlsItemRender cell = this;
                btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    XlsItemRender c = cell;

                    public void handle(MouseEvent event) {
                        boolean isUpdated = Xls2LuaUtils.createLua(c.item);
                        if (!isUpdated) {
                            AlertBox.showAlert("此文件不需要更新!");
                        } else {
                            c.updateItem(c.item, false);
                            XLS2LUAController.instance.updateLuaCfg();
                        }
                    }
                });
                Button tsBtn = new Button("生成Ts");
                tsBtn.setPrefSize(100, 26);
                tsBtn.setAlignment(Pos.CENTER);
                final XlsItemRender tsCell = this;
                tsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    XlsItemRender c = tsCell;

                    public void handle(MouseEvent event) {
                        boolean isUpdated = Xls2TsUtils.createTs(c.item);
                        if (!isUpdated) {
                            AlertBox.showAlert("此文件不需要更新!");
                        } else {
                            c.updateItem(c.item, false);
                            XLS2LUAController.instance.updateLuaCfg();
                        }
                    }
                });
                Button txtBtn = new Button("生成Txt");
                txtBtn.setPrefSize(100, 26);
                txtBtn.setAlignment(Pos.CENTER);
                txtBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    XlsItemRender c = cell;

                    @Override
                    public void handle(MouseEvent event) {
                        createTxt(c.item.file);
                    }
                });
                Region reg = new Region();
                HBox.setHgrow(reg, Priority.ALWAYS);
                Region reg1 = new Region();
                HBox.setHgrow(reg1, Priority.ALWAYS);
                Region reg2 = new Region();
                HBox.setHgrow(reg2, Priority.ALWAYS);
                hbox.getChildren().addAll(label, reg, btn,reg1,tsBtn, reg2, txtBtn);
            }
            if (item.isNeedUpdate()) {
                label.setText(item.fileName);
                label.setTextFill(Color.web("#000000"));
            } else {
                label.setText(item.fileName);
                label.setTextFill(Color.web("#009900"));
            }
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }
}
