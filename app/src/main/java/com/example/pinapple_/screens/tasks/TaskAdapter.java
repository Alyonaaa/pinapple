package com.example.pinapple_.screens.tasks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple_.R;
import com.example.pinapple_.databinding.TaskBinding;
import com.example.pinapple_.model.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterHolder> {
    List<Task> data = new ArrayList<>();
    private TasksViewModel viewModel;

    TaskAdapter(TasksViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setData(List<Task> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskBinding binding = TaskBinding.inflate(inflater, parent, false);
        binding.taskIsDone.setOnClickListener((v) -> {
            Object tag = v.getTag(R.string.tagPosition);
            if (tag == null) {
                return;
            }

            boolean value = binding.taskIsDone.isChecked();
            Integer position = (Integer) tag;
            Log.d("mytag", position.toString());
            Task task = data.get(position);
            task.isDone = value;
            viewModel.update(task);
        });
        return new TaskAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapterHolder holder, int position) {
        Task task = data.get(position);
        holder.binding.position.setText(position + "");
        holder.binding.taskIsDone.setChecked(task.isDone);
        holder.binding.taskIsDone.setTag(R.string.tagPosition, new Integer(position));
        holder.binding.taskText.setText(task.text);
        holder.binding.taskSubject.setText(task.subject);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class TaskAdapterHolder extends RecyclerView.ViewHolder {
        TaskBinding binding;

        public TaskAdapterHolder(@NonNull TaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
//            this.binding.taskIsDone.setOnCheckedChangeListener((v, value) -> {
//                Integer position = Integer.parseInt(binding.position.getText().toString());
//                Log.d("mytag", position.toString());
//                Task task = data.get(position);
//                task.isDone = value;
//                viewModel.update(task);
//            });
        }
    }
}
