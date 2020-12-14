package com.target.mvvmexample.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.target.mvvmexample.ui.veiwModel.NewNoteActivityViewModel;
import com.target.mvvmexample.R;
import com.target.mvvmexample.data.enititys.Note;

public class NewNoteActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    NewNoteActivityViewModel viewModel;
    Toolbar toolbar;
    EditText name,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        name=findViewById(R.id.NoteName);
        body=findViewById(R.id.NoteBody);

        toolbar=findViewById(R.id.ToolBar);
        viewModel= new ViewModelProvider(this).get(NewNoteActivityViewModel.class);
        viewModel.init(this);

        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Note n =new Note(name.getText().toString(),body.getText().toString());
        viewModel.addNote(n);
        finish();
        return false;
    }
}