package co.edu.eci.ieti.android.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.edu.eci.ieti.android.dao.TaskDao;
import co.edu.eci.ieti.android.model.Task;
import co.edu.eci.ieti.android.roomdb.TaskRoomDatabase;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public void insert(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void deleteAll(){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.deleteAll();
        });

    }
}
