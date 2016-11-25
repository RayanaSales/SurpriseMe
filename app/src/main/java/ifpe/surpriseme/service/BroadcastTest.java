package ifpe.surpriseme.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BroadcastTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Script", "onReceive()");
        Intent it = new Intent(context, ServiceTest.class);
        context.startService(intent);
    }

}

