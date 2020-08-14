package sample.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import sample.Controller;
import sample.file.FileOperator;
import sample.file.FileOperator.Filter;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/23 14:30
 */
public class FileRenameUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileRenameUtils.class);

    /**
     * src和toDir不同一个目录时，是复制，否则是重命名
     * @param src
     * @param toDir
     * @param matchReg
     * @param replace
     * @return
     */
    public static boolean rename(String src, String toDir, String matchReg, String replace) {
        File root = new File(src);
        Pattern pattern = Pattern.compile(matchReg);

        ArrayList<File> allFiles = FileOperator.getAllFiles(root, entry -> {
            Matcher matcher = pattern.matcher(entry.getAbsolutePath().replace(src, ""));
            return matcher.find();
        });

        boolean isCopy = !src.equals(toDir);
        for (File file : allFiles) {
            try {
                String name = file.getName();

                String toName = name.replaceAll(matchReg, replace);
                String shortPath = file.getAbsolutePath().replace(src, "");
                shortPath = shortPath.replace(file.getName(), "");
                String toFilePath = toDir + shortPath + toName;
                if (toFilePath.endsWith(file.getAbsolutePath())) {
                    continue;
                }
                File toFile = new File(toFilePath);
                if (!toFile.getParentFile().exists()) {
                    toFile.getParentFile().mkdirs();
                }
                if (isCopy) {
                    Controller.log("复制文件:" + toShortPath(file) + " to:" + toShortPath(toFile));
                    Files.copy(file.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Controller.log("重命名文件:" + toShortPath(file) + " to:" + toShortPath(toFile));
                    Files.move(file.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    private static String toShortPath(File file) {
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.length() > 20) {
            absolutePath = "..." + absolutePath.substring(absolutePath.length() - 20);
        }
        return absolutePath;
    }

    public static void main(String[] args) {
        String src = "D:\\SVN\\client\\sourceCode\\SummonWorld2\\resources\\zhCN\\ATF\\pc\\assets\\atlasATF\\dyload\\PNG\\1001";
        rename(src, src, "\\w+_0+(\\d+)", "$1");
    }
}
