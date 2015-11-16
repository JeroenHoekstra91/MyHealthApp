package com.example.jeroen.myhealthapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import com.example.jeroen.myhealthapp.models.Pulse;

import org.w3c.dom.Comment;

/**
 * Created by Jeroen on 12-11-2015.
 */
public class PulseDao extends Dao<Pulse, PulseDao> {
    protected static PulseDao singleton;
    protected static char DATA_SEPERATOR = ',';

    private PulseDao(Context context) {
        super(context, "pulse");
        TABLE = "pulse";
        COLUMNS = new String[]{"data"};
        COLUMN_TYPES = new String[]{"TEXT NOT NULL"};
    }

    public static PulseDao getDao(Context context) {
        if(singleton == null) { singleton = new PulseDao(context); }
        return singleton;
    }

    @Override
    public void save(Pulse instance) {
        ContentValues values = new ContentValues();

        String data = "";
        for(int val : instance.getData()) {
            data += "" + val + DATA_SEPERATOR;
        }
        data = data.substring(0, data.length() - 1);

        values.put("data", data);
        database.insert(TABLE, null, values);
    }

    @Override
    public void update(Pulse instance) {}

    @Override
    public Pulse deserialize(Cursor cursor) {
        Pulse pulse = new Pulse();
        pulse.setId((int) cursor.getLong(0));
        String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
        String[] vals = data.split(DATA_SEPERATOR + "");

        for(String val : vals) {
            pulse.addData(Integer.parseInt(val));
        }

        return pulse;
    }
}