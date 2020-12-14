package com.target.mvvmexample.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.target.mvvmexample.data.enititys.Note;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface NotesDao {
    @Insert
    public Completable insertNote(Note note);

    @Query("select * from notes_table")
    public LiveData<List<Note>> getAllNotes();

    @Delete
    public Completable DeleteNote(Note n);

    @Delete
    public Completable deleteAllNote(List<Note> n);
}
