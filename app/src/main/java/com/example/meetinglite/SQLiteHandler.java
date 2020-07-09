package com.example.meetinglite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by cocse_admin on 1/25/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    // make the variables constant by "private static final"
    private static final String MYDATABASE = "meetingdb";
    private static final int VERSION = 1;
    private final String TABLE_NAME = "meetingstable";
    protected Context context;

    public SQLiteHandler(final Context connection) {

        super(connection, MYDATABASE, null, VERSION);
        this.context = connection;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // don't miss the space after EXISTS " otherwise it will crush the db
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " (meetingname VARCHAR,meetingdesc VARCHAR,meetingdate VARCHAR,meetingtime VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXIST o");
        // onCreate(db);
    }

    public void InsertData(String mname, String mdesc, String mdate,
                           String mtime) {

        try {

            // open sqlite database to enter/insert data
            SQLiteDatabase db = this.getWritableDatabase();

            // insert data
            ContentValues row = new ContentValues();
            row.put("meetingname", mname);
            row.put("meetingdesc", mdesc);
            row.put("meetingdate", mdate);
            row.put("meetingtime", mtime);

            long chk = db.insert("meetingstable", null, row);

            if (chk != 0) {
                Toast.makeText(context, "Record added successfully",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Record add failed...! ",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String getMeetingsName() {

        SQLiteDatabase db = this.getReadableDatabase();

        final Cursor c = db.rawQuery("SELECT meetingname FROM " + TABLE_NAME
                + "", null);
        StringBuffer bf = new StringBuffer();

        while (c.moveToNext()) {
            int index1 = c.getColumnIndex("meetingname");
            String meetings = c.getString(index1);
            bf.append(meetings + "#");
        }
        return bf.toString();

    }
    public String searchByDate(String meetingdate) {

        SQLiteDatabase db = this.getReadableDatabase();

        final Cursor c = db.rawQuery("SELECT meetingname FROM " + TABLE_NAME
                + " where meetingdate" + " = '" + meetingdate + "'", null);

        StringBuffer bf = new StringBuffer();

        while (c.moveToNext()) {
            int index1 = c.getColumnIndex("meetingname");
            String test_name = c.getString(index1);
            bf.append(test_name + ",");
        }
        return bf.toString();

    }

    public String getMeetingDetails(String meetingname) {

        SQLiteDatabase db = this.getReadableDatabase();

        final Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " where meetingname" + " = '" + meetingname + "'", null);
        StringBuffer bf = new StringBuffer();

        while (c.moveToNext()) {
            int index1 = c.getColumnIndex("meetingname");
            int index2 = c.getColumnIndex("meetingdesc");
            int index3 = c.getColumnIndex("meetingdate");
            int index4 = c.getColumnIndex("meetingtime");

            String meeting_name = c.getString(index1);
            String meeting_desc = c.getString(index2);
            String meeting_date = c.getString(index3);
            String meeting_time = c.getString(index4);

            bf.append(meeting_name + "," + meeting_desc + "," + meeting_date
                    + "," + meeting_time + ",");
        }
        return bf.toString();

    }
}