package ifpe.surpriseme.database;

import android.provider.BaseColumns;

import ifpe.surpriseme.Model.Category;

/**
 * Created by Rayana on 11/17/2016.
 */
public class DatabaseSchemaHelper {

    /**
     * UPDATE table_name
     SET column1 = value1, column2 = value2...., columnN = valueN
     WHERE [condition];
     *
     * */
    public static String change_image_time;
    public static boolean save_image_tophone;
    public static final String DATABASE_NAME = "SurpriseMe.db";
    public static final int DATABASE_VERSION = 6;

    public static final String SQL_CREATE_USERSETTINGS = "CREATE TABLE " + UserSettings.TABLE_NAME + " (" + UserSettings._ID + " INTEGER PRIMARY KEY, " +
            UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME + " TEXT, " + UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE + " BOOLEAN " + ")";

    public static final String SQL_DELETE_USERSETTINGS = "" + "DROP TABLE IF EXISTS " + UserSettings.TABLE_NAME;

    public static final String SQL_CREATE_CATEGORY = "CREATE TABLE " + Category.TABLE_NAME + " (" + Category._ID + " INTEGER PRIMARY KEY, " +
            Category.COLUMN_NAME_CATEGORY_NAME + " VARCHAR(20), " + Category.COLUMN_NAME_CHANGE_ISACTIVE + " BOOLEAN " + ")";
    public static final String SQL_DELETE_CATEGORY = "" + "DROP TABLE IF EXISTS " + Category.TABLE_NAME;

    public static abstract class UserSettings implements BaseColumns {
        public static final String TABLE_NAME = "UserSettings";
        public static final String COLUMN_NAME_CHANGE_IMAGEM_TIME = "ChangeImageTime";
        public static final String COLUMN_NAME_SAVE_IMAGE_TOPHONE = "SaveImagemToPhone";
    }

    public static abstract class Category implements BaseColumns {
        public static final String TABLE_NAME = "Category";
        public static final String COLUMN_NAME_CATEGORY_NAME = "CategoryName";
        public static final String COLUMN_NAME_CHANGE_ISACTIVE = "IsActive";
    }

    private DatabaseSchemaHelper() {
    }
}
