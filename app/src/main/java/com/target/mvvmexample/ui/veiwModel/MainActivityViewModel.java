package com.target.mvvmexample.ui.veiwModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.mvvmexample.data.Repository;
import com.target.mvvmexample.data.enititys.Note;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    LiveData<List<Note>> noteList;
    Context context;

    public void init(Context context) {
        this.context = context;
        noteList = Repository.getInstance().getNotesLiveData(context);
    }

    public LiveData<List<Note>> getNotes() {
        return noteList;
    }


    public void deleteAll(List<Note> n) {
        Repository.getInstance().deleteAllNote(n);
        noteList = Repository.getInstance().getNotesLiveData(context);
    }

    public void deleteNote(Note n) {
        Repository.getInstance().deleteNote(n);
        noteList = Repository.getInstance().getNotesLiveData(context);
    }
}
