package com.target.mvvmexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.target.mvvmexample.R;
import com.target.mvvmexample.data.enititys.Note;

import java.util.ArrayList;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {
    ArrayList<Note> noteArrayList;
    public OnItemClicked onItemClicked;

    public NoteRecyclerViewAdapter(ArrayList<Note> notes, OnItemClicked onItemClicked) {
        noteArrayList = notes;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteName.setText(noteArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView noteName;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.note_name);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClicked.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemClicked.onLongItemClick(getAdapterPosition());
            return true;
        }
    }
}
