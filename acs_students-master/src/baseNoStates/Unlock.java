package baseNoStates;
import java.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//Concrete state of the State pattern.

public class Unlock extends DoorState {

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Unlock");

  public Unlock(Door d) {
    stateName = UNLOCKED;
    door = d;
  }
  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      logger.info("Door " + door.getDoorId() + "opened");
    }
    else {
      //System.out.println("Can't open door " + door.getDoorId() + " because it's already open");
      logger.warn("Can't open door " + door.getDoorId() + " because it's already open");
    }
  };

  @Override
  public void close() {
    if (door.isClosed()) {
      //System.out.println("Can't open door " + door.getDoorId() + " because it's already close");
      logger.warn("Can't close door " + door.getDoorId() + " because it's already closed");
    } else {
      door.setClosed(true);
      logger.info("Door " + door.getDoorId() + "closed");
    }
  };

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Lock(door));
      logger.info("Door " + door.getDoorId() + "locked");
    }
  }
  @Override
  public void unlock() {
    //System.out.println("Can't unlock door " + door.getDoorId() + " because it's already unlocked");
    logger.warn("Can't unlock door " + door.getDoorId() + " because it's already unlocked");
  }

  //TODO
  @Override
  public void unlockShortly() {
    //System.out.println("Can't unlock door " + door.getDoorId() + " because it's already unlocked");
    logger.warn("Can't unlock door " + door.getDoorId() + " because it's already unlocked");
  }
}
