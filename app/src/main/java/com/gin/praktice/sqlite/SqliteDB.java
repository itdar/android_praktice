//package com.gin.praktice.sqlite;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//public class SqliteDB {
//
//    private SQLiteDatabase sqliteDB;
//
//    public static final String DB_NAME = "MS.db";
//    public static final int DB_VERSION = 1;
//
//    public static final String TABLE_SQUAD = "Squad";
//
//    public static final String IDX = "IDX";
//    public static final String COL_0 = "DRWNO";
//    public static final String COL_1 = "DRWTNO1";
//    public static final String COL_2 = "DRWTNO2";
//    public static final String COL_3 = "DRWTNO3";
//    public static final String COL_4 = "DRWTNO4";
//    public static final String COL_5 = "DRWTNO5";
//    public static final String COL_6 = "DRWTNO6";
//    public static final String COL_7 = "BNUSNO";
//    public static final String COL_8 = "FIRSTPRZWNERCO";
//    public static final String COL_9 = "FIRSTWINAMNT";
//    public static final String COL_10 = "TOTSELLAMNT";
//    public static final String COL_11 = "DRWNODATE";
//
//    public SqliteDB(Context context) {
//        DBHelper dbHelper = new DBHelper(context, DB_NAME, DB_VERSION);
//
//        sqliteDB = dbHelper.getWritableDatabase();
//
//    }
//
//
//    // Database 생성 및 열기
////    public void createDatabase(String dbName, int dbMode){
////        sqliteDB = openOrCreateDatabase(dbName,dbMode,null);
////    }
//
//    // Table 생성
//    public void createTable(){
//        String sql = "create table " + TABLE_SQUAD + "(id integer primary key autoincrement, "+"voca text not null)";
//        sqliteDB.execSQL(sql);
//    }
//
//    // Table 삭제
//    public void removeTable(){
//        String sql = "drop table " + TABLE_SQUAD;
//        sqliteDB.execSQL(sql);
//    }
//
//    // Data 추가
//    public void insertData(String voca){
//        String sql = "insert into " + TABLE_SQUAD + " values(NULL, '" + voca +"');";
//        sqliteDB.execSQL(sql);
//    }
//
//    // Data 업데이트
//    public void updateData(int index, String voca){
//        String sql = "update " + TABLE_SQUAD + " set voca = '" + voca +"' where id = "+index +";";
//        sqliteDB.execSQL(sql);
//    }
//
//    // Data 삭제
//    public void removeData(int index){
//        String sql = "delete from " + TABLE_SQUAD + " where id = "+index+";";
//        sqliteDB.execSQL(sql);
//    }
//
//    // Data 읽기(꺼내오기)
//    public void selectData(int index){
//        String sql = "select * from " +TABLE_SQUAD+ " where id = "+index+";";
//        Cursor result = sqliteDB.rawQuery(sql, null);
//
//        // result(Cursor 객체)가 비어 있으면 false 리턴
//        if(result.moveToFirst()){
//            int id = result.getInt(0);
//            String voca = result.getString(1);
////            Toast.makeText(this, "index= "+id+" voca="+voca, 0).show();
//        }
//        result.close();
//    }
//
//
//    // 모든 Data 읽기
//    public void selectAll(){
//        String sql = "select * from " + TABLE_SQUAD + ";";
//        Cursor results = sqliteDB.rawQuery(sql, null);
//
//        results.moveToFirst();
//
//        while(!results.isAfterLast()){
//            int id = results.getInt(0);
//            String voca = results.getString(1);
////            Toast.makeText(this, "index= "+id+" voca="+voca, 0).show();
//            results.moveToNext();
//        }
//        results.close();
//    }
//
//
//    public void setSqliteDB(SQLiteDatabase sqliteDB) { this.sqliteDB = sqliteDB; }
//    public SQLiteDatabase getSqliteDB() { return sqliteDB; }
//}
