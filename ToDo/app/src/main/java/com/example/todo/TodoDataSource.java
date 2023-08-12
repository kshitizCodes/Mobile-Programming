package com.example.todo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public TodoDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertTask(String task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TASK, task);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public List<Todo> getAllTasks() {
        List<Todo> tasks = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Todo task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    @SuppressLint("Range")
    private Todo cursorToTask(Cursor cursor) {
        Todo task = new Todo();

        task.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));

        task.setTask(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK)));
        return task;
    }

    public void updateTask(long id, String newTask) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TASK, newTask);
        database.update(DatabaseHelper.TABLE_NAME, values,
                DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID +
                " = ?", new String[]{String.valueOf(id)});
    }
}
