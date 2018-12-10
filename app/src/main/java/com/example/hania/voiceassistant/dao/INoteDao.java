package com.example.hania.voiceassistant.dao;

import java.util.List;

public interface INoteDao {
    Note fetchNoteById(int noteId);
    List<Note> fetchAllNotes();
    boolean addNote(Note note);
}

