package com.gin.praktice.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper {

    // stored local variables for the OpenHelper and the database it opens
    public DatabaseOpenHelper openHelper;
    public SQLiteDatabase database;

    // list of constants for referencing the db's internal values
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ms.db";

    public static final String TABLE_NAME_SQUAD = "TABLE_NAME_SQUAD";

    public static final String SQUAD_COLUMN_ID = "id";
    public static final String SQUAD_COLUMN_NAME = "name";
    public static final String SQUAD_COLUMN_CREATEDATETIME = "createDateTime";

    public static final String TABLE_NAME_MEMBER = "TABLE_NAME_MEMBER";
    public static final String TABLE_NAME_RESULT = "TABLE_NAME_RESULT";

//    public static final String USER_TABLE_NAME = "user_profile";
//    public static final String PROFILE_COLUMN_ID = "_id";
//    public static final String PROFILE_COLUMN_USERNAME = "userName";
//    public static final String PROFILE_COLUMN_GENDER = "gender";
//    public static final String PROFILE_COLUMN_AGE = "age";

    public SQLiteHelper(Context context) {
        openHelper = new DatabaseOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    // this is what actually declares the database so that other activites can
    // read and write to it
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        DatabaseOpenHelper(Context context) {
            // pass name (pft_test_records.db) and version number (1) as super
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // when the db is created. this method is called by onUpgrade when the
        // version number is changed
        public void onCreate(SQLiteDatabase database) {
            // execSQL actually creates the schema of the db we defined in the
            // method above
            database.execSQL("CREATE TABLE " + TABLE_NAME_SQUAD + "( "
                    + SQUAD_COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + SQUAD_COLUMN_NAME + " TEXT, "
                    + SQUAD_COLUMN_CREATEDATETIME + " DATE)");

//            database.execSQL("CREATE TABLE " + USER_TABLE_NAME + "( "
//                    + PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY, "
//                    + PROFILE_COLUMN_USERNAME + " TEXT, "
//                    + PROFILE_COLUMN_GENDER + " TEXT, " + PROFILE_COLUMN_AGE
//                    + " TEXT )");
        }

        // when the version number is changed, this method is called
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
            // drops table if exists, and then calls onCreate which implements
            // our new schema
//            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SQUAD);
//            database.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
//            onCreate(database);
        }
    }

    // method called by onActivityResult which actually saves user entered data
    // into the db
    public void saveSquad(String name)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQUAD_COLUMN_NAME, name);
        contentValues.put(SQUAD_COLUMN_CREATEDATETIME, "");

        database.insert(TABLE_NAME_SQUAD, null, contentValues);
    }

    public void deleteSquad(String name)
    {
        database.delete(TABLE_NAME_SQUAD, SQUAD_COLUMN_NAME + "=" + "'" + name + "'", null);
    }

//     method that saves the users profile information
//    public void saveUserProfile(String userName, String userGender,
//                                String userAge) {
//
//        // Clears the legacy profile information first
////        database.execSQL("DELETE FROM user_profile;");
////
////        // Writes the new profile information
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(PROFILE_COLUMN_USERNAME, userName);
////        contentValues.put(PROFILE_COLUMN_GENDER, userGender);
////        contentValues.put(PROFILE_COLUMN_AGE, userAge);
////        database.insert(USER_TABLE_NAME, null, contentValues);
//    }

    // null because there are no selection args since we are just selecting
    // everything
//    public Cursor getUserProfileInfo() {
//        return database.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
//    }


    // null because there are no selection args since we are just selecting
    // everything
    public Cursor getAllSquad() {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME_SQUAD + " ORDER BY "
                + SQUAD_COLUMN_ID + " DESC", null);
    }

    // null because there are no selection args since we are just selecting
    // everything
    public Cursor getSingleTimeRecord(String list_view_row_id) {
        return database.rawQuery("SELECT * FROM pft_test_records WHERE _id = "
                + list_view_row_id + ";", null);
    }

    public void deleteSingleRecord(String list_view_row_id) {
        database.execSQL("DELETE FROM pft_test_records WHERE _id = "
                + list_view_row_id + ";");
    }
}
