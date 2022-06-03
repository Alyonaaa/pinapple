package com.example.pinapple_.screens.tasks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinapple_.App;
import com.example.pinapple_.R;
import com.example.pinapple_.databinding.FragmentTasksBinding;
import com.example.pinapple_.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TasksFragment extends Fragment {
    TasksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTasksBinding binding = FragmentTasksBinding.inflate(inflater);

        TasksFragmentArgs args = TasksFragmentArgs.fromBundle(requireArguments());

        viewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        TaskAdapter adapter = new TaskAdapter(viewModel);

        viewModel.getTasksByDate(args.getDate()).observe(getViewLifecycleOwner(), (tasks) -> {
            adapter.setData(tasks);
        });
        binding.taskList.setAdapter(adapter);
        binding.addTaskButton.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(TasksFragmentDirections.actionTasksFragmentToAddTaskFragment(args.getDate()));
        });

        return binding.getRoot();
    }
}