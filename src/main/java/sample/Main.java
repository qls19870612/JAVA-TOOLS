package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
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
import sample.file.FileOperator;
import sample.startLoader.Constant;
import sample.startLoader.PropertyReaderHelper;
import sample.startLoader.SplashScreen;
import sample.utils.LogCustom;

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
        LogCustom logCustom = new LogCustom();
        logCustom.detach();
        String property = System.getProperty("java.ext.dirs");
        logger.debug("loadDefaultIcons property:{}", property);
        Class<? extends Main> aClass = getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        logger.debug("loadDefaultIcons getName:{}", aClass.getName());
        logger.debug("loadDefaultIcons classLoader:{}", classLoader);

        String baseUrl = "icons/";


        Image image = getImage(classLoader, baseUrl, "gear_16x16.png");
        return Arrays.asList(image, getImage(classLoader, baseUrl, "gear_24x24.png"), getImage(classLoader, baseUrl, "gear_36x36.png"),
                getImage(classLoader, baseUrl, "gear_42x42.png"), getImage(classLoader, baseUrl, "gear_64x64.png"));
    }

    private Image getImage(ClassLoader classLoader, String baseUrl, String s) {
        URL resource = classLoader.getResource(baseUrl + s);
        return new Image(resource.toExternalForm());
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#init()
     */
    @Override
    public void init() throws Exception {


        // Load in JavaFx Thread and reused by Completable Future, but should no be a big deal.
        long startTime = System.currentTimeMillis();
        try {
            defaultIcons.addAll(loadDefaultIcons());

        } catch (Exception e) {
            e.printStackTrace();
        }
        CompletableFuture.supplyAsync(() -> SpringApplication.run(SpringMain.class, savedArgs)).whenComplete((ctx, throwable) -> {

            applicationContext = ctx;
            initConfig();
            logger.debug("init currentTimeMillis:{}", System.currentTimeMillis() - startTime);
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

    private void initConfig() {
        AppConfig.initSqlLite();

        AppConfig.parserTemplate();

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
    public void start(Stage primaryStage) {
        checkDbFolder();
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

            }
            splashStage.hide();
            splashStage.setScene(null);
        });


    }

    private void checkDbFolder() {
        String config = FileOperator.getConfig("config/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new StringBufferInputStream(config));
            String property = properties.getProperty("spring.datasource.url");
            String[] split = property.split(":");
            String dbUrl = split[2];
            File file = new File(dbUrl);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("checkDbFolder getMessage:{}", e.getMessage());
        }

    }


    private void initPrimaryStage(Stage primaryStage) throws Exception {

        URL resource = getClass().getResource("/Main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.load();
        Controller controller = loader.getController();

        Parent root = loader.getRoot();
        primaryStage.setTitle("工具箱");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.getIcons().addAll(icons);
        primaryStage.show();
        //        root.getScene().getStylesheets().add(this.getClass().getResource("/css/listview.css").toExternalForm());
        //        URL resource1 = this.getClass().getResource("/css/JMetroDarkTheme.css");
        URL resource1 = this.getClass().getResource("/css/listview.css");
        String e = resource1.toExternalForm();
        root.getScene().getStylesheets().add(e);
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
