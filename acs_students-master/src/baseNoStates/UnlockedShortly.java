package baseNoStates;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//ConcreteState of the State pattern.
//It Unlocks the door during a short period of time and then it Locks itself.
//If the door is opened when it tries to Lock, it changes to propped state.

public class UnlockedShortly extends DoorState implements Observer{

  private final LocalDateTime dateTimeUnlocked;
  private static final int MAXIMUM_TIME_UNLOCKED = 10;

  //a unique clock is shared by all UnlockedShortly states
  public Clock clock = Clock.getInstance();

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.UnlockedShortly");

  public UnlockedShortly(Door d, LocalDateTime now){
    stateName = UNLOCKED_SHORTLY;
    door = d;
    dateTimeUnlocked = now; //time state has changed
    clock.addObserver(this);
  }

  public void update(Observable observable, Object object){
    LocalDateTime now = (LocalDateTime) object;

    Duration duration = Duration.between(dateTimeUnlocked, now);
    long timePassed = Math.abs(duration.toSeconds());

    //TODO Arreglar el unlocked shortly, cuando se ejecuta una vez pasan los 10 segundos
    //TODO sigue entrando en esta función y printando loggers en bucle, aunque el estado de la puerta cambie.
    //TODO Estube probando y tampoco funciona muy bien la puerta después de volver a estado Lock.
    if (timePassed > MAXIMUM_TIME_UNLOCKED){
      //System.out.println("Door " + this.door.getDoorId() + " closed = " + door.isClosed()
        //+ " more than " + MAXIMUM_TIME_UNLOCKED + " seconds unlocked");
      //TODO este comentario está mal, es muy raro.
      logger.info("Door " + this.door.getDoorId() + " closed = " + door.isClosed()
          + " more than " + MAXIMUM_TIME_UNLOCKED + " seconds unlocked");
      if (door.isClosed()){
        //System.out.println("Door " + this.door.getDoorId() + " leaves Unlocked shortly and goes Locked");
        logger.info("Door " + this.door.getDoorId() + " leaves Unlocked shortly and goes Locked");
        door.setState(new Lock(door));
      } else {
        //System.out.println("Door " + this.door.getDoorId() + " leaves Unlocked shortly and goes Propped");
        //clock.deleteObserver(this);
        logger.info("Door " + this.door.getDoorId() + " leaves Unlocked shortly and goes Propped");
        door.setState(new Propped(door));
      }
    }
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door " + this.door.getDoorId() + " opened");
    }
    else
      //System.out.println("Can't open door " + door.getDoorId() + " because it's already open");
      logger.warn("Can't open door " + this.door.getDoorId() + " because it's already open");
  }

  @Override
  public void close() {
    if (door.isClosed())
      //System.out.println("Can't close door " + door.getDoorId() + " because it's already close");
      logger.warn("Can't close door " + this.door.getDoorId() + " because it's already closed");
    else {
      logger.info("Door " + this.door.getDoorId() + " closed");
      door.setClosed(true);
    }
  }

  @Override
  public void lock(){
    door.setState(new Lock(door));
    logger.info("Door " + this.door.getDoorId() + " locked");
  }

  @Override
  public void unlockShortly() {
    //System.out.println("Can't unlock door " + door.getDoorId() + " because it's already unlocked");
    logger.warn("Can't unlock door " + this.door.getDoorId() + " because it's already unlocked");
  }

}
