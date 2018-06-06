//package com.gin.praktice.sqlite;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public DBHelper(Context context, String dbName, int dbVersion) {
//        super(context, dbName, null, dbVersion);
//
//
//    }
//
//    /**
//     * It'll be called just one time when the app's first installing?
//     *
//     * @param db
//     */
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL("create table " + TABLE_NAME + " ("
//                + IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COL_0 + " INTEGER, "
//                + COL_1 + " INTEGER, "
//                + COL_2 + " INTEGER, "
//                + COL_3 + " INTEGER, "
//                + COL_4 + " INTEGER, "
//                + COL_5 + " INTEGER, "
//                + COL_6 + " INTEGER, "
//                + COL_7 + " INTEGER, "
//                + COL_8 + " INTEGER, "
//                + COL_9 + " INTEGER, "
//                + COL_10 + " INTEGER, "
//                + COL_11 + " TEXT"
//                + ")");
//
//    }
//
//    /**
//     * It'll be called when the application is updated
//     *
//     * @param db
//     * @param oldVersion
//     * @param newVersion
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//
//}
