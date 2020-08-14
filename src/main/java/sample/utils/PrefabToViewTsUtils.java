package sample.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.bcel.internal.generic.NEW;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;

import sample.file.FileOperator;
import sample.fxml.controllers.XlsController;
import sample.fxml.controllers.cocos.CocosNode;
import sample.fxml.controllers.ts.JavaAndTsDialectProvider;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/06/22 11:32
 */
public class PrefabToViewTsUtils {
    private static final Logger logger = LoggerFactory.getLogger(PrefabToViewTsUtils.class);

    private static VelocityEngine velocityEngine;

    public static void main(String[] args) {
        byte[] decode = Base64.getDecoder().decode("26nt1wgHJEUrV/U563uIeg");
        String s = new String(decode);
        logger.debug("main s:{}", s);

    }

    public static void createTsByTemplate(File newDir, File file, ArrayList<CocosNode> filedNameArr) {
        if (velocityEngine == null) {
            Properties properties = new Properties();
            //设置velocity资源加载方式为class
            properties.setProperty("resource.loader", "class");
            //设置velocity资源加载方式为file时的处理类
            properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            velocityEngine = new VelocityEngine();
            try {
                velocityEngine.init(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        VelocityContext context = new VelocityContext();
        String name = file.getName().replace(".prefab", "");
        if (name.endsWith("View")) {
            name = name.substring(0, name.length() - 4);
        }
        name = name + "Base";
        context.put("name", name);
        context.put("list", filedNameArr);
        StringWriter readWriter = new StringWriter();
        try {

            velocityEngine.mergeTemplate("config/cocosViewTemplate.vm", "UTF-8", context, readWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.debug("createTsByTemplate toString:{}", readWriter.toString());

        FileOperator.writeFile(new File(newDir.getAbsolutePath() + "/" + name + "View.ts"), readWriter.toString());


    }
}















