package sample.fxml.renders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import sample.entitys.PublishLogEntity;
import sample.file.FileOperator;
import sample.fxml.controllers.DiabloPublishController;
import sample.services.TableMangerService;
import sample.utils.Empty;
import sample.utils.SpringUtil;
import sample.utils.TimeUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/24 20:30
 */
public class PublishLogRender extends ListCell<PublishLogEntity> {
    private static final Logger logger = LoggerFactory.getLogger(PublishLogRender.class);

    private final Label dateLabel;
    private final Label versionLabel;
    private final Label serverLabel;
    private final HBox box;
    private final Button deleteBtn;
    private final Region rect;
    private final Button openBtn;

    public PublishLogRender() {
        super();
        dateLabel = new Label();
        dateLabel.minWidth(120);
        dateLabel.setMaxWidth(120);


        serverLabel = new Label();
        serverLabel.setPrefWidth(270);
        versionLabel = new Label();
        rect = new Region();
        rect.setMinWidth(-10);
        deleteBtn = new Button("删除");
        deleteBtn.setMinWidth(50);
        deleteBtn.setMaxWidth(50);
        openBtn = new Button("打开");
        openBtn.setMinWidth(50);
        openBtn.setMaxWidth(50);
        box = new HBox(10);
        box.getChildren().addAll(dateLabel, serverLabel, versionLabel, rect, deleteBtn, openBtn);
        this.getChildren().add(box);
        box.setVisible(false);

        HBox.setHgrow(rect, Priority.ALWAYS);

        deleteBtn.setOnMouseClicked(event -> {
            try {
                PublishLogRender.this.onClickDelete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        openBtn.setOnMouseClicked(event -> {
            PublishLogRender.this.onClickOpen();
        });
        this.setOnMouseClicked(event -> PublishLogRender.this.onClick());
    }

    private void onClick() {
        PublishLogEntity item = getItem();
        if (item != null) {

            DiabloPublishController.THIS.resolve(item);

        }
    }

    private void onClickOpen() {
        PublishLogEntity item = getItem();
        if (item != null) {
            File serverFolder = new File(item.getServerFolder());
            if (!serverFolder.exists() || !serverFolder.isDirectory()) {
                return;
            }
            File batFile = DiabloPublishController.THIS.getBatFile(serverFolder, item.getVersionName());
            FileOperator.openFileAndSelect(batFile.getPath());
        }
    }

    private void onClickDelete() throws Exception {
        PublishLogEntity item = getItem();
        if (item != null) {
            TableMangerService bean = SpringUtil.getBean(TableMangerService.class);
            bean.diabloPublishMapper.deleteLog(item.getId());
            this.getListView().getItems().remove(item);
            File serverFolder = new File(item.getServerFolder());
            if (!serverFolder.exists() || !serverFolder.isDirectory()) {
                return;
            }
            DiabloPublishController controller = DiabloPublishController.THIS;
            File batFile = controller.getBatFile(serverFolder, item.getVersionName());

            String versionProjectName = controller.getVersionProjectName(item.getVersionName(), controller.projectFileSelector.getFileName());
            File newProjectFile = new File(serverFolder.getAbsolutePath() + "/" + versionProjectName);
            ArrayList<File> files = new ArrayList<>();
            files.add(newProjectFile);
            files.add(batFile);
            File masterFile = controller.getMasterBatFile(serverFolder, item.getVersionName());
            File combatFile = controller.getCombatBatFile(serverFolder, item.getVersionName());
            if (masterFile.exists()) {
                files.add(masterFile);
            }
            if (combatFile.exists()) {
                files.add(combatFile);
            }
            try {
                controller.deleteFileAndCommit(serverFolder, "删除" + item.getVersionName(), files.toArray(Empty.FILES));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void updateItem(PublishLogEntity item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            box.setVisible(true);
            dateLabel.setText(TimeUtils.printDateTime(item.getUpdateDate()));
            versionLabel.setText("|　" + item.getVersionName());
            serverLabel.setText("|　" + item.getServerFolder());

            setGraphic(box);

        } else {
            box.setVisible(false);
        }
    }
}
