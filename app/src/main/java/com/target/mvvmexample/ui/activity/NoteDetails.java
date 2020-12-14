package com.target.mvvmexample.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.target.mvvmexample.R;

public class NoteDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        toolbar = findViewById(R.id.ToolBar);
        textView = findViewById(R.id.NoteBody);

        toolbar.setTitle(getIntent().getStringExtra("title"));
        textView.setText(getIntent().getStringExtra("body"));
    }
}