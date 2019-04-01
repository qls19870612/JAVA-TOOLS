package sample.fxml.controllers.client;

import sample.fxml.controllers.client.handlers.base.HandlerBase;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 14:13
 */
public interface IHandlerHub {
    HandlerBase getHandler(int moduleId);
}
