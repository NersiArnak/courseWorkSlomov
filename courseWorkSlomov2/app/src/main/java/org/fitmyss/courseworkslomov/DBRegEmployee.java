package org.fitmyss.courseworkslomov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBRegEmployee extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RegEmployee.db";
    private static final String TABLE_NAME = "RegEmployee";
    private static final String COL_EMAIL = "Email";
    private static final String COL_PASSWORD = "Password";

    public DBRegEmployee(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean checkUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_EMAIL}, COL_EMAIL + "=?", new String[]{email}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUserPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_PASSWORD}, COL_EMAIL + "=? AND " + COL_PASSWORD + "=?", new String[]{email, password}, null, null, null);
        boolean correctPassword = cursor.getCount() > 0;
        cursor.close();
        return correctPassword;
    }
    public void addContact(Data objData){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EMAIL, objData.email);
        contentValues.put(COL_PASSWORD, objData.password);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public void addOne(Data objData){
        addContact(objData);
    }
}
