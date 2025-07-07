package baseNoStates;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Represents people that will interact with our system
public class User {
  private final String name;
  private final String credential;
  private final UserGroup userGroup;

  public User(String name, String credential, UserGroup group) {
    this.name = name;
    this.credential = credential;
    this.userGroup = group;
    userGroup.addUser(this);
  }

  public String getCredential() {
    return this.credential;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return "User{name=" + this.name + ", credential=" + this.credential + "}";
  }

  public boolean canBeInSpace(Area space) {
    return userGroup.canAccess(space); }

  public boolean canDoAction(String action) {
    return userGroup.canDoAction(action);
  }

  public boolean canSendRequests(LocalDateTime time) {
    return userGroup.isWithinSchedule(time);
  }

}
