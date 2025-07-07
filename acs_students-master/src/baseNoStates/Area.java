package baseNoStates;

//Area is a part of the composite pattern, it specially represents the component
//This class is created in order to represent the building as a tree structure.
//The building represents the root and their childs represents other Areas that can be Partitions or Spaces.

import java.util.ArrayList;

public abstract class Area {

  protected String id;

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public String getName() {
    return id;
  }

  public abstract Area findAreaById(String id2);

  public abstract ArrayList<Space> getSpaces();

}
