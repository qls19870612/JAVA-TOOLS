package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.config.AppConfig;
import sample.startLoader.Constant;
import sample.startLoader.PropertyReaderHelper;
import sample.startLoader.SplashScreen;

import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;


public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    static SplashScreen splashScreen;

    private static List<Image> icons = new ArrayList<>();

    private static Consumer<Throwable> errorAction = defaultErrorAction();

    private final List<Image> defaultIcons = new ArrayList<>();

    private final CompletableFuture<Runnable> splashIsShowing;

    private static String[] savedArgs = new String[0];
    private static Stage primaryStage;
    private ConfigurableApplicationContext applicationContext;

    /**
     * Default error action that shows a message and closes the app.
     */
    private static Consumer<Throwable> defaultErrorAction() {
        return e -> {
            Alert alert = new Alert(AlertType.ERROR,
                    "Oops! An unrecoverable error occurred.\n" + "Please contact your software vendor.\n\n" + "The application will stop now.");
            alert.showAndWait().ifPresent(response -> Platform.exit());
        };
    }

    public Main() {
        splashIsShowing = new CompletableFuture<>();
    }

    public Collection<Image> loadDefaultIcons() {
        String property = System.getProperty("java.ext.dirs");
        logger.debug("loadDefaultIcons property:{}", property);
        Class<? extends Main> aClass = getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        logger.debug("loadDefaultIcons getName:{}", aClass.getName());
        logger.debug("loadDefaultIcons classLoader:{}", classLoader);

        String baseUrl = "/icons/";


        return Arrays.asList(new Image(classLoader.getResource(baseUrl + "gear_16x16.png").toExternalForm()),
                new Image(classLoader.getResource(baseUrl + "gear_24x24.png").toExternalForm()),
                new Image(classLoader.getResource(baseUrl + "gear_36x36.png").toExternalForm()),
                new Image(classLoader.getResource(baseUrl + "gear_42x42.png").toExternalForm()),
                new Image(classLoader.getResource(baseUrl + "gear_64x64.png").toExternalForm()));
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#init()
     */
    @Override
    public void init() throws Exception {
        // Load in JavaFx Thread and reused by Completable Future, but should no be a big deal.
        try {
            defaultIcons.addAll(loadDefaultIcons());

        } catch (Exception e) {

        }
        CompletableFuture.supplyAsync(() -> SpringApplication.run(SpringMain.class, savedArgs)).whenComplete((ctx, throwable) -> {
            applicationContext = ctx;
            if (throwable != null) {
                logger.error("Failed to load spring application context: ", throwable);
                Platform.runLater(() -> errorAction.accept(throwable));
            } else {
                Platform.runLater(() -> {
                    applyEnvPropsToView(ctx);
                    loadIcons(ctx);
                    //                    launchApplicationView(ctx);
                });
            }
        }).thenAcceptBothAsync(splashIsShowing, (ctx, closeSplash) -> {
            Platform.runLater(closeSplash);
        });
    }

    private void loadIcons(ConfigurableApplicationContext ctx) {
        try {
            final List<String> fsImages = PropertyReaderHelper.get(ctx.getEnvironment(), Constant.KEY_APPICONS);
            if (!fsImages.isEmpty()) {
                fsImages.forEach((s) -> {
                    Image img = new Image(getClass().getResource(s).toExternalForm());
                    icons.add(img);
                });
            } else { // add factory images
                icons.addAll(defaultIcons);
            }
        } catch (Exception e) {
            logger.error("Failed to load icons: ", e);
        }
    }

    /**
     * Apply env props to view.
     * @param ctx
     */
    private static void applyEnvPropsToView(ConfigurableApplicationContext ctx) {
        PropertyReaderHelper.setIfPresent(ctx.getEnvironment(), Constant.KEY_TITLE, String.class, primaryStage::setTitle);

        PropertyReaderHelper.setIfPresent(ctx.getEnvironment(), Constant.KEY_STAGE_WIDTH, Double.class, primaryStage::setWidth);

        PropertyReaderHelper.setIfPresent(ctx.getEnvironment(), Constant.KEY_STAGE_HEIGHT, Double.class, primaryStage::setHeight);

        PropertyReaderHelper.setIfPresent(ctx.getEnvironment(), Constant.KEY_STAGE_RESIZABLE, Boolean.class, primaryStage::setResizable);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        final Stage splashStage = new Stage(StageStyle.TRANSPARENT);

        if (splashScreen.visible()) {
            final Scene splashScene = new Scene(splashScreen.getParent(), Color.TRANSPARENT);
            splashStage.setScene(splashScene);
            splashStage.getIcons().addAll(defaultIcons);
            splashStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("启动");
            splashStage.show();
        }

        splashIsShowing.complete(() -> {
            try {
                initPrimaryStage(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                splashStage.hide();
                splashStage.setScene(null);
            }

        });


    }

    private void initPrimaryStage(Stage primaryStage) throws Exception {
        AppConfig.initSqlLite();
        AppConfig.initFactory();
        AppConfig.parseDataTypeMap();
        AppConfig.parserTemplate();
        AppConfig.parseClassConfig();
        URL resource = getClass().getResource("/sample.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.load();
        Controller controller = loader.getController();

        Parent root = loader.getRoot();
        primaryStage.setTitle("XLS文件转java配置类");
        primaryStage.setScene(new Scene(root, 820, 600));
        primaryStage.show();
        root.getScene().getStylesheets().add(this.getClass().getResource("/css/listview.css").toExternalForm());
        controller.init();
        primaryStage.addEventHandler(WINDOW_CLOSE_REQUEST, event -> controller.onAppClose());
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (applicationContext != null) {
            applicationContext.close();
        }
    }


    public static void main(String[] args) {

        splashScreen = new SplashScreen();
        savedArgs = args;


        launch(args);

    }


}
