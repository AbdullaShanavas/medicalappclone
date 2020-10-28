package com.app.staycured.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.app.staycured.Object.CommentsObject;
import com.app.staycured.Object.PatientDetailsObject;
import com.app.staycured.Object.RegistrationDetailsObject;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "appointmentDb";


    //appointment details table
    private static final String TABLE_APPOINTMENT = "appointmentdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_PATIENT_NAME = "patientName";
    private static final String KEY_SYMPTOMS = "symptoms";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_DRNAME = "drname";
    private static final String KEY_SPECIALITY = "speciality";

    //appointment details table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PATIENT_NAME + " TEXT,"
            + KEY_SYMPTOMS + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_TIME + " TEXT, "
            + KEY_DRNAME + " TEXT, "
            + KEY_SPECIALITY + " TEXT "
            + ")";

    //appointment details inserting
    public void insertAppointmentDetails(Context context, PatientDetailsObject patientDetailsObject
    ) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_APPOINTMENT;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        ContentValues cValues = new ContentValues();
//        cValues.put(KEY_ID, count);
        cValues.put(KEY_PATIENT_NAME, patientDetailsObject.getPatient());
        cValues.put(KEY_SYMPTOMS, patientDetailsObject.getSymptoms());
        cValues.put(KEY_DESCRIPTION, patientDetailsObject.getDescription());
        cValues.put(KEY_DATE, patientDetailsObject.getDate());
        cValues.put(KEY_TIME, patientDetailsObject.getTime());
        cValues.put(KEY_DRNAME, patientDetailsObject.getDrname());
        cValues.put(KEY_SPECIALITY, patientDetailsObject.getSpeciality());
        db.insert(TABLE_APPOINTMENT, null, cValues);
        Log.d("TAG", "insertAppointmentDetails: " + cValues);
        db.close();
    }
    // Get patient Details
    public ArrayList<PatientDetailsObject> GetAppoinmtmentList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PatientDetailsObject> appointmentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_APPOINTMENT;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PatientDetailsObject patientDetailsObject = new PatientDetailsObject();
                patientDetailsObject.setPatient(cursor.getString(cursor.getColumnIndex(KEY_PATIENT_NAME)));
                patientDetailsObject.setSymptoms(cursor.getString(cursor.getColumnIndex(KEY_SYMPTOMS)));
                patientDetailsObject.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                patientDetailsObject.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                patientDetailsObject.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                patientDetailsObject.setDrname(cursor.getString(cursor.getColumnIndex(KEY_DRNAME)));
                patientDetailsObject.setSpeciality(cursor.getString(cursor.getColumnIndex(KEY_SPECIALITY)));

                appointmentList.add(patientDetailsObject);
                Log.d("TAG", "GetAppoinmtmentList: " + patientDetailsObject);
            } while (cursor.moveToNext());

        }
        return appointmentList;
    }

    public ArrayList<PatientDetailsObject> getAllAppointmentList() {
        ArrayList<PatientDetailsObject> patientDetailsObjects = new ArrayList<>();

        // Select All Query
        String selectQuery2 = "SELECT * FROM " + TABLE_APPOINTMENT + " ORDER BY " +
                KEY_PATIENT_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery2, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PatientDetailsObject patientDetailsObject = new PatientDetailsObject();
                patientDetailsObject.setPatient(cursor.getString(cursor.getColumnIndex(KEY_PATIENT_NAME)));
                patientDetailsObject.setSymptoms(cursor.getString(cursor.getColumnIndex(KEY_SYMPTOMS)));
                patientDetailsObject.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                patientDetailsObject.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                patientDetailsObject.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                patientDetailsObject.setDrname(cursor.getString(cursor.getColumnIndex(KEY_DRNAME)));
                patientDetailsObject.setSpeciality(cursor.getString(cursor.getColumnIndex(KEY_SPECIALITY)));


                patientDetailsObjects.add(patientDetailsObject);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return patientDetailsObjects;
    }


    public void DeleteDetails(PatientDetailsObject patientDetailsObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APPOINTMENT, KEY_PATIENT_NAME + " = ?", new String[]{
                String.valueOf(patientDetailsObject.getPatient())});
        db.close();
    }

    public int updatePatientDetails(PatientDetailsObject patientDetailsObject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PATIENT_NAME, patientDetailsObject.getPatient());
        values.put(KEY_DESCRIPTION, patientDetailsObject.getDescription());
        values.put(KEY_SYMPTOMS, patientDetailsObject.getSymptoms());
        values.put(KEY_DATE, patientDetailsObject.getDate());
        values.put(KEY_TIME, patientDetailsObject.getTime());
        values.put(KEY_DRNAME, patientDetailsObject.getDrname());
        values.put(KEY_SPECIALITY, patientDetailsObject.getSpeciality());

        // updating row
        return db.update(TABLE_APPOINTMENT, values,
                KEY_DRNAME + " = ?",
                new String[]{String.valueOf(patientDetailsObject.getDrname())});
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //comments and rating table
    private static final String TABLE_COMMENTS = "commentstable";
    private static final String KEY_COMMENTS = "comments";
    private static final String KEY_RATING = "rating";
    //comments and rating
    private static final String CREATE_COMMENTS_TABLE = "CREATE TABLE " + TABLE_COMMENTS + "("
            + KEY_PATIENT_NAME + " TEXT,"
            + KEY_SYMPTOMS + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_TIME + " TEXT, "
            + KEY_DRNAME + " TEXT, "
            + KEY_SPECIALITY + " TEXT ,"
            + KEY_COMMENTS + " TEXT,"
            + KEY_RATING + " TEXT"
            + ")";
    //comments and rating inserting
    public void insertCommentsRating(Context context, CommentsObject commentsObject
    ) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COMMENTS;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        ContentValues cValues = new ContentValues();
//        cValues.put(KEY_ID, count);
        cValues.put(KEY_COMMENTS, commentsObject.getComments());
        cValues.put(KEY_RATING, commentsObject.getRating());
        cValues.put(KEY_PATIENT_NAME, commentsObject.getPatient());
        cValues.put(KEY_SYMPTOMS, commentsObject.getSymptoms());
        cValues.put(KEY_DESCRIPTION, commentsObject.getDescription());
        cValues.put(KEY_DATE, commentsObject.getDate());
        cValues.put(KEY_TIME, commentsObject.getTime());
        cValues.put(KEY_DRNAME, commentsObject.getDrname());
        cValues.put(KEY_SPECIALITY, commentsObject.getSpeciality());
        db.insert(TABLE_COMMENTS, null, cValues);
        Log.d("TAG", "insertCommentsRating: " + cValues);
        db.close();
    }
    //getting commentsrating
    public ArrayList<CommentsObject> GetCommentsRatings() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CommentsObject> GetCommentsRatingsList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_COMMENTS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                CommentsObject commentsObject = new CommentsObject();
                commentsObject.setComments(cursor.getString(cursor.getColumnIndex(KEY_COMMENTS)));
                commentsObject.setRating(cursor.getString(cursor.getColumnIndex(KEY_RATING)));
                commentsObject.setPatient(cursor.getString(cursor.getColumnIndex(KEY_PATIENT_NAME)));
                commentsObject.setSymptoms(cursor.getString(cursor.getColumnIndex(KEY_SYMPTOMS)));
                commentsObject.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                commentsObject.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                commentsObject.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                commentsObject.setDrname(cursor.getString(cursor.getColumnIndex(KEY_DRNAME)));
                commentsObject.setSpeciality(cursor.getString(cursor.getColumnIndex(KEY_SPECIALITY)));

                GetCommentsRatingsList.add(commentsObject);
                Log.d("TAG", "GetAppoinmtmentList: " + commentsObject);
            } while (cursor.moveToNext());

        }
        return GetCommentsRatingsList;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //registration table
    private static final String TABLE_REGISTRATION = "registeration";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_MIDDLENAME = "middleName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_ADDRESSLINE1 = "addressline1";
    private static final String KEY_ADDRESSLINE2 = "addressline2";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_ZIPCODE = "zipcode";
    private static final String KEY_MOBILE = "mobile";

    //comments and rating
    private static final String CREATE_REGISTRATION = "CREATE TABLE " + TABLE_REGISTRATION + "("
            + KEY_EMAIL + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_FIRSTNAME + " TEXT,"
            + KEY_MIDDLENAME + " TEXT,"
            + KEY_LASTNAME + " TEXT, "
            + KEY_GENDER + " TEXT, "
            + KEY_DOB + " TEXT ,"
            + KEY_ADDRESSLINE1 + " TEXT,"
            + KEY_ADDRESSLINE2 + " TEXT,"
            + KEY_CITY + " TEXT,"
            + KEY_STATE + " TEXT,"
            + KEY_COUNTRY + " TEXT,"
            + KEY_ZIPCODE + " TEXT,"
            + KEY_MOBILE + " TEXT"
            + ")";
    //inserting registration
    public void insertRegistration(Context context, RegistrationDetailsObject registrationDetailsObject
    ) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_REGISTRATION;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_EMAIL, registrationDetailsObject.getEmail());
        cValues.put(KEY_PASSWORD, registrationDetailsObject.getPassword());
        cValues.put(KEY_FIRSTNAME, registrationDetailsObject.getFirstName());
        cValues.put(KEY_MIDDLENAME, registrationDetailsObject.getMiddleName());
        cValues.put(KEY_LASTNAME, registrationDetailsObject.getLastName());
        cValues.put(KEY_GENDER, registrationDetailsObject.getGender());
        cValues.put(KEY_DOB, registrationDetailsObject.getDob());
        cValues.put(KEY_ADDRESSLINE1, registrationDetailsObject.getAddressLine1());
        cValues.put(KEY_ADDRESSLINE2, registrationDetailsObject.getAddressLine2());
        cValues.put(KEY_CITY, registrationDetailsObject.getCity());
        cValues.put(KEY_STATE, registrationDetailsObject.getState());
        cValues.put(KEY_COUNTRY, registrationDetailsObject.getCountry());
        cValues.put(KEY_ZIPCODE, registrationDetailsObject.getZipCode());
        cValues.put(KEY_MOBILE, registrationDetailsObject.getMobile());
        db.insert(TABLE_REGISTRATION, null, cValues);
        Log.d("TAG", "insertCommentsRating: " + cValues);
        db.close();
    }
    //getting registeration
    public ArrayList<RegistrationDetailsObject> registrationDetailsObjectArrayList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<RegistrationDetailsObject> GetRegistrationList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_REGISTRATION;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                RegistrationDetailsObject registrationDetailsObject = new RegistrationDetailsObject();
                registrationDetailsObject.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                registrationDetailsObject.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                registrationDetailsObject.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                registrationDetailsObject.setMiddleName(cursor.getString(cursor.getColumnIndex(KEY_MIDDLENAME)));
                registrationDetailsObject.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                registrationDetailsObject.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                registrationDetailsObject.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                registrationDetailsObject.setAddressLine1(cursor.getString(cursor.getColumnIndex(KEY_ADDRESSLINE1)));
                registrationDetailsObject.setAddressLine2(cursor.getString(cursor.getColumnIndex(KEY_ADDRESSLINE2)));
                registrationDetailsObject.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                registrationDetailsObject.setState(cursor.getString(cursor.getColumnIndex(KEY_STATE)));
                registrationDetailsObject.setCountry(cursor.getString(cursor.getColumnIndex(KEY_COUNTRY)));
                registrationDetailsObject.setZipCode(cursor.getString(cursor.getColumnIndex(KEY_ZIPCODE)));
                registrationDetailsObject.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));

                GetRegistrationList.add(registrationDetailsObject);
                Log.d("TAG", "GetAppoinmtmentList: " + registrationDetailsObject);
            } while (cursor.moveToNext());

        }
        return GetRegistrationList;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_COMMENTS_TABLE);
        db.execSQL(CREATE_REGISTRATION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL(TABLE_APPOINTMENT);
        db.execSQL(TABLE_COMMENTS);
        db.execSQL(TABLE_REGISTRATION);

        // Create tables again
        onCreate(db);
    }
}