package com.example.todo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private TodoDataSource dataSource;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private EditText taskEditText;
    private Button addButton;
    private boolean isEditMode = false;
    private long editTaskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new TodoDataSource(this);
        dataSource.open();
        taskEditText = findViewById(R.id.taskEditText);
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerView);
        addButton.setOnClickListener(v -> {
            String task = taskEditText.getText().toString();
            if (task.trim().equals("")) {
                Toast.makeText(MainActivity.this, "Task can't be empty",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEditMode) {
                if (editTaskId != -1) {
                    dataSource.updateTask(editTaskId, task);
                    refreshRecyclerView();
                    taskEditText.setText("");
                    addButton.setText("Add");
                    isEditMode = false;
                    editTaskId = -1;
                }
            } else {
                dataSource.insertTask(task);
                refreshRecyclerView();
                taskEditText.setText("");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TodoAdapter(this, dataSource.getAllTasks());
        adapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                Todo taskToEdit = adapter.getItemAtPosition(position);
                taskEditText.setText(taskToEdit.getTask());
                addButton.setText("Update");
                isEditMode = true;
                editTaskId = taskToEdit.getId();
            }

            @Override
            public void onDeleteClick(int position) {
                Todo taskToDelete = adapter.getItemAtPosition(position);
                dataSource.deleteTask(taskToDelete.getId());
                refreshRecyclerView();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void refreshRecyclerView() {
        adapter.updateTasks(dataSource.getAllTasks());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}