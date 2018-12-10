package com.example.hania.voiceassistant.dao;

public interface INoteSchema {
    String NOTE_TABLE = "note";
    String COLUMN_ID = "_id";
    String COLUMN_CONTENT = "content";
    String NOTE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + NOTE_TABLE
            + " ("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_CONTENT
            + " TEXT NOT NULL)";

    String [] NOTE_COLUMNS = new String[] { COLUMN_ID,
            COLUMN_CONTENT};
}
