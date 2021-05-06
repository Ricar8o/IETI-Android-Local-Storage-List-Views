package co.edu.eci.ieti.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.eci.ieti.R;
import co.edu.eci.ieti.android.model.Task;

public class TasksAdapter
        extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder>
{

    List<Task> taskList = null;

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType )
    {
        return new TaskViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.task_row, parent, false ) );
    }

    @Override
    public void onBindViewHolder( @NonNull TaskViewHolder holder, int position )
    {
        Task task = taskList.get( position );
        holder.bind(task.toString());
    }

    @Override
    public int getItemCount()
    {
        return taskList != null ? taskList.size() : 0;
    }

    public void updateTasks(List<Task> tasks){
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        private final TextView taskItemView;

        TaskViewHolder( @NonNull View itemView ) {
            super( itemView );
            taskItemView = itemView.findViewById(R.id.textView);
        }
        public void bind(String text) {
            taskItemView.setText(text);
        }

        public static TaskViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_row, parent, false);
            return new TaskViewHolder(view);
        }


    }

}