package teamup.helpers.obj;

public class Calendar {

    private final String calendarName;

    public Calendar(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getName() {
        return calendarName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calendar calendar = (Calendar) o;

        return calendarName != null ? calendarName.equals(calendar.calendarName) : calendar.calendarName == null;
    }

    @Override
    public int hashCode() {
        return calendarName != null ? calendarName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarName='" + calendarName + '\'' +
                '}';
    }
}