package co.edu.eci.ieti.android.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.edu.eci.ieti.android.model.Task;
import co.edu.eci.ieti.android.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;

    private final LiveData<List<Task>> allTasks;

    public TaskViewModel (Application application){
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() { return allTasks; }

    public void insert(Task task) { taskRepository.insert(task); }
}
