package com.example.notebook;


import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List <Note>> getAllNOtes();

    @Query("DELETE FROM note_table")
    void deleteAll();


}
