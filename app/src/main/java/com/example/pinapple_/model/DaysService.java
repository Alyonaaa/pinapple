package com.example.pinapple_.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DaysService {
    public interface DaysListener {
        void onChange();
    }

    private List<Day> days = new ArrayList<>();
    private List<DaysListener> listeners = new ArrayList<>();

//    {
//        Calendar calendar = Calendar.getInstance();
//        days.add(new Day(calendar.getTimeInMillis()));
//    }

    public List<Day> getDays() {
        return days;
    }

    public void addDay(Day day) {
        days.add(day);
        notifyAllDataChanged();
    }

//    public void addDay(long date) {
//        this.addDay(new Day(days.size() + 1, date));
//    }

    public void addListener(DaysListener listener) {
        listeners.add(listener);
    }

    private void notifyAllDataChanged() {
        for (DaysListener l : listeners) {
            l.onChange();
        }
    }
}
