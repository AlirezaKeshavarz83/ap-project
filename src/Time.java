import java.io.Serializable;
import java.time.LocalDateTime;
public class Time implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    Time(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    Time(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = 0;
        this.minute = 0;
    }
    public String getDateString(){
        return ("" + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day));
    }
    public String getDateTimeString(){
        return ("" + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }
    public static Time now(){
        var dt = LocalDateTime.now();
        return new Time(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute());
    }
}
