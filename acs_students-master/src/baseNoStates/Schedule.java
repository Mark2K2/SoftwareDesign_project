package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

//Schedule class represents a specified time frame

public class Schedule {
  private final LocalDate fromDate;
  private final LocalDate toDate;
  private final ArrayList<DayOfWeek> daysOfWeek;
  private final LocalTime fromHour;
  private final LocalTime toHour;

  public Schedule(LocalDate fromDate, LocalDate toDate, ArrayList<DayOfWeek> daysOfWeek, LocalTime fromHour, LocalTime toHour){
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.daysOfWeek = daysOfWeek;
    this.fromHour = fromHour;
    this.toHour = toHour;
  }

  private boolean isSomeDay(DayOfWeek day){
    return daysOfWeek.contains(day);
  }

  public boolean isWithin(LocalDateTime date) {
    return ((fromDate.isBefore(date.toLocalDate())) && toDate.isAfter(date.toLocalDate())
        && isSomeDay(date.toLocalDate().getDayOfWeek()) && date.toLocalTime().isAfter(fromHour)
        && date.toLocalTime().isBefore(toHour));
  }

  @Override
  public String toString(){
    return "Schedule{"
        + "fromDate=" + fromDate
        + ", toDate=" + toDate
        + ", daysOfWeek=" + daysOfWeek
        + ", fromHour=" + fromHour
        + ", toHour=" + toHour
        + "}";

  }
}
