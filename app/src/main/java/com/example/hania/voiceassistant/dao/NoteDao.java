package com.example.hania.voiceassistant.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class NoteDao extends DbContentProvider
        implements INoteSchema, INoteDao {
    private Cursor cursor;
    private ContentValues initialValues;

    public NoteDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Note fetchNoteById(int noteId) {
        final String selectionArgs[] = {String.valueOf(noteId)};
        final String selection = ID + " = ?";
        Note note = new Note();
        cursor = super.query(NOTE_TABLE, NOTE_COLUMNS, selection, selectionArgs, COLUMN_ID);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                note = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return note;
    }

    @Override
    public List<Note> fetchAllNotes() {
        List<Note> noteList = new ArrayList<Note>();
        cursor = super.query(NOTE_TABLE, NOTE_COLUMNS, null, null, COLUMN_ID);

        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Note note = cursorToEntity(cursor);
                noteList.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return noteList;
    }

    @Override
    public boolean addNote(Note note) {
        setContentValue(note);
        try {
            return super.insert(NOTE_TABLE, getContentValue()) > 0;
        }catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    private void setContentValue(Note note) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ID, note.noteId);
        initialValues.put(COLUMN_CONTENT,note.content);
    }

    @Override
    protected Note cursorToEntity(Cursor cursor) {

        Note note = new Note();

        int idIndex;
        int contentIndex;

        if(cursor != null){
            if (cursor.getColumnIndex(COLUMN_ID) != -1){
                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                note.noteId = cursor.getInt(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_CONTENT) != -1) {
                contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT);
                note.content = cursor.getString(contentIndex);
            }
        }
        return note;
    }
}

