package ifpe.surpriseme.fragments;

import android.content.Context;
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

            //SALVAR
            DataOutputStream outputStream = null;
            try {
                outputStream = new DataOutputStream(getActivity().openFileOutput(categoryName_editText, Context.MODE_PRIVATE)); //nome da categoria
                outputStream.writeBoolean(false); //isSelect
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //tentando encontrar o dir do file
            Field pathField = null;
            String path = "";
            try {
                pathField = FileOutputStream.class.getDeclaredField(".");
                pathField.setAccessible(true);
                path = (String) pathField.get(outputStream);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity()," path: " + path, Toast.LENGTH_SHORT).show();

            //READING - TESTE
            DataInputStream inputStream;
            boolean valor = false;
            try {
                inputStream = new DataInputStream(getActivity().openFileInput(categoryName_editText));
                valor = inputStream.readBoolean();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity()," valor: " + valor, Toast.LENGTH_SHORT).show();
        }
    };

    //evento para tratar o botão salvar configurações
    private View.OnClickListener buttonClickSaveSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String changeTime_editText = ((EditText) getView().findViewById(R.id.changeTime_editText)).getText().toString();
            boolean saveToPhone_Switch = ((Switch) getView().findViewById(R.id.saveToPhone_Switch)).isChecked();

            DataOutputStream outputStream;
            try {
                outputStream = new DataOutputStream(getActivity().openFileOutput("ChangeTime", Context.MODE_PRIVATE)); //file name / quem acessa
                outputStream.writeUTF(changeTime_editText);

                outputStream = new DataOutputStream(getActivity().openFileOutput("SaveToPhone", Context.MODE_PRIVATE)); //file name / quem acessa
                outputStream.writeBoolean(saveToPhone_Switch);

                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //READING - test
            DataInputStream inputStream;
            try {
                inputStream = new DataInputStream(getActivity().openFileInput("ChangeTime"));
                String changeTimeValue = inputStream.readUTF();

                inputStream = new DataInputStream(getActivity().openFileInput("SaveToPhone"));
                boolean saveToPhoneValue = inputStream.readBoolean();
                inputStream.close();

                Toast.makeText(getActivity(), "changeTimeValue: " + changeTimeValue + " - saveToPhoneValue: " + saveToPhoneValue, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
