package baseNoStates;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.DayOfWeek;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//DirectoryUserGroups class represents a directory of user groups, organizing their permissions and schedules
//assigns specific users, defines their permitted actions, accessible spaces, and schedules

public final class DirectoryUserGroups {
  private static ArrayList<UserGroup> usersGroups = new ArrayList<>();

  static Logger logger = LoggerFactory.getLogger("baseNoStates.DirectoryUserGroups");

  public static void makeUserGroup() {

    ArrayList<String> allActions = new ArrayList<>(Arrays.asList(Actions.LOCK,Actions.UNLOCK,Actions.UNLOCK_SHORTLY,Actions.CLOSE,Actions.OPEN));

    Area accesBuilding = DirectoryAreas.findAreaById("building");
    Area floor1 = DirectoryAreas.findAreaById("floor1");
    Area exterior = DirectoryAreas.findAreaById("exterior");
    Area stairs = DirectoryAreas.findAreaById("stairs");
    Area groundFloor = DirectoryAreas.findAreaById("ground_floor");

    ArrayList<DayOfWeek> monToFri = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));

    ArrayList<DayOfWeek> monToSat = (ArrayList<DayOfWeek>) monToFri.clone();
    monToSat.add(DayOfWeek.SATURDAY);

    ArrayList<DayOfWeek> everyday = (ArrayList<DayOfWeek>) monToSat.clone();
    everyday.add(DayOfWeek.SUNDAY);

    // blank user group: users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back permissions later

    UserGroup blank = new UserGroup("blank");
    User bernat = new User("Bernat", "12345", blank);
    User blai = new User("Blai", "77532", blank);

    // employees user group:
    // set a schedule for their access within a specified time frame (from September 1st, 2023, to March 1st, 2024,
    // during weekdays from 9 AM to 5 PM).

    UserGroup employees = new UserGroup("employees");
    User ernest = new User("Ernest", "74984", employees);
    User eulalia = new User("Eulalia", "43295", employees);

    ArrayList<String> employeeActions = new ArrayList<>(Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE));
    employees.setActions(employeeActions);

    ArrayList<Area> employee_spaces = new ArrayList<>(Arrays.asList(groundFloor,floor1,exterior,stairs));
    employees.setSpaces(employee_spaces);

    LocalDate dayFrom = LocalDate.of(2023,9,1);// (yyyy,mm,dd)
    LocalDate dayTo = LocalDate.of(2024,3,1);
    LocalTime hourFrom = LocalTime.of(9,0);//24h
    LocalTime hourTo = LocalTime.of(17,0);

    Schedule employeeSchedule = new Schedule(dayFrom,dayTo,monToFri,hourFrom,hourTo);
    employees.setSchedule(employeeSchedule);

    // managers user group:
    // sets a schedule from September 1st, 2023, to March 1st, 2024, allowing access for managers
    // on weekdays and Saturdays, from 8 AM to 8 PM.

    UserGroup managers = new UserGroup("managers");
    User manel = new User("Manel", "95783", managers);
    User marta = new User("Marta", "05827", managers);

    managers.setActions(allActions);

    ArrayList<Area> managersSpaces = new ArrayList<>(Arrays.asList(accesBuilding));
    managers.setSpaces(managersSpaces);

    LocalDate dayFromMan = LocalDate.of(2023,9,1);
    LocalDate dayToMan = LocalDate.of(2024,3,1);
    LocalTime hourFromMan = LocalTime.of(8,0);
    LocalTime hourToMan = LocalTime.of(20,0);

    Schedule managerSchedule = new Schedule(dayFromMan,dayToMan,monToSat,hourFromMan,hourToMan);
    managers.setSchedule(managerSchedule);

    // admin user group
    // sets a schedule from January 1st, 2023, to January 1st, 2100, granting access every day,
    // allowing admin privileges 24/7.

    UserGroup admin = new UserGroup("admin");
    User ana = new User("Ana", "11343", admin);

    admin.setActions(allActions);

    ArrayList<Area> adminSpaces = new ArrayList<>(Arrays.asList(accesBuilding));
    admin.setSpaces(adminSpaces);

    LocalDate dayFromAdmin = LocalDate.of(2023,1,1);//2023
    LocalDate dayToAdmin = LocalDate.of(2100,1,1);//2100
    LocalTime hourFromAdmin = LocalTime.of(0,0);
    LocalTime hourToAdmin = LocalTime.of(23,59);

    Schedule adminSchedule = new Schedule(dayFromAdmin,dayToAdmin,everyday,hourFromAdmin,hourToAdmin);
    admin.setSchedule(adminSchedule);

    usersGroups = new ArrayList<>(Arrays.asList(admin, employees, blank, managers));
  }

  public static User findUserByCredential(String credential) {
    for (int i = 0; i < usersGroups.size(); i++) {
      for (int j = 0; j < usersGroups.get(i).getUsers().size(); j++)
        if (usersGroups.get(i).getUsers().get(j).getCredential().equals(credential)) {
          return usersGroups.get(i).getUsers().get(j);
        }
    }
    //System.out.println("user with credential " + credential + " not found");
    logger.error("User with credential " + credential + " not found");
    return null;
  }
}
