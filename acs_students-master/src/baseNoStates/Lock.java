package baseNoStates;
import java.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


//Concrete state of the State pattern.

public class Lock extends DoorState{

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Lock");


  public Lock(Door d) {
    this.stateName = LOCKED;
    this.door = d;
  }

  @Override
  public void open() {
    logger.info("Trying to open door " + door.getDoorId() + "...");
    logger.warn("Can't open door " + door.getDoorId() + " because it's locked");
    //System.out.println("Can't open door " + door.getDoorId() + " because it's locked");
  };

  @Override
  public void close() {
    logger.warn("Can't close door " + door.getDoorId() + " because it's already close");
    //System.out.println("Can't close door " + door.getDoorId() + " because it's already close");
  };

  @Override
  public void lock() {
    logger.warn("Can't lock door " + door.getDoorId() + " because it's already locked");
    //System.out.println("Can't lock door " + door.getDoorId() + " because it's already locked");
  }

  @Override
  public void unlock() {
    door.setState(new Unlock(door));
    logger.info("Door " + door.getDoorId() + "unlocked");
  };

  @Override
  public void unlockShortly() {
    door.setState(new UnlockedShortly(door, LocalDateTime.now()));
    logger.info("Door " + door.getDoorId() + "shortly unlocked");
  }
}
