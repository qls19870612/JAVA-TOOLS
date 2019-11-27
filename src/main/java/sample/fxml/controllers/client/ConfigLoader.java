package sample.fxml.controllers.client;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import game.initializer.Config;
import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderBytes;
import game.initializer.io.loader.FileLoaderOS;
import game.initializer.parser.CfgParserKv;
import game.initializer.parser.CfgParserTsv;
import game.initializer.parser.CfgParserXls;
import game.service.IThreadService;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by wyt on 16-12-5.
 */

public class ConfigLoader implements GuiceClean {

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public static ConfigLoader of(FileLoader fileLoader, IThreadService threadService) {
        return new ConfigLoader(fileLoader, threadService);
    }

    public static final String packedCfgName = "config.pk";

    private FileLoader fileLoader;

    private final IThreadService threadService;
 
    ConfigLoader(IThreadService threadService) {
        this.threadService = threadService;
        File file = new File("config");
        if (file.exists()) {
            checkArgument(file.isDirectory(), "检测到config，但居然不是文件夹！%s", file.getAbsolutePath());
            this.fileLoader = FileLoaderOS.of("");
        } else {
            InputStream is = ClassLoader.getSystemResourceAsStream(packedCfgName);
            byte[] data;
            try {
                data = ByteStreams.toByteArray(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.fileLoader = FileLoaderBytes.of(data);
        }
    }

    private ConfigLoader(FileLoader fileLoader, IThreadService threadService) {
        this.fileLoader = fileLoader;
        this.threadService = threadService;
    }

    public List<Config> load(String path) {
        return load(path, false); // TODO 妥协改成了false，这样ok？
    }

    public List<Config> load(String path, boolean mustExists) {
        logger.info("load: {}", path);
        String s = fileLoader.fileToStr(path, mustExists);
        if (s != null) {
            return CfgParserTsv.parse(s, path);
        }
        return Collections.emptyList();
    }

    public Config loadKV(String path) {
        return loadKV(path, true);
    }

    public Config loadKV(String path, boolean mustExists) {
        String s = fileLoader.fileToStr(path, mustExists);
        if (s != null) {
            return CfgParserKv.parse(s, path);
        }
        return Config.EMPTY;
    }

    public List<Config> loadXls(String path, String sheetName) {
        return loadXls(path, sheetName, true);
    }

    public List<Config> loadXls(String path) {
        return CfgParserXls.parseList(path, fileLoader.fileToBytes(path, true), this.threadService.getDbExecutor());
    }

    public List<Config> loadXls(String path, String sheetName, boolean mustExists) {
        byte[] bytes = fileLoader.fileToBytes(path, mustExists);
        if (bytes == null) {
            return Lists.newArrayListWithCapacity(0);
        }
        return CfgParserXls.parseList(path, sheetName, fileLoader.fileToBytes(path, mustExists), this.threadService.getDbExecutor());
    }

    public String loadFile(String file) {
        return fileLoader.fileToStr(file, false);
    }

    public Map<String, String> loadDir(String dir) {
        return fileLoader.dirToStr(dir, false);
    }

    public FileLoader getFileLoader() {
        return fileLoader;
    }

    public boolean needToDel(String name) {
        return name.endsWith("_del");
    }

    public String getOrginalName(String name) {
        return name.replace("_del", "");
    }

    @Override
    public void clean() {
        fileLoader.clean();
        fileLoader = null;
    }
}
