package com.salmanvirji.testcryptov2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "cryptotrackerdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "favlist";

    private static final String ID_COLUMN = "id";

    private static final String FAV_COLUMN = "favitem";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sql query to create new Table
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FAV_COLUMN + " TEXT)";

        // execute sql query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDB, int newDB) {
        // check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewFavItem(String favItemName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FAV_COLUMN, favItemName);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<String> readFavItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor cursorFav = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Cursor cursorFav = db.query(
          TABLE_NAME, null, null, null, null, null, null
        );

        ArrayList<String> cryptoFavArrayList = new ArrayList<>();

        cursorFav.moveToFirst();
        while (cursorFav.moveToNext()) {
            String itemName = cursorFav.getString(
                    cursorFav.getColumnIndexOrThrow(FAV_COLUMN));
            cryptoFavArrayList.add(itemName);
        };

        cursorFav.close();
        return cryptoFavArrayList;
    }
}
