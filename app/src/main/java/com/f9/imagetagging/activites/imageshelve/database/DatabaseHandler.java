package com.f9.imagetagging.activites.imageshelve.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import com.f9.imagetagging.activites.imageshelve.data.ImageData;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milind Suryawanshi on 5/9/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context){
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DbConstants.TABLE_IMAGE_DATA + "(" +
        DbConstants.DB_COL_IMG_ID + " INTEGER"  + DbConstants.DB_COL_IMG_PATH + " TEXT,"
        + DbConstants.DB_COL_SMILEY_TYPE + " INTEGER"  + DbConstants.DB_COL_MOVE_TO_TRASH + " BOOLEAN" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_IMAGE_DATA);

        // Create tables again
        onCreate(db);
    }




    //All CRUD Operations (Create, Read, Update and Delete)

    // Adding new IMAGE PATH WITH ITS TYPE
    public void addImageData(ImageData imgData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbConstants.DB_COL_IMG_PATH, imgData.getImgPath()); // Contact Name
        values.put(DbConstants.DB_COL_SMILEY_TYPE, imgData.getImgSmiley()); // Contact Phone Number

        // Inserting Row
        db.insert(DbConstants.TABLE_IMAGE_DATA, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
//    public Image getImageData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(DbConstants.TABLE_IMAGE_DATA, new String[] { KEY_ID,
//                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return contact
//        return contact;
//    }

    // Getting All Contacts
    public List<ImageData> getAllImageList() {
        List<ImageData> imgDataList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DbConstants.TABLE_IMAGE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ImageData imgData = new ImageData();
                imgData.setImgPath(cursor.getString(0));
                imgData.setImgSmiley(Integer.parseInt(cursor.getString(1)));
                // Adding Images to list
                imgDataList.add(imgData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return imgDataList;
    }
}
