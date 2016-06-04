package com.f9.imagetagging.activites.imageshelve.database;

/**
 * Created by Milind Suryawanshi on 5/9/16.
 */
public class DbConstants {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "ImageData";

    // Contacts table name
    public static final String TABLE_IMAGE_DATA = "imagesData";

    // Contacts Table Columns names
    public static final String DB_COL_IMG_ID = "imageID";
    public static final String DB_COL_IMG_PATH = "imagePath";
    public static final String DB_COL_SMILEY_TYPE = "imageType";
    public static final String DB_COL_MOVE_TO_TRASH = "moveToTrash";


    public static final int SMILEY_TYPE_HAPPY = 0;
    public static final int SMILEY_TYPE_IMOTION = 1 + SMILEY_TYPE_HAPPY ;
    public static final int SMILEY_TYPE_ANGRY = 1+SMILEY_TYPE_IMOTION;
}
