package com.example.hania.voiceassistant.dao;

public interface IArgsSchema {

    String ARGS_TABLE = "args";
    String REQUEST_TABLE = "request";
    String COLUMN_ARG_ID = "_id";
    String COLUMN_REQUEST_ID = "request_id";
    String COLUMN_ARG = "argContent";
    String COLUMN_TYPE = "type";

    String ARG_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + ARGS_TABLE
            + " ("
            + COLUMN_ARG_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_REQUEST_ID
            + " INTEGER FOREGIN KEY REFERENCES "
            + REQUEST_TABLE
            + " ("
            + COLUMN_REQUEST_ID
            + ") ON DELETE CASCADE ON UPDATE NO ACTION, "
            + COLUMN_ARG
            + " TEXT NOT NULL, "
            + COLUMN_TYPE
            + " TEXT NOT NULL)";

    String[] ARG_COLUMNS = new String[] {COLUMN_ARG_ID, COLUMN_REQUEST_ID,
            COLUMN_ARG, COLUMN_TYPE};
}
