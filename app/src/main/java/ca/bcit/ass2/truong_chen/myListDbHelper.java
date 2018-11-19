package ca.bcit.ass2.truong_chen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        seedDb(db);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        seedDb(db);
    }

    public boolean insertData(String firstName, String lastName, String birthDate, String street, String city, String province, String postalCode,
                              String country, double latitude, double longitude, int isNaughty) {
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






        SimpleDateFormat setDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = setDate.format(new Date());

        contentValues.put(COL_13, dateString);

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

    public void seedDb(SQLiteDatabase db) {
        ContentValues myContents= new ContentValues();
        ContentValues myContents2= new ContentValues();
        ContentValues myContents3= new ContentValues();
        ContentValues myContents4= new ContentValues();
        ContentValues myContents5= new ContentValues();

        myContents.put(COL_2, "Chris");
        myContents.put(COL_3, "Truong");
        myContents.put(COL_4, "march 14 1990");
        myContents.put(COL_5, "152 fake street");
        myContents.put(COL_6, "Surrey");
        myContents.put(COL_7, "BC");
        myContents.put(COL_8, "V3R 8R7");
        myContents.put(COL_9, "Can");
        myContents.put(COL_10, 8.4);
        myContents.put(COL_11, 8.2);
        myContents.put(COL_12, 1);

        myContents2.put(COL_2, "Chang");
        myContents2.put(COL_3, "Chen");
        myContents2.put(COL_4, "Feb 31 2020");
        myContents2.put(COL_5, "183 fake street");
        myContents2.put(COL_6, "Vancouver");
        myContents2.put(COL_7, "AB");
        myContents2.put(COL_8, "X8U 8R7");
        myContents2.put(COL_9, "Mexico");
        myContents2.put(COL_10, 9.2);
        myContents2.put(COL_11, 1.98);
        myContents2.put(COL_12, 0);

        myContents3.put(COL_2, "Samit");
        myContents3.put(COL_3, "Bains");
        myContents3.put(COL_4, "June 8 2010");
        myContents3.put(COL_5, "183 kingsway street");
        myContents3.put(COL_6, "Chiraq");
        myContents3.put(COL_7, "AB");
        myContents3.put(COL_8, "V8A 9V6");
        myContents3.put(COL_9, "United States");
        myContents3.put(COL_10, 9.29);
        myContents3.put(COL_11, 9.31);
        myContents3.put(COL_12, 0);

        myContents4.put(COL_2, "Jack");
        myContents4.put(COL_3, "Unknown");
        myContents4.put(COL_4, "May 2 1839");
        myContents4.put(COL_5, "123 Tyne street");
        myContents4.put(COL_6, "David Thompson");
        myContents4.put(COL_7, "BC");
        myContents4.put(COL_8, "V3A 9P8");
        myContents4.put(COL_9, "China");
        myContents4.put(COL_10, 9.21);
        myContents4.put(COL_11, 12.3);
        myContents4.put(COL_12, 1);

        myContents5.put(COL_2, "Kevin");
        myContents5.put(COL_3, "Kim");
        myContents5.put(COL_4, "Oct 12 2039");
        myContents5.put(COL_5, "123 Victoria street");
        myContents5.put(COL_6, "Coquitlam");
        myContents5.put(COL_7, "BC");
        myContents5.put(COL_8, "V2A 9P0");
        myContents5.put(COL_9, "Korea");
        myContents5.put(COL_10, 19.42);
        myContents5.put(COL_11, 2.48);
        myContents5.put(COL_12, 0);



        SimpleDateFormat setDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = setDate.format(new Date());

        myContents.put(COL_13,dateString);
        myContents2.put(COL_13,dateString);
        myContents3.put(COL_13,dateString);
        myContents4.put(COL_13, dateString);
        myContents5.put(COL_13, dateString);

        db.insert(TABLE_NAME,null,myContents);
        db.insert(TABLE_NAME,null,myContents2);
        db.insert(TABLE_NAME,null,myContents3);
        db.insert(TABLE_NAME,null,myContents4);
        db.insert(TABLE_NAME,null,myContents5);

    }

    public boolean editData(String nameFirst, String id, String lastName, String birthDate, String street, String city,
                            String province, String postalCode, String country, double latitude, double longitude,
                            int isNaughty) {

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
//        contentValues.put(COL_13, dateCreated);

        db.update(TABLE_NAME, contentValues, "Id = ?",new String[]{id});
        return true;
    }

    public Integer deleteEntry(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Id = ?", new String[]{id} );
    }

}
