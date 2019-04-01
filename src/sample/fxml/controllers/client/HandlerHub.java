package sample.fxml.controllers.client;

import java.lang.reflect.Modifier;

import game.collection.IntHashMap;
import game.collection.IntHashMap.Entry;
import io.ytcode.reflect.clazz.Classes;
import io.ytcode.reflect.resource.Resources;
import io.ytcode.reflect.resource.Scanner;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:14
 */
public class HandlerHub implements IHandlerHub {
    private HandlerBase[] handlers;

    public HandlerHub() {
        initGMHandlers();
    }

    private void initGMHandlers() {
        Resources rs = Scanner.pkgs(Modules.class.getPackage().getName()).scan();
        Classes clss = rs.classes().subTypeOf(HandlerBase.class).filter(input -> !input.isInterface() && !Modifier.isAbstract(input.getModifiers()));
        IntHashMap<HandlerBase> handlerBases = new IntHashMap<>();
        int maxModuleId = 0;
        for (Class<?> cls : clss) {
            Handler annotation = cls.getAnnotation(Handler.class);
            if (annotation != null) {
                Class<HandlerBase> handlerBaseClass = (Class<HandlerBase>) cls;
                HandlerBase handlerBase = null;
                try {
                    handlerBase = handlerBaseClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (handlerBase == null) {
                    continue;
                }
                maxModuleId = Math.max(maxModuleId, annotation.moduleId());
                handlerBases.put(annotation.moduleId(), handlerBase);
            }
        }
        handlers = new HandlerBase[maxModuleId + 1];
        for (Entry<HandlerBase> handlerBaseEntry : handlerBases.entrySet()) {
            handlers[handlerBaseEntry.getKey()] = handlerBaseEntry.getValue();
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
