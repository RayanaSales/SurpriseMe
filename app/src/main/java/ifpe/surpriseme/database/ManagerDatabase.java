package ifpe.surpriseme.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

public class ManagerDatabase {

    public SQLiteDatabase ds;
    private FragmentActivity activity;
    private DatabaseHelper dh;
    public Cursor c;

    //usado para listar - qd se quer só o Cursor
    public ManagerDatabase(FragmentActivity activity, String tableName, String[] columns){
        if(this.activity != activity ) //para cada atividade, tem que fazer de novo
        {
            this.activity = activity;
            ds = getSQLiteDatabase(activity);
            c = ds.query(tableName, columns, "", null, "", "", "", "");
        }
    }

    //usado para inserir - qd se quer so o ds
    public ManagerDatabase(FragmentActivity activity){
        if(this.activity != activity ) //para cada atividade, tem que fazer de novo
        {
            this.activity = activity;
            ds = getSQLiteDatabase(activity);
        }
    }

    private DatabaseHelper getDatabaseHelper(FragmentActivity activity) {

        if (dh == null) {
            dh = new DatabaseHelper(activity);
        }
        return dh;
    }

    //get ds
    public SQLiteDatabase getSQLiteDatabase(FragmentActivity activity) {

        if (dh == null) {
            dh = getDatabaseHelper(activity);
        }
        return dh.getWritableDatabase();
    }
}
