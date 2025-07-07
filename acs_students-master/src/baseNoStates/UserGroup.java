package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import baseNoStates.Schedule;
import java.util.ArrayList;

//Represents a group of users with associated permissions to access areas, schedules and actions
public class UserGroup {
  private final String nameGroup;
  private ArrayList<User> users = new ArrayList<>();
  private ArrayList<String> actions = new ArrayList<>();
  private ArrayList<Area> areas = new ArrayList<>();
  private Schedule schedule;

  public UserGroup(String groupName){
    this.nameGroup = groupName;
  }

  public ArrayList<User> getUsers() {
    return this.users;
  }

  public void setActions(ArrayList<String> actions) {
    this.actions = actions;
  }

  public void setSpaces(ArrayList<Area> spaces) {
    this.areas = spaces;
  }

  public void setSchedule(Schedule s) {
    this.schedule = s;
  }

  public void addUser(User user) {
    users.add(user);
  }

  public boolean isWithinSchedule(LocalDateTime dateTime){
    if (schedule != null) {
      return schedule.isWithin(dateTime);
    } else {
      return false;
    }
  }

  public boolean canDoAction(String action){
    return actions.contains(action);
  }

  public boolean canAccess(Area area) {
    String id = area.getName();
    for (Area a : areas) {
      Area find = a.findAreaById(id);
      if (find != null) {
        return true;
      }
    }
    return false;
  }
}