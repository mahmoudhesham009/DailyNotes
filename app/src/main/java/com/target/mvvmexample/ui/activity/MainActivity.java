package com.target.mvvmexample.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.target.mvvmexample.R;
import com.target.mvvmexample.adapters.NoteRecyclerViewAdapter;
import com.target.mvvmexample.adapters.OnItemClicked;
import com.target.mvvmexample.data.enititys.Note;
import com.target.mvvmexample.ui.veiwModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, OnItemClicked {
    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    ArrayList<Note> mNoteArrayList = new ArrayList<>();
    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    NoteRecyclerViewAdapter mNoteRecyclerViewAdapter;
    List<Note> mRoomList;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.ToolBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mNoteRecyclerViewAdapter = new NoteRecyclerViewAdapter(mNoteArrayList, this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mNoteRecyclerViewAdapter);

        mToolbar.setOnMenuItemClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init(this);
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mNoteArrayList.clear();
                mNoteArrayList.addAll(notes);
                mNoteRecyclerViewAdapter.notifyDataSetChanged();
                mRoomList = notes;
            }
        });

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                viewModel.deleteAll(mRoomList);
                break;

            case R.id.add:
                startActivity(new Intent(this, NewNoteActivity.class));
        }
        return false;
    }

    @Override
    public void onItemClick(int position) {
        Note selectedNote = mNoteArrayList.get(position);
        Intent intent = new Intent(this, NoteDetails.class);
        intent.putExtra("title", selectedNote.getName());
        intent.putExtra("body", selectedNote.getBody());
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Do you wanna delete this note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteNote(mNoteArrayList.get(position));
                    }
                })
                .setNegativeButton("Cancle", null)
                .create().show();
    }
}