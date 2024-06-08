package org.fitmyss.courseworkslomov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBDataPatient extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "patientData.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_PATIENTS = "Patients";

    // Table columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_HOUSE = "house";
    public static final String COLUMN_DISEASES = "diseases";
    public static final String COLUMN_ALLERGY = "allergy";
    public static final String COLUMN_HABITS = "habits";
    public static final String COLUMN_COMPLAINTS = "complaints";
    public static final String COLUMN_DOC = "doctor";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_RECEPTION = "reception";

    // SQL statement to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PATIENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULL_NAME + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " +
                    COLUMN_HOUSE + " TEXT, " +
                    COLUMN_DISEASES + " TEXT, " +
                    COLUMN_ALLERGY + " TEXT, " +
                    COLUMN_HABITS + " TEXT, " +
                    COLUMN_COMPLAINTS + " TEXT, " +
                    COLUMN_DOC + " TEXT, " +
                    COLUMN_DATETIME + " TEXT, " +
                    COLUMN_RECEPTION + " TEXT" +
                    ");";

    public DBDataPatient(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    public long insertPatientData(String fullName, int age, String house, String diseases, String allergy, String habits, String complaints, String doc, String dateTime, String reception) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, fullName);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_HOUSE, house);
        values.put(COLUMN_DISEASES, diseases);
        values.put(COLUMN_ALLERGY, allergy);
        values.put(COLUMN_HABITS, habits);
        values.put(COLUMN_COMPLAINTS, complaints);
        values.put(COLUMN_DOC, doc);
        values.put(COLUMN_DATETIME, dateTime);
        values.put(COLUMN_RECEPTION, reception);

        long result = db.insert(TABLE_PATIENTS, null, values);
        db.close();
        return result;
    }

    public List<String> getAllPatients() {
        List<String> patients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENTS, null);

        if (cursor.moveToFirst()) {
            do {
                String patient = "ID: " + cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)) +
                        ", Full Name: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)) +
                        ", Age: " + cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)) +
                        ", House: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOUSE)) +
                        ", Diseases: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DISEASES)) +
                        ", Allergy: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ALLERGY)) +
                        ", Habits: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HABITS)) +
                        ", Complaints: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPLAINTS)) +
                        ", Doctor: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC)) +
                        ", Datetime: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)) +
                        ", Reception: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECEPTION));
                patients.add(patient);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return patients;
    }
    public boolean updateReceptionById(int id, String newReception) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECEPTION, newReception);

        int rowsAffected = db.update(TABLE_PATIENTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}

