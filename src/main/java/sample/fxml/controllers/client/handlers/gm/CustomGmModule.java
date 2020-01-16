package sample.fxml.controllers.client.handlers.gm;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/23 17:25
 */
public class CustomGmModule extends GmModule {

    private String[] cmds;

    public CustomGmModule(String moduleName) {
        super(moduleName);
        this.type = GmModule.TYPE_CUSTOM;
    }

    public void fillCmds(HashMap<String, GmCmd> cmdHashMap) {
        this.gmCmds.clear();
        if (cmds != null) {
            for (String cmd : cmds) {
                GmCmd gmCmd = cmdHashMap.get(cmd.toLowerCase());
                if (gmCmd != null) {
                    addGm(gmCmd);
                }
            }
        }
    }

    public String[] getCmds() {
        return cmds;
    }

    public void setCmds(String[] cmds) {
        this.cmds = cmds;
    }

    public void menuAddGm(GmCmd cmd) {
        super.addGm(cmd);
        this.cmds = Arrays.copyOf(this.cmds, this.cmds.length + 1);
        this.cmds[this.cmds.length - 1] = cmd.lowerCmdName;
    }

    public void remove(GmCmd gmCmd) {
        this.gmCmds.remove(gmCmd);
    }

    public boolean container(GmCmd gmCmd) {
        return this.gmCmds.indexOf(gmCmd) > -1;
    }
}
