package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

//Spaces represents the leaf class of the composite pattern
//This class can't have children.

public class Space extends Area{

  private ArrayList<Door> doors ;

  public Space(String id2) {
    this.id = id2;
    this.doors = new ArrayList<>();
  }

  @Override
  public String getName() {
    return super.getName();
  }

  public void addDoorGivingAccess(Door d) {
    doors.add(d);
  }
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<Space>(Arrays.asList(this));
  }

  @Override
  public Area findAreaById(String id2) {
    if(this.id.equals(id2)){
      return this;
    }
    return null;
  }
}