package com.target.mvvmexample.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.target.mvvmexample.data.enititys.Note;

@Database(entities = Note.class,version = 1, exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    public static NoteDataBase instance;
    public synchronized static NoteDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"Room Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


    public abstract NotesDao notesDao();
}
