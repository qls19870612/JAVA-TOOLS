package sample.fxml.controllers.client.config;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import game.collection.IntHashMap;
import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;

import static sample.Controller.log;
import static sample.config.AppConfig.noticePath;
import static sample.utils.CodeCreateUtils.getEncodePath;
import static sample.utils.Xls2TxtUtils.getStringFromCellValue;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 21:06
 */
public class NoticeDatas {
    private static final Logger logger = LoggerFactory.getLogger(NoticeDatas.class);
    private static final IntHashMap<NoticeData> datas = new IntHashMap<>();


    public NoticeDatas() {

        List<Map<String, String>> maps = initConfig();
        if (maps != null) {
            for (Map<String, String> map : maps) {

                NoticeData noticeData = new NoticeData(map);
                datas.put(noticeData.key, noticeData);
            }
        }
    }

    private List<Map<String, String>> initConfig() {
        try {
            File file = new File(noticePath);
            FileLoader fileLoader = FileLoaderOS.of(file.getPath().replace(file.getName(), ""));

            String fileName = getEncodePath(file.getName());
            byte[] fileContent = fileLoader.fileToBytes(fileName, true);

            InputStream is = new ByteArrayInputStream(fileContent);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            return createShellFile(file, sheetIterator.next());


        } catch (IOException e) {
            e.printStackTrace();
            log("警告", "配置文件解析失败:" + e.getMessage());
        }
        return null;
    }

    private List<Map<String, String>> createShellFile(File file, Sheet sheet) {
        int rowCount = sheet.getLastRowNum() + 1;

        String[] header = null;
        List<Map<String, String>> configs = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                logger.debug("空行filePath:{} rowCount:{} i:{}", noticePath, rowCount, i);

                continue;
            }


            HashMap<String, String> map = null;
            int colCount = row.getLastCellNum() + 1;
            if (i == 0) {
                header = new String[colCount];
            } else if (i == 1 || i == 2) {
                continue;
            } else {
                map = new HashMap<>();
            }

            boolean isAdd = false;
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                String value = getStringFromCellValue(cell);
                if (i == 0) {
                    header[j] = StringUtils.trim(value);
                    continue;
                } else {

                    assert header != null;
                    if (j >= header.length) {
                        break;
                    }
                }

                if (cell == null || StringUtils.isEmpty(header[j])) {
                    continue;
                }


                map.put(header[j], value);
                isAdd = true;
            }
            if (isAdd) {
                configs.add(map);
            }
        }
        return configs;

    }

    public final NoticeData getData(int moduleId, int msgId) {
        int key = NoticeData.getKey(moduleId, msgId);
        return datas.get(key);
    }
}
