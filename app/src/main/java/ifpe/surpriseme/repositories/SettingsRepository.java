package ifpe.surpriseme.repositories;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import ifpe.surpriseme.Model.PhoneSettings;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;

public class SettingsRepository {

    private static SettingsRepository settingsRepository;

    public static SettingsRepository getSettingsRepository() {
        if (settingsRepository == null)
            settingsRepository = new SettingsRepository();

        return settingsRepository;
    }

    public ArrayList<PhoneSettings> list(FragmentActivity activity) {

        ArrayList<PhoneSettings> list_settings = new ArrayList<PhoneSettings>();

        String[] columns = {DatabaseSchemaHelper.UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE, DatabaseSchemaHelper.UserSettings.COLUMN_NAME_FREQUENCY,
                DatabaseSchemaHelper.UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME};
        ManagerDatabase md = new ManagerDatabase(activity, DatabaseSchemaHelper.UserSettings.TABLE_NAME, columns);

        while (md.c.moveToNext()) {
            String timeFrequency = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_FREQUENCY)); //time
            String valueFrequency = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_CHANGE_IMAGEM_TIME)); //value
            Boolean saveToPhone = md.c.getInt(md.c.getColumnIndex(DatabaseSchemaHelper.UserSettings.COLUMN_NAME_SAVE_IMAGE_TOPHONE)) > 0;

            PhoneSettings settings = new PhoneSettings(valueFrequency, timeFrequency, saveToPhone);
            list_settings.add(settings);
        }

        return list_settings;
    }
}
