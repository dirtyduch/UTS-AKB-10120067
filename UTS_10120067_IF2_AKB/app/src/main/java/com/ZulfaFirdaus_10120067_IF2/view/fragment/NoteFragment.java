package com.ZulfaFirdaus_10120067_IF2.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ZulfaFirdaus_10120067_IF2.view.activity.AddNoteActivity;
import com.ZulfaFirdaus_10120067_IF2.view.activity.MainActivity;
import com.ZulfaFirdaus_10120067_IF2.R;
import com.ZulfaFirdaus_10120067_IF2.adapter.NoteAdapter;
import com.ZulfaFirdaus_10120067_IF2.model.Note;
import com.ZulfaFirdaus_10120067_IF2.database.DatabaseHelper;
import com.ZulfaFirdaus_10120067_IF2.NoteInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * NAMA    : Zulfa Firdaus
 * NIM     : 10120067
 * Kelas   : IF-2
 * MataKuliah : Aplikasi Komputasi Bergerak
 * Tugas Pengganti UTS Aplikasi Komputasi Bergerak
 */
public class NoteFragment extends Fragment  {

    private MainActivity mainActivity;
    private List<Note> note;
    private NoteInterface noteInterface;
    private RecyclerView recyclerView;
    private com.ZulfaFirdaus_10120067_IF2.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_note, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().hide();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mynote);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNoteActivity.class));
        });

        read();
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    private void read() {
        note = new ArrayList<Note>();
        noteInterface = new DatabaseHelper(getContext());
        Cursor cursor = noteInterface.read();
        if (cursor.moveToFirst()){
            do {
                Note n = new Note(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                note.add(n);
            } while (cursor.moveToNext());
        }

        noteAdapter = new NoteAdapter(note);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }
}

/**
 * NAMA    : Zulfa Firdaus
 * NIM     : 10120067
 * Kelas   : IF-2
 * MataKuliah : Aplikasi Komputasi Bergerak
 * Tugas Pengganti UTS Aplikasi Komputasi Bergerak
 */