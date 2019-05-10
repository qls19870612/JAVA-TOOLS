package sample.fxml.controllers.client.handlers.gm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import sample.fxml.controllers.client.handlers.base.GMParamType;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:51
 */
public class CmdParam {
    private static final Logger logger = LoggerFactory.getLogger(CmdParam.class);
    public final String paramsName;
    public final GMParamType type;
    public final Object defaultValue;

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    private String inputValue;


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
