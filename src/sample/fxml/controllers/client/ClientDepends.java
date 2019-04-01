package sample.fxml.controllers.client;

import game.service.IThreadService;
import sample.fxml.controllers.client.robots.DBService;
import sample.fxml.controllers.client.threads.ThreadServiceImpl;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 16:28
 */
public class ClientDepends {

    public final TimeService timeService;
    public final HandlerHub handlerHub;
    public final IThreadService threadService;
    public final DBService dbService;

    public ClientDepends() {
        timeService = new TimeService();
        handlerHub = new HandlerHub();
        threadService = new ThreadServiceImpl();
        dbService = new DBService();
    }
}
