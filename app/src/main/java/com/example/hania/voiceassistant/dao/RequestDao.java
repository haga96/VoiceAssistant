package com.example.hania.voiceassistant.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class RequestDao extends DbContentProvider
        implements IRequestSchema, IRequestDao {
    private Cursor cursor;
    private ContentValues initialValues;

    public RequestDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Request fetchRequestById(int requestId) {
        final String selectionArgs[] = {String.valueOf(requestId)};
        final String selection = ID + " = ?";
        Request request = new Request();
        cursor = super.query(REQUEST_TABLE, REQUEST_COLUMNS, selection, selectionArgs, COLUMN_ID);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                request = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return request;
    }

    @Override
    public List<Request> fetchAllRequests() {
        List<Request> requestList = new ArrayList<Request>();
        cursor = super.query(REQUEST_TABLE, REQUEST_COLUMNS, null, null, COLUMN_ID);

        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Request request = cursorToEntity(cursor);
                requestList.add(request);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return requestList;
    }

    @Override
    public boolean addRequest(Request request) {
        setContentValue(request);
        try {
            return super.insert(REQUEST_TABLE, getContentValue()) > 0;
        }catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    private void setContentValue(Request request) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ID, request.requestId);
        initialValues.put(COLUMN_TEXT,request.text);
        initialValues.put(COLUMN_ACTION,request.action);
    }

    @Override
    public boolean deleteAllRequests() {
        return false;
    }

    @Override
    protected Request cursorToEntity(Cursor cursor) {

        Request request = new Request();

        int idIndex;
        int textIndex;
        int actionIndex;

        if(cursor != null){
            if (cursor.getColumnIndex(COLUMN_ID) != -1){
                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                request.requestId = cursor.getInt(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_TEXT) != -1){
                textIndex = cursor.getColumnIndexOrThrow(COLUMN_TEXT);
                request.text = cursor.getString(textIndex);
            }
            if (cursor.getColumnIndex(COLUMN_ACTION) != -1){
                actionIndex = cursor.getColumnIndexOrThrow(COLUMN_ACTION);
                request.action = cursor.getString(actionIndex);
            }
        }
        return request;
    }
}
