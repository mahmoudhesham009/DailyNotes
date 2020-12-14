package com.target.mvvmexample.data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.target.mvvmexample.data.enititys.Note;
import com.target.mvvmexample.data.local.NoteDataBase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    Context context;
    //singleton pattern
    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<List<Note>> getNotesLiveData(Context context) {
        this.context = context;
        return NoteDataBase.getInstance(context).notesDao().getAllNotes();
    }

    public void addNote(Note note) {
        NoteDataBase.getInstance(context).notesDao().insertNote(note)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Observer", "subscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Observer", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void deleteAllNote(List<Note> notes) {
        NoteDataBase.getInstance(context).notesDao().deleteAllNote(notes)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Observer", e.getLocalizedMessage());
                    }
                });
    }

    public void deleteNote(Note n) {
        NoteDataBase.getInstance(context).notesDao().DeleteNote(n)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}