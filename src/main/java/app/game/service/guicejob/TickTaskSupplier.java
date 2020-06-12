package app.game.service.guicejob;

/** Created by wyt on 3/27/17. */
public interface TickTaskSupplier {

  TickTask[] tickTasks();

  abstract class TickTask {
    final String name;
    final long delay;

    public TickTask(String name, long delay) {
      this.name = name;
      this.delay = delay;
    }

    public abstract void tick(long ctime);
  }
}
