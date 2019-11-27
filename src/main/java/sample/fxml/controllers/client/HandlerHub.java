package sample.fxml.controllers.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.utils.SpringUtil;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:14
 */
public class HandlerHub implements IHandlerHub {
    private static final Logger logger = LoggerFactory.getLogger(HandlerHub.class);
    private HandlerBase[] handlers;

    public HandlerHub() {
        initGMHandlers();
    }

    private void initGMHandlers() {

        Map<String, Object> handlerMap = SpringUtil.getHandlers();
        int maxModuleId = 0;
        for (Map.Entry<String, Object> entry : handlerMap.entrySet()) {
            HandlerBase handlerBase = (HandlerBase) entry.getValue();
            Handler annotation = handlerBase.getClass().getAnnotation(Handler.class);
            maxModuleId = Math.max(maxModuleId, annotation.moduleId());

        }
        this.handlers = new HandlerBase[maxModuleId + 1];

        for (Map.Entry<String, Object> entry : handlerMap.entrySet()) {
            HandlerBase handlerBase = (HandlerBase) entry.getValue();
            Handler annotation = handlerBase.getClass().getAnnotation(Handler.class);
            this.handlers[annotation.moduleId()] = handlerBase;
        }


    }

    @Override
    public HandlerBase getHandler(int moduleId) {
        if (moduleId < 0 || moduleId >= handlers.length) {
            return null;
        }
        return handlers[moduleId];
    }


}
