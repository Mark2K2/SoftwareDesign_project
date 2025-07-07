package baseNoStates;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//Propped is a special state.
// if the door is in unlocked shortly state, and it remains opened for more
// than 10 seconds, the door state is set to propped
// if is in propped state and is sent a close request, it turns to Locked

public class Propped extends DoorState{

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DoorState.Propped");

  public Propped(Door d) {
    stateName = PROPPED;
    door = d;
  }

  @Override
  public void open() {
    logger.warn("Can't open door " + door.getDoorId() + " because it's already opened");
  }

  @Override
  public void close() {
    if (!door.isClosed()){
      door.setClosed(true);
      door.setState(new Lock(door));
      logger.info("Door " + door.getDoorId() + " closed and automatically locked");
    }
  }
}
