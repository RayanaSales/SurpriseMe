package ifpe.surpriseme.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import ifpe.surpriseme.Model.ApplicationSingleton;
import ifpe.surpriseme.controllers.BackgroundController;

public class ChangeBackgroundOffline extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {

        //um alarme eh disparado e me chama de acordo com o tempo definido em bg controller
        BackgroundController bg = new BackgroundController();
        bg.manageSwapBackground();
    }
}