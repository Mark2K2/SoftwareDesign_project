package baseNoStates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class ClockNoSingleton extends Observable {
  //podem tenir més d'una instancia d'aquest rellotge

  private LocalDateTime date;
  private Timer timer;

  public ClockNoSingleton() {
    timer = new Timer();
  }

  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() {
        date = LocalDateTime.now();
        System.out.println("run() executed at " + date);
        setChanged();
        notifyObservers(date); // Notificar a los observadores con la fecha actual
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 10000); // Cambiado a un valor fijo
  }

  public void stop() {
    timer.cancel();
  }

  public LocalDateTime getDate() {
    return date;
  }
}