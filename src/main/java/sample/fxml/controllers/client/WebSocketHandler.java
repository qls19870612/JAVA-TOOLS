package sample.fxml.controllers.client;

import org.java_websocket.drafts.Draft_6455;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/05/08 9:26
 */
public class WebSocketHandler extends Draft_6455 {
    private IClient client;

    public WebSocketHandler(IClient client) {
        this.client = client;
    }
}
