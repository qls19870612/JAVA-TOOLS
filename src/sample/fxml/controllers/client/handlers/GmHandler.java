package sample.fxml.controllers.client.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import sample.Controller;
import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.gm.GmClient;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.handlers.gm.CmdParam;
import sample.fxml.controllers.client.handlers.gm.GmCmd;
import sample.fxml.controllers.client.handlers.gm.GmModule;
import sample.utils.BufferUtil;
import sample.utils.StringUtils;

import static sample.utils.Utils.BR;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 12:02
 */
@Handler(moduleId = Modules.GM_MODULE_ID)
public class GmHandler extends HandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(GmHandler.class);


    @Override
    public void handle(IClient iclient, int sequence, ChannelBuffer buffer) {
        if (!(iclient instanceof GmClient)) {
            return;
        }
        String gmMsg = BufferUtil.readUTF(buffer);

        GmClient client = (GmClient) iclient;

        if (gmMsg.startsWith("gmAll#")) {
            String substring = gmMsg.substring(6);
            ArrayList<GmModule> modules = parseAllGm(substring);
            client.updateModules(modules);
        } else {
            Controller.log(gmMsg);
            if (gmMsg.startsWith("设置代理目标")) {
                String[] split = gmMsg.split("：");
                client.setProxy(split[1]);
            } else if (gmMsg.startsWith("清空代理目标")) {
                client.setProxy("");

            }
        }


    }

    private ArrayList<GmModule> parseAllGm(String substring) {
        String[] lines = substring.split(BR);
        ArrayList<GmModule> modules = new ArrayList<>();
        GmModule module = null;
        GmCmd cmd = null;
        CmdParam params = null;

        int len = lines.length;
        for (int i = 0; i < len; i++) {
            String line = lines[i].trim();
            if (StringUtils.isEmpty(line)) {
                continue;
            }
            if (line.startsWith("=========")) {
                module = new GmModule(line.replaceAll("=========", ""));
                modules.add(module);
                continue;
            }
            if (line.endsWith("-----")) {
                String cmdComment = line.replaceAll("-----", "");
                i += 2;
                String cmdName = lines[i];
                cmd = new GmCmd(cmdName.trim(), cmdComment);
                module.addGm(cmd);
                continue;
            }
            String[] paramArr = line.split("\t");
            params = new CmdParam(paramArr);
            cmd.addCmdParams(params);
        }
        return modules;
    }


}
