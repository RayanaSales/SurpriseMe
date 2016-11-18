package ifpe.surpriseme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static ifpe.surpriseme.database.DatabaseSchemaHelper.DATABASE_NAME;
import static ifpe.surpriseme.database.DatabaseSchemaHelper.DATABASE_VERSION;
import static ifpe.surpriseme.database.DatabaseSchemaHelper.SQL_CREATE_CATEGORY;
import static ifpe.surpriseme.database.DatabaseSchemaHelper.SQL_CREATE_USERSETTINGS;
import static ifpe.surpriseme.database.DatabaseSchemaHelper.SQL_DELETE_CATEGORY;
import static ifpe.surpriseme.database.DatabaseSchemaHelper.SQL_DELETE_USERSETTINGS;

/**
 * Created by Rayana on 11/17/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERSETTINGS);
        db.execSQL(SQL_CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USERSETTINGS);
        db.execSQL(SQL_DELETE_CATEGORY);
        this.onCreate(db);
    }
}