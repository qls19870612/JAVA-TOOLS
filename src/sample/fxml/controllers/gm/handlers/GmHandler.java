package sample.fxml.controllers.gm.handlers;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import sample.Controller;
import sample.fxml.controllers.gm.Client;
import sample.fxml.controllers.gm.Modules;
import sample.fxml.controllers.gm.handlers.base.GMParamType;
import sample.fxml.controllers.gm.handlers.base.Handler;
import sample.fxml.controllers.gm.handlers.base.HandlerBase;
import sample.utils.BufferUtil;
import sample.utils.StringUtils;
import sample.utils.Utils;

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
    public void handle(Client client, int sequence, ChannelBuffer buffer) {

        String gmMsg = BufferUtil.readUTF(buffer);

        if (gmMsg.startsWith("gmAll#")) {
            String substring = gmMsg.substring(6);
            ArrayList<GmModule> modules = parseAllGm(substring);
            client.gmProxyController.updateModules(modules);
        } else {
            logger.debug("handle gmMsg:{}", gmMsg);
            Controller.log(gmMsg);
            if (gmMsg.startsWith("设置代理目标")) {
                String[] split = gmMsg.split("：");
                client.gmProxyController.setProxy(split[1]);
            } else if (gmMsg.startsWith("清空代理目标")) {
                client.gmProxyController.setProxy("");

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
        modules.sort((o1, o2) -> o1.pinyinString.compareTo(o2.pinyinString));
        return modules;
    }

    public class GmModule {
        public final String moduleName;
        public ArrayList<GmCmd> cmds = new ArrayList<>();

        public String getPinyinString() {
            return pinyinString;
        }

        private String pinyinString;

        public GmModule(String moduleName) {
            this.moduleName = moduleName;
            try {
                pinyinString = PinyinHelper.toHanYuPinyinString(moduleName, Utils.outputFormat, "", true).toLowerCase();
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
                pinyinString = moduleName.toLowerCase();
            }
        }

        public void addGm(GmCmd cmd) {
            cmds.add(cmd);
        }
    }

    public class GmCmd {
        public final String cmdName;
        public final String comment;
        public final String lowerCmdName;
        public ArrayList<CmdParam> params = new ArrayList<>();

        public GmCmd(String cmdName, String comment) {
            this.cmdName = cmdName;
            this.lowerCmdName = cmdName.toLowerCase();
            this.comment = comment;
        }

        public void addCmdParams(CmdParam param) {
            this.params.add(param);

        }
    }

    public class CmdParam {
        public final String paramsName;
        public final GMParamType type;
        public final Object defaultValue;


        public CmdParam(String[] paramArr) {
            if (paramArr.length < 3) {

                logger.debug("CmdParam paramArr:{}", paramArr);

            }
            if (paramArr.length > 3) {
                StringBuilder remain = new StringBuilder();

                for (int i = 2; i < paramArr.length; i++) {
                    remain.append(paramArr[i]);
                }
                paramArr = Arrays.copyOf(paramArr, 3);
                paramArr[2] = remain.toString();
            }

            this.paramsName = paramArr[2];
            type = GMParamType.of(paramArr[0]);
            String[] split = paramArr[1].split(":");
            if (split.length > 1) {

                defaultValue = type.convert(split[1]);
            } else {
                defaultValue = type.convert("");


            }


        }
    }
}
