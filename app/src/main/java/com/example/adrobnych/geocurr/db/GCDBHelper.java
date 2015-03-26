package com.example.adrobnych.geocurr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.adrobnych.geocurr.model.entities.GCCurrency;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by adrobnych on 3/26/15.
 */
public class GCDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "com.example.adrobnych.geocurr.db.GCDbHelper";
    private static final String DB_NAME = "gcapp.db";
    private static final int DB_VERSION = 1;
    private Context context;

    public GCDBHelper(Context context) throws SQLException {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTableIfNotExists(connectionSource, GCCurrency.class);

        } catch (java.sql.SQLException e) {
            Log.e(TAG, "onCreate", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Context getContext() {
        return context;
    }
}