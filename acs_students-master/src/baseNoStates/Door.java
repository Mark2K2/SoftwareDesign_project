package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

//Door is a class that divides physically two spaces and that has associated an space

public class Door {
  private final String doorId;
  private boolean closed; // physically
  private DoorState doorState;
  private final Space spaceFrom;
  private final Space spaceTo;


  public Door(String id, Space spaceFrom, Space spaceTo) {
    this.doorId = id;
    closed = true;
    doorState = new Lock(this);
    this.spaceFrom = spaceFrom;
    this.spaceTo = spaceTo;
    this.spaceTo.addDoorGivingAccess(this);
  }

  public Space getSpaceFrom() {
    return spaceFrom;
  }

  public Space getSpaceTo() {
    return spaceTo;
  }

  // Processes a request
  // it is the Door that process the request because the door has and knows
  // its state, and if closed or open
  public void processRequest(RequestReader request) {

    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  //Performs the requested action based on the door's current state
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        doorState.open();
        break;
      case Actions.CLOSE:
        doorState.close();
        break;
      case Actions.LOCK:
        doorState.lock();
        break;
      case Actions.UNLOCK:
        doorState.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        doorState.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getDoorId() {
    return doorId;
  }

  public String getStateName() {
    return doorState.getStateName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + doorId + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", doorId);
    json.put("state", getStateName());
    json.put("closed", closed);

    return json;
  }

  public void setState(DoorState state) {
    this.doorState = state;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }
}
