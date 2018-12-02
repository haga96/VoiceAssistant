package com.example.hania.voiceassistant.dao;

public interface IRequestSchema {
    String REQUEST_TABLE = "request";
    String COLUMN_ID = "_id";
    String COLUMN_TEXT = "text";
    String COLUMN_ACTION = "action";
    String REQUEST_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + REQUEST_TABLE
            + " ("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_TEXT
            + " TEXT NOT NULL, "
            + COLUMN_ACTION
            + " TEXT NOT NULL)";

    String [] REQUEST_COLUMNS = new String[] { COLUMN_ID,
    COLUMN_TEXT, COLUMN_ACTION};

}
