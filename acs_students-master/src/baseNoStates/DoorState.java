package baseNoStates;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//State pattern base. It contains all the different States a door can have

public abstract class DoorState {

  public final static String LOCKED = "locked";
  public final static String UNLOCKED = "unlocked";
  public final static String UNLOCKED_SHORTLY = "unlocked_shortly";
  public final static String PROPPED = "propped";
  protected String stateName;
  protected Door door;

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState");

  public String getStateName() {
    return stateName;
  }

  public void open() {};
  public void close() {};
  public void lock() {}
  public void unlock() {};
  public void unlockShortly() {};

}
