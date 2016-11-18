package ifpe.surpriseme.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;

import ifpe.surpriseme.R;
import ifpe.surpriseme.database.DatabaseHelper;
import ifpe.surpriseme.database.DatabaseSchemaHelper;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Button submitSettings = (Button) rootView.findViewById(R.id.submitSettings_button);
        submitSettings.setOnClickListener(buttonClickSaveSettingsListener);

        Button saveCategorySettings = (Button) rootView.findViewById(R.id.submitCategorySettings_button);
        saveCategorySettings.setOnClickListener(buttonClickSaveCategoryListener);

        return rootView;
    }

    //evento para tratar o salvar categoria
    private View.OnClickListener buttonClickSaveCategoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            String categoryName_editText = ((EditText) getView().findViewById(R.id.category_name_edit_text)).getText().toString();


            DatabaseHelper dh = new DatabaseHelper(getActivity());
            SQLiteDatabase ds = dh.getWritableDatabase();

            String category_name_edit_text = ((EditText) getView().findViewById(R.id.category_name_edit_text)).getText().toString();

            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME, category_name_edit_text);
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE, false);

            long newId = ds.insert(DatabaseSchemaHelper.Category.TABLE_NAME, null, values);

            Toast toast = Toast.makeText(getActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
            toast.show();

        }
    };

    //evento para tratar o botão salvar configurações
    private View.OnClickListener buttonClickSaveSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // INSERT
            DatabaseHelper dh = new DatabaseHelper(getActivity());
            SQLiteDatabase ds = dh.getWritableDatabase();

            Boolean saveToPhone_boolean = ((Switch) getView().findViewById(R.id.saveToPhone_Switch)).isChecked();
            String changeTime_editText = ((EditText) getView().findViewById(R.id.changeTime_editText)).getText().toString();

            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME, changeTime_editText);
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE, saveToPhone_boolean);

            long newId = ds.insert(DatabaseSchemaHelper.UserSettings.TABLE_NAME, null, values);

            Toast toast = Toast.makeText(getActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
            toast.show();
        }
    };
}
