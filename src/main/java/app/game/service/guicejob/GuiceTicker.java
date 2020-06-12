package app.game.service.guicejob;

/** Created by wyt on 16-12-5. */
public interface GuiceTicker {

  void tick(long ctime);

  long tickDelay();
}
