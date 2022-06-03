package com.example.pinapple_.screens.main;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple_.R;
import com.example.pinapple_.databinding.DayBinding;
import com.example.pinapple_.model.Day;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    List<Day> data;
    public void setData(List<Day> list) {
        data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DayBinding binding = DayBinding.inflate(inflater, parent, false);
        return new DaysViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {

        Day day = data.get(position);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(day.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTimeInMillis(date != null ? date.getTime() : 0);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        holder.binding.dayName.setText(getDayName(dayOfWeek));
        holder.binding.dayDate.setText(day.date);

        int textColor = (day.countDone < day.countTasks) ? Color.parseColor("#FF0000") : Color.parseColor("#00FF00");
        holder.binding.dayCountTasks.setTextColor(textColor);
        holder.binding.dayCountTasks.setText(String.format("%d/%d дз", day.countDone, day.countTasks));

        holder.binding.getRoot().setOnClickListener((v) -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(MainFragmentDirections.actionMainFragmentToTasksFragment(day.date));
        });
    }

    private String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY: return "Воскресенье";
            case Calendar.MONDAY: return "Понедельник";
            case Calendar.TUESDAY: return "Вторник";
            case Calendar.WEDNESDAY: return "Среда";
            case Calendar.THURSDAY: return "Четверг";
            case Calendar.FRIDAY: return "Пятница";
            case Calendar.SATURDAY: return "Суббота";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DaysViewHolder extends RecyclerView.ViewHolder {
        DayBinding binding;

        public DaysViewHolder(@NonNull DayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

