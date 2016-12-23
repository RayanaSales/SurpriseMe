package ifpe.surpriseme.services;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import ifpe.surpriseme.R;
import ifpe.surpriseme.activities.MainActivity;

/**
 * Created by Rayana on 12/21/2016.
 */
public class ChangeBackgroundOffline extends IntentService
{
    public ChangeBackgroundOffline() {
        super("ChangeBackgroundOffline");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(Intent intent, String msg) {

        // Crio o Intent que será associado com o toque na notificação
        // esse Intent irá lançar a aplicação novamente.
        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(getApplicationContext(), 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //constroi notificação
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("STATTUS DOWNLOAD PDF:");
        builder.setContentText("Completo...");
        builder.setContentIntent(pendingNotificationIntent);
        builder.setAutoCancel(true);
        Notification notification = builder.build();

        // Obter o gerenciador de notificações do sistema e exibe a notificação
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
