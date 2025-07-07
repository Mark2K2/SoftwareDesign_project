package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//DirectoryAreas class inicialize the areas and creates the tree structure of them
public class DirectoryAreas {
  private static Area rootArea;
  private static ArrayList<Door> allDoors;

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryAreas");

  public static void makeAreas() {

    Space stairs = new Space("stairs");
    Space exterior = new Space("exterior");

    Space parking = new Space("parking");
    Door d1 = new Door("D1", exterior, parking);
    Door d2 = new Door("D2", stairs, parking);
    Partition basement = new Partition("basement", new ArrayList<>(Arrays.asList(parking)));

    Space hall = new Space("hall");
    Space room1 = new Space("room1");
    Space room2 = new Space("room2");
    Door d3 = new Door("D3", exterior, hall);
    Door d4 = new Door("D4", stairs, hall);
    Door d5 = new Door("D5", hall, room1);
    Door d6 = new Door("D6", hall, room2);
    Partition groundFloor = new Partition("ground_floor", new ArrayList<>(Arrays.asList(hall,room1,room2)));

    Space room3 = new Space("room3");
    Space corridor = new Space("corridor");
    Space IT = new Space("IT");
    Door d7 = new Door("D7", stairs, corridor);
    Door d8 = new Door("D8", corridor, room3);
    Door d9 = new Door("D9", corridor, IT);
    Partition floor1 = new Partition("floor1", new ArrayList<>(Arrays.asList(room3,corridor,IT)));

    Partition building = new Partition("building", new ArrayList<>(Arrays.asList(basement, groundFloor, floor1, stairs, exterior)));

    rootArea = building;

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  public static Area findAreaById(String id) {
    return rootArea.findAreaById(id);
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getDoorId().equals(id))
        return door;
    }
    //System.out.println("door with id " + id + " not found");
    logger.error("Door with id " + id + " not found");
    return null;
  }

  public static ArrayList<Door> getAllDoors() {
    //System.out.println(allDoors);
    logger.info("Doors: " + allDoors);
    return allDoors;
  }
}