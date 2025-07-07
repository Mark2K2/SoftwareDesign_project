package baseNoStates;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

//Clock is based in two design pattern. 
//First we are applying the singleton pattern to allow only a single instance for this class
//Also it is the observable object of the pattern Observer

public final class Clock extends Observable {

  private LocalDateTime date;
  private Timer timer;
  private int period; //seconds

  private static Clock instance = null;

  static Logger logger = LoggerFactory.getLogger("baseNoStates.Observable.Clock"); //Observable.Clock o .Clock a secas??

  private Clock() {
    this.period = 1;
    this.timer = new Timer();
    start();
  }

  public static Clock getInstance() {
    if (instance == null) {
      instance = new Clock();
    }
    return instance;
  }

  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() {
        date = LocalDateTime.now();
        //System.out.println("run() executed at " + date);
        logger.info("run() executed at " + date);

        setChanged();
        notifyObservers(date);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
  }
  public void stop() {
    timer.cancel();
  }
  public int getPeriod() {
    return period;
  }
  public LocalDateTime getDate() {
    return date;
  }

}
