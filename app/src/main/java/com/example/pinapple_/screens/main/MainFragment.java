package com.example.pinapple_.screens.main;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinapple_.App;
import com.example.pinapple_.R;
import com.example.pinapple_.databinding.AddDayBinding;
import com.example.pinapple_.databinding.FragmentMainBinding;
import com.example.pinapple_.screens.main.DaysAdapter;
import com.example.pinapple_.screens.tasks.TasksViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainFragment extends Fragment {
    TasksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        App app = (App)(getContext().getApplicationContext());
        viewModel = new ViewModelProvider(this).get(TasksViewModel.class);

        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater);

        DaysAdapter adapter = new DaysAdapter();
        viewModel.getDays().observe(getViewLifecycleOwner(), (data) -> {
            adapter.setData(data);
        });

        binding.dayList.setAdapter(adapter);
        binding.addTaskButton.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(MainFragmentDirections.actionMainFragmentToAddTaskFragment(""));
        });

        return binding.getRoot();
    }

    private void showDialog() {
        App app = (App)(getContext().getApplicationContext());

        AddDayBinding addDayBinding = AddDayBinding.inflate(getLayoutInflater());
        addDayBinding.calendar.setOnDateChangeListener((view, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            view.setDate(calendar.getTimeInMillis(), true, true);
        });

//        AlertDialog dialog = new AlertDialog.Builder(getContext())
//                .setTitle("Add day")
//                .setView(addDayBinding.getRoot())
//                .setPositiveButton("add", (a, b) -> {
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
//                    Toast.makeText(getContext(), dateFormat.format(addDayBinding.calendar.getDate()), Toast.LENGTH_SHORT).show();
//                    app.daysService.addDay(addDayBinding.calendar.getDate());
//                })
//                .create();
//        dialog.show();
    }
}