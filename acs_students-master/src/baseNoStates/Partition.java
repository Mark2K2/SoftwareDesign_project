package baseNoStates;

import java.util.ArrayList;

//Partition represents the composite class.
//This pattern allows the class to have subAreas (Areas) that can represent other Partitions or Spaces.

public class Partition extends Area{

  private ArrayList<Area> subAreas;

  public Partition(String id2, ArrayList<Area> areas){
    id = id2;
    subAreas = areas;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public Area findAreaById(String id2) {
    Area areaReturn = null;
    if(this.id.equals(id2)){
      return this;
    }
    for(Area area: subAreas){
      areaReturn = area.findAreaById(id2);
      if(areaReturn != null)
      {
        return areaReturn;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doorsAccess = new ArrayList<>();
    for (Area area : subAreas) {
      for (Door d : area.getDoorsGivingAccess()) {
        if (!doorsAccess.contains(d)) {
          doorsAccess.add(d);
        }
      }
    }
    return doorsAccess;
  }


  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : subAreas) {
      for (Space s : area.getSpaces()) {
        if (!spaces.contains(s)) {
          spaces.add(s);
        }
      }
    }
    return spaces;
  }

}
