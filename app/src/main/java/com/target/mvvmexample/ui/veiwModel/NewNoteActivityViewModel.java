package com.target.mvvmexample.ui.veiwModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.target.mvvmexample.data.Repository;
import com.target.mvvmexample.data.enititys.Note;

public class NewNoteActivityViewModel extends ViewModel {
    Context context;

    public void init(Context context){
        this.context=context;
    }

    public void addNote(Note n) {
        Repository.getInstance().addNote(n);
    }
}
