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
import sample.datas.vo.CodeInfo;
import sample.utils.CodeCreateUtils;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class CreateJavaClassRender extends ListCell<CodeInfo> {
    private CodeInfo item;
    private Label label;
    private HBox hbox;
    private Button btn;

    public CreateJavaClassRender() {
        super();
    }

    @Override
    protected void updateItem(final CodeInfo item, boolean empty) {
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

                btn = new Button("生成代码");
                btn.setPrefSize(100, 26);
                btn.setAlignment(Pos.CENTER);
                final CreateJavaClassRender cell = this;
                btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    CreateJavaClassRender c = cell;

                    public void handle(MouseEvent event) {
                        CodeCreateUtils.createCode(c.item);
                    }
                });

                Region reg = new Region();
                HBox.setHgrow(reg, Priority.ALWAYS);
                hbox.getChildren().addAll(label, reg, btn);
            }
            label.setText(item.className);
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }
}
