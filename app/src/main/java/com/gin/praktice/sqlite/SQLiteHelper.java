package com.gin.praktice.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gin.praktice.component.Component;
import com.gin.praktice.component.Member;
import com.gin.praktice.component.Squad;

import java.util.List;

public class SQLiteHelper {

    public static SQLiteHelper instance = null;

    // stored local variables for the OpenHelper and the database it opens
    public DatabaseOpenHelper openHelper;
    public SQLiteDatabase database;

    // list of constants for referencing the db's internal values
    public final int DATABASE_VERSION = 1;
    public final String DATABASE_NAME = "ms.db";

    public final String TABLE_NAME_SETTING = "TABLE_NAME_SETTING";
    public final String TABLE_NAME_SQUAD = "TABLE_NAME_SQUAD";
    public final String TABLE_NAME_MEMBER = "TABLE_NAME_MEMBER";

    public final String SPLITOR_COLUMN_LANGFLAG = "languageFlag";

    public final String SQUAD_COLUMN_ID = "squadID";
    public final String SQUAD_COLUMN_NAME = "name";

    public final String MEMBER_COLUMN_ID = "memberID";
    public final String MEMBER_COLUMN_SQUAD_NAME = "squadName";
    public final String MEMBER_COLUMN_NAME = "name";
    public final String MEMBER_COLUMN_BANK = "bank";
    public final String MEMBER_COLUMN_ACCOUNT = "accountNumber";
    public final String MEMBER_COLUMN_PHONENUMBER = "phoneNumber";

    private SQLiteHelper() {

    }
    private SQLiteHelper(Context context) {
        openHelper = new DatabaseOpenHelper(context);
        database = openHelper.getWritableDatabase();

        loadLanguageFlag();

//        context.deleteDatabase("ms.db");
    }

    public static SQLiteHelper getInstance(Context context) {
        if (instance == null)
        {
            instance = new SQLiteHelper(context);
        }
        return instance;
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
            createTables(database);
        }

        // when the version number is changed, this method is called
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
            // drops table if exists, and then calls onCreate which implements
            // our new schema
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SETTING);
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SQUAD);
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEMBER);
            onCreate(database);
        }
    }

    //default flag (If langFlag = -1)
    //Update flag (If set new langFlag)
    public void saveLanguageFlag(int languageFlagInt) {

        ContentValues languageFlag = new ContentValues();

        if (languageFlagInt < 0)
        {
            languageFlag.put(SPLITOR_COLUMN_LANGFLAG, 0);
            database.insert(TABLE_NAME_SETTING, null, languageFlag);
        }
        else
        {
            database.execSQL("delete from "+ TABLE_NAME_SETTING);
            languageFlag.put(SPLITOR_COLUMN_LANGFLAG, languageFlagInt);
            database.insert(TABLE_NAME_SETTING, null, languageFlag);
        }
    }

    public int loadLanguageFlag() {
        Cursor langFlagCursor = database.rawQuery("SELECT * FROM " + TABLE_NAME_SETTING, null);
        langFlagCursor.moveToFirst();

        // Initial execute
//        if (langFlagCursor.isNull(langFlagCursor.getColumnIndex(SPLITOR_COLUMN_LANGFLAG)))
//        {
//            saveLanguageFlag(-1);
//            return 0;
//        }
        return langFlagCursor.getInt(langFlagCursor.getColumnIndex(SPLITOR_COLUMN_LANGFLAG));
    }

    // 2. 마지막 Result 나온 후 저장하는 기능 추후에 (Table 추가하거나 column 추가 해얄듯)
    public void saveSquad(Squad squad)
    {
        ContentValues squadTableValues = new ContentValues();
        squadTableValues.put(SQUAD_COLUMN_NAME, squad.getName());

        database.insert(TABLE_NAME_SQUAD, null, squadTableValues);

        List<Component> memberList = squad.getList();
        for (int i = 0; i < memberList.size(); ++i)
        {
            ContentValues memberTableValues = new ContentValues();
            memberTableValues.put(MEMBER_COLUMN_SQUAD_NAME, squad.getName());
            memberTableValues.put(MEMBER_COLUMN_NAME, ((Member)memberList.get(i)).getName());
            memberTableValues.put(MEMBER_COLUMN_BANK, "");//((Member)memberList.get(i)).getName());
            memberTableValues.put(MEMBER_COLUMN_ACCOUNT, "");//((Member)memberList.get(i)).getName());
            memberTableValues.put(MEMBER_COLUMN_PHONENUMBER, "");//((Member)memberList.get(i)).getName());

            database.insert(TABLE_NAME_MEMBER, null, memberTableValues);
        }
    }

    public void insertMember(String squadName, Member member) {
        ContentValues memberTableValues = new ContentValues();
        memberTableValues.put(MEMBER_COLUMN_SQUAD_NAME, squadName);
        memberTableValues.put(MEMBER_COLUMN_NAME, member.getName());
        memberTableValues.put(MEMBER_COLUMN_BANK, "");          // member.getBank());
        memberTableValues.put(MEMBER_COLUMN_ACCOUNT, "");       // member.getAccount());
        memberTableValues.put(MEMBER_COLUMN_PHONENUMBER, "");   // member.getPhoneNumber());

        database.insert(TABLE_NAME_MEMBER, null, memberTableValues);
    }

    // 삭제할때 member table에서 같은 아이디값들 삭제해야함
    public void deleteSquad(Squad squad)
    {
        database.delete(TABLE_NAME_SQUAD, SQUAD_COLUMN_NAME + "=" + "'" + squad.getName() + "'", null);
        database.delete(TABLE_NAME_MEMBER, MEMBER_COLUMN_SQUAD_NAME + "=" + "'" + squad.getName() + "'", null);
    }

    public void deleteMember(String memberName, String squadName)
    {
        database.delete(TABLE_NAME_MEMBER, MEMBER_COLUMN_SQUAD_NAME + "=" + "'" + squadName + "'"
                                    + " AND " + MEMBER_COLUMN_NAME + "=" + "'" + memberName + "'", null);
    }

    private void createTables(SQLiteDatabase database)
    {
        database.execSQL("CREATE TABLE " + TABLE_NAME_SETTING
                + "("
                + SPLITOR_COLUMN_LANGFLAG + " INTEGER"
                + ")" );

        database.execSQL("CREATE TABLE " + TABLE_NAME_SQUAD
                + "("
                + SQUAD_COLUMN_ID + " INTEGER PRIMARY KEY, "
                + SQUAD_COLUMN_NAME + " TEXT"
                + ")" );

        database.execSQL("CREATE TABLE " + TABLE_NAME_MEMBER
                + "("
                + MEMBER_COLUMN_ID + " INTEGER PRIMARY KEY, "
                + MEMBER_COLUMN_SQUAD_NAME + " TEXT,"
                + MEMBER_COLUMN_NAME + " TEXT,"
                + MEMBER_COLUMN_BANK + " TEXT,"
                + MEMBER_COLUMN_ACCOUNT + " INTEGER,"
                + MEMBER_COLUMN_PHONENUMBER + " INTEGER"
                + ")" );
    }

    // null because there are no selection args since we are just selecting
    // everything
    public Cursor getAllSquad() {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME_SQUAD + " ORDER BY "
                + SQUAD_COLUMN_ID + " DESC", null);
    }
    public Cursor getAllMembers() {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME_MEMBER, null);
    }
}
