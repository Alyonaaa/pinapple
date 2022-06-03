package com.example.pinapple_.screens.addtask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.pinapple_.R;
import com.example.pinapple_.databinding.FragmentAddTaskBinding;
import com.example.pinapple_.model.Subject;
import com.example.pinapple_.model.Task;
import com.example.pinapple_.screens.tasks.TasksViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTaskFragment extends Fragment {
    private FragmentAddTaskBinding binding;
    private TasksViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddTaskBinding.inflate(inflater);

        AddTaskFragmentArgs args = AddTaskFragmentArgs.fromBundle(requireArguments());

        viewModel = new ViewModelProvider(this).get(TasksViewModel.class);

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                getSubjects()
        );
        binding.subjectsList.setAdapter(adapter);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(args.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.calendarView.setDate(date != null ? date.getTime() : Calendar.getInstance().getTimeInMillis());

        binding.calendarView.setOnDateChangeListener((view, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            view.setDate(calendar.getTimeInMillis(), true, true);
        });

        binding.addButton.setOnClickListener((v) -> createTask());

        binding.cancelButton.setOnClickListener((v) -> navigateUp());

        return binding.getRoot();
    }

    private void navigateUp() {
        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

    private String  dateFormat(long ms) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(ms);
    }

    private void createTask() {
        Task task = new Task(
                dateFormat(binding.calendarView.getDate()),
                binding.subjectsList.getSelectedItem().toString(),
                binding.text.getText().toString()
        );
        viewModel.insert(task);
        navigateUp();
    }

    private List<Subject>  getSubjects() {
        List<Subject> list = new ArrayList<>();
        list.add(new Subject("Математика"));
        list.add(new Subject("Русский"));
        list.add(new Subject("Физика"));
        list.add(new Subject("Английский язык"));
        list.add(new Subject("Французский язык"));
        list.add(new Subject("Китайский язык"));
        list.add(new Subject("Алгебра"));
        list.add(new Subject("Геометрия"));
        list.add(new Subject("Биология"));
        list.add(new Subject("История"));
        list.add(new Subject("Физкультура"));
        list.add(new Subject("Основы нравственности"));
        list.add(new Subject("Литература"));
        list.add(new Subject("ОБЖ"));
        list.add(new Subject("ОРКСР"));
        list.add(new Subject("Краеведение"));
        list.add(new Subject("Информатика"));
        list.add(new Subject("Химия"));
        list.add(new Subject("Родной русский язык"));
        list.add(new Subject("Родная литература"));
        list.add(new Subject("Музыка"));
        list.add(new Subject("ИЗО"));
        list.add(new Subject("Черчение"));
        list.add(new Subject("Труды"));
        list.add(new Subject("Технология"));
        list.add(new Subject("Чтение"));
        list.add(new Subject("Обществознание"));
























        return list;
    }
}
