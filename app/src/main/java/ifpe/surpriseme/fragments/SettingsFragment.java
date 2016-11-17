package ifpe.surpriseme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import ifpe.surpriseme.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Button submitSettings = (Button) rootView.findViewById(R.id.submitSettings_button);
        submitSettings.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) //do what you want to do when button is clicked
    {
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
}
