package com.example.hania.voiceassistant.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class ArgDao extends DbContentProvider
        implements IArgsSchema, IArgDao{
    private Cursor cursor;
    private ContentValues initialValues;

    public ArgDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Arg fetchArgById(int argId) {
        final String selectionArgs[] = {String.valueOf(argId)};
        final String selection = ID + " = ?";
        Arg arg = new Arg();
        cursor = super.query(ARGS_TABLE, ARG_COLUMNS, selection, selectionArgs, COLUMN_ARG_ID);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                arg = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return arg;

    }

    @Override
    public List<Arg> fetchAllArgs() {
        List<Arg> argList = new ArrayList<Arg>();
        cursor = super.query(ARGS_TABLE, ARG_COLUMNS, null, null, COLUMN_ARG_ID);

        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Arg arg = cursorToEntity(cursor);
                argList.add(arg);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return argList;
    }

    @Override
    public boolean addArgs(Arg arg) {
        setContentValue(arg);
        try {
            return super.insert(ARGS_TABLE, getContentValue()) > 0;
        }catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    private void setContentValue(Arg arg) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ARG_ID, arg.argId);
        initialValues.put(COLUMN_REQUEST_ID,arg.requestId);
        initialValues.put(COLUMN_ARG,arg.argContent);
        initialValues.put(COLUMN_TYPE, arg.type);
    }

    @Override
    public boolean deleteAllArgs() {
        return false;
    }

    @Override
    protected Arg cursorToEntity(Cursor cursor) {

        Arg arg = new Arg();

        int argIdIndex;
        int requestIdIndex;
        int argContentIndex;
        int typeIndex;

        if(cursor != null){
            if (cursor.getColumnIndex(COLUMN_ARG_ID) != -1){
                argIdIndex = cursor.getColumnIndexOrThrow(COLUMN_ARG_ID);
                arg.argId = cursor.getInt(argIdIndex);
            }
            if (cursor.getColumnIndex(COLUMN_REQUEST_ID) != -1){
                requestIdIndex = cursor.getColumnIndexOrThrow(COLUMN_REQUEST_ID);
                arg.requestId = cursor.getInt(requestIdIndex);
            }
            if (cursor.getColumnIndex(COLUMN_ARG) != -1){
                argContentIndex = cursor.getColumnIndexOrThrow(COLUMN_ARG);
                arg.argContent = cursor.getString(argContentIndex);
            }
            if (cursor.getColumnIndex(COLUMN_TYPE) != -1){
                typeIndex = cursor.getColumnIndexOrThrow(COLUMN_TYPE);
                arg.type = cursor.getString(typeIndex);
            }
        }
        return arg;
    }
}
