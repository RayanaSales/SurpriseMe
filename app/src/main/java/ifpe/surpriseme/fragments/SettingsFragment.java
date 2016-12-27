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

import java.util.ArrayList;

import ifpe.surpriseme.Model.ApplicationSingleton;
import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.Model.PhoneSettings;
import ifpe.surpriseme.R;
import ifpe.surpriseme.controllers.BackgroundController;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;
import ifpe.surpriseme.repositories.SettingsRepository;

public class SettingsFragment extends Fragment {

    private Spinner timeSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialize
        ApplicationSingleton.setCurrentActivity(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Button submitSettings = (Button) rootView.findViewById(R.id.submitSettings_button);
        submitSettings.setOnClickListener(buttonClickSaveSettingsListener);

        Button saveCategorySettings = (Button) rootView.findViewById(R.id.submitCategorySettings_button);
        saveCategorySettings.setOnClickListener(buttonClickSaveCategoryListener);

        timeSpinner = (Spinner) rootView.findViewById(R.id.selectTime_spinner);
        timeSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        initSettingsFragment(rootView);

        return rootView;
    }

    private void initSettingsFragment(View rootView) {

//        Switch saveToPhone_Switch = (Switch) rootView.findViewById(R.id.saveToPhone_Switch);
        EditText changeTime_editText = (EditText) rootView.findViewById(R.id.changeTime_editText);
        Spinner selectTime_spinner = (Spinner) rootView.findViewById(R.id.selectTime_spinner);

        ArrayList<PhoneSettings> list_settings = SettingsRepository.getSettingsRepository().list(getActivity());

        if (!list_settings.isEmpty()) {

//            if (list_settings.get(0).isSaveOnDevice() == true) {
//                saveToPhone_Switch.setChecked(true);
//            } else saveToPhone_Switch.setChecked(false);

            changeTime_editText.setText(list_settings.get(0).getValueFrequency());

            switch (list_settings.get(0).getTimeFrequency()) {
                case "Minutes":
                    selectTime_spinner.setSelection(0);
                    break;
                case "Hours":
                    selectTime_spinner.setSelection(1);
                    break;
                case "Days":
                    selectTime_spinner.setSelection(2);
                    break;
            }
        }
    }

    //evento para tratar o salvar categoria
    // A CATEGORIA PODE SER SALVA VÁRIAS VEZES.
    private View.OnClickListener buttonClickSaveCategoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ManagerDatabase md = new ManagerDatabase(getActivity());

            String category_name_edit_text = ((EditText) getView().findViewById(R.id.category_name_edit_text)).getText().toString();

            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME, category_name_edit_text);
            values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE, false);

            long newId = md.ds.insert(DatabaseSchemaHelper.Category.TABLE_NAME, null, values);

            Toast toast = Toast.makeText(getActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
            toast.show();

            Category category = new Category();
            category.setName(category_name_edit_text);
            category.setId(newId);

            Intent intent = new Intent(CategoriesFragment.UPDATE_LIST);
            intent.putExtra(CategoriesFragment.NEW_CATEGORY, category);

            if (getActivity() != null) {
                getActivity().sendBroadcast(intent);
            }

        }
    };

    //evento para tratar o botão salvar configurações
    // JÁ AS CONFIGURAÇÕES UMA VEZ SALVAS, APENAS SERÃO EDITADAS.
    private View.OnClickListener buttonClickSaveSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String[] columns = {DatabaseSchemaHelper.UserSettings._ID};
            ManagerDatabase md = new ManagerDatabase(getActivity(), DatabaseSchemaHelper.UserSettings.TABLE_NAME, columns);

            //Boolean saveToPhone_boolean = ((Switch) getView().findViewById(R.id.saveToPhone_Switch)).isChecked();
            String changeTime_editText = ((EditText) getView().findViewById(R.id.changeTime_editText)).getText().toString();


            ContentValues values = new ContentValues();
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME, changeTime_editText);
          //  values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE, saveToPhone_boolean);
            values.put(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_FREQUENCY, CustomOnItemSelectedListener.frequency);

            if (md.c.getCount() == 0) {
                long newId = md.ds.insert(DatabaseSchemaHelper.UserSettings.TABLE_NAME, null, values);

                Toast toast = Toast.makeText(ApplicationSingleton.getCurrentActivity(), "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                long newId = md.ds.update(DatabaseSchemaHelper.UserSettings.TABLE_NAME, values, DatabaseSchemaHelper.UserSettings._ID + " =  1", null);

                Toast toast = Toast.makeText(ApplicationSingleton.getCurrentActivity(), "Registro atualizado. ID = " + newId, Toast.LENGTH_SHORT);
                toast.show();

            }

            ApplicationSingleton.setBackgroundController(new BackgroundController(CustomOnItemSelectedListener.frequency,changeTime_editText));
            ApplicationSingleton.getBackgroundController().manageSwapBackground();
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