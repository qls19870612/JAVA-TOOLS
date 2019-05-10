package sample.fxml.controllers.client;

import game.service.IThreadService;
import sample.fxml.controllers.client.config.NoticeDatas;
import sample.fxml.controllers.client.robots.DBService;
import sample.fxml.controllers.client.threads.ThreadServiceImpl;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 16:28
 */
public class ClientDepends {

    private boolean isClosed = false;
    public final TimeService timeService;
    public final HandlerHub handlerHub;
    public final IThreadService threadService;
    public final DBService dbService;
    public final NoticeDatas noticeDatas;

    public ClientDepends() {
        timeService = new TimeService();
        handlerHub = new HandlerHub();
        threadService = new ThreadServiceImpl();
        dbService = new DBService();
        //        ConfigLoader configLoader = new ConfigLoader(threadService);
        noticeDatas = new NoticeDatas();
    }

    public void close() {
        if (isClosed) {
            return;
        }
        isClosed = true;
        timeService.close();
        threadService.close();
        dbService.close();
    }
}
