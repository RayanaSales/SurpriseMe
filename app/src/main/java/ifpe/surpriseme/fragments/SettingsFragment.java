package ifpe.surpriseme.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import ifpe.surpriseme.R;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;
import ifpe.surpriseme.service.ServiceTest;
import ifpe.surpriseme.time.MyCountDownTimer;

public class SettingsFragment extends Fragment {

    private Spinner timeSpinner;
    public EditText editText_frequency;
    public static String changeTime_editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Button submitSettings = (Button) rootView.findViewById(R.id.submitSettings_button);
        submitSettings.setOnClickListener(buttonClickSaveSettingsListener);

        Button saveCategorySettings = (Button) rootView.findViewById(R.id.submitCategorySettings_button);
        saveCategorySettings.setOnClickListener(buttonClickSaveCategoryListener);

        timeSpinner = (Spinner) rootView.findViewById(R.id.selectTime_spinner);
        timeSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        return rootView;
    }



    //evento para tratar o salvar categoria
    // A CATEGORIA PODE SER SALVA VÁRIAS VEZES.
    private View.OnClickListener buttonClickSaveCategoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String categoryName_editText = ((EditText) getView().findViewById(R.id.category_name_edit_text)).getText().toString();

            ManagerDatabase md = new ManagerDatabase(getActivity());

            String category_name_edit_text = ((EditText) getView().findViewById(R.id.category_name_edit_text)).getText().toString();

            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME, category_name_edit_text);
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE, false);

            long newId = md.ds.insert(DatabaseSchemaHelper.Category.TABLE_NAME, null, values);

            Toast toast = Toast.makeText(getActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
            toast.show();

        }
    };

    //evento para tratar o botão salvar configurações
    // JÁ AS CONFIGURAÇÕES UMA VEZ SALVAS, APENAS SERÃO EDITADAS.
    private View.OnClickListener buttonClickSaveSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] columns = {DatabaseSchemaHelper.UserSettings._ID};
            ManagerDatabase md = new ManagerDatabase(getActivity(), DatabaseSchemaHelper.UserSettings.TABLE_NAME, columns);

            Boolean saveToPhone_boolean = ((Switch) getView().findViewById(R.id.saveToPhone_Switch)).isChecked();
            String changeTime_editText = ((EditText) getView().findViewById(R.id.changeTime_editText)).getText().toString();

            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME, changeTime_editText);
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE, saveToPhone_boolean);
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_FREQUENCY, CustomOnItemSelectedListener.frequency);

            if (md.c.getCount() == 0) {
                long newId = md.ds.insert(DatabaseSchemaHelper.UserSettings.TABLE_NAME, null, values);

                Toast toast = Toast.makeText(getActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                long newId = md.ds.update(DatabaseSchemaHelper.UserSettings.TABLE_NAME, values, DatabaseSchemaHelper.UserSettings._ID + " =  1", null);

                Toast toast = Toast.makeText(getActivity(), "Registro atualizado. ID = " + newId, Toast.LENGTH_SHORT);
                toast.show();

            }

        }
    };

}

class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public static String frequency;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //Toast.makeText(parent.getContext(), "Frequencia selecionada: " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        this.frequency = parent.getItemAtPosition(pos).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}