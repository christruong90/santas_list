package ca.bcit.ass2.truong_chen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myListDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "xmasList.db";
    private static final String TABLE_NAME = "children_table";


    private static final String DB_PATH = "/data/data/ca.bcit.santalist/databases/";


    public static final String COL_1 = "Id";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "BirthDate";
    public static final String COL_5 = "Street";
    public static final String COL_6 = "City";
    public static final String COL_7 = "Province";
    public static final String COL_8 = "PostalCode";
    public static final String COL_9 = "Country";
    public static final String COL_10 = "Latitude";
    public static final String COL_11 = "Longitude";
    public static final String COL_12 = "isNaughty";
    public static final String COL_13 = "DateCreated";

    private static final int DB_VERSION = 1;


    public myListDbHelper(Context context)
    {
        // The 3'rd parameter (null) is an advanced feature relating to cursors
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, FirstName TEXT, LastName TEXT, BirthDate TEXT," +
                "Street TEXT, City TEXT, Province TEXT, PostalCode TEXT, Country TEXT, Latitude INTEGER, Longitude INTEGER, isNaughty INTEGER," +
                "DateCreated TEXT )");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String firstName, String lastName, String birthDate, String street, String city, String province, String postalCode,
                              String country, double latitude, double longitude, int isNaughty, String dateCreated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, firstName);
        contentValues.put(COL_3, lastName);
        contentValues.put(COL_4, birthDate);
        contentValues.put(COL_5, street);
        contentValues.put(COL_6, city);
        contentValues.put(COL_7, province);
        contentValues.put(COL_8, postalCode);
        contentValues.put(COL_9, country);
        contentValues.put(COL_10, latitude);
        contentValues.put(COL_11, longitude);
        contentValues.put(COL_12, isNaughty);
        contentValues.put(COL_13, dateCreated);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public Cursor findEntry(String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME + " where FirstName = \'" + firstName + "\' or " +
                                "LastName = \'" + lastName + "\'", null);
        return res;
    }

    public boolean editData(String nameFirst, String id, String lastName, String birthDate, String street, String city,
                            String province, String postalCode, String country, double latitude, double longitude,
                            int isNaughty, String dateCreated) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nameFirst);
        contentValues.put(COL_3, lastName);
        contentValues.put(COL_4, birthDate);
        contentValues.put(COL_5, street);
        contentValues.put(COL_6, city);
        contentValues.put(COL_7, province);
        contentValues.put(COL_8, postalCode);
        contentValues.put(COL_9, country);
        contentValues.put(COL_10, latitude);
        contentValues.put(COL_11, longitude);
        contentValues.put(COL_12, isNaughty);
        contentValues.put(COL_13, dateCreated);

        db.update(TABLE_NAME, contentValues, "Id = ?",new String[]{id});
        return true;
    }

    public Integer deleteEntry(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Id = ?", new String[]{id} );
    }

}
