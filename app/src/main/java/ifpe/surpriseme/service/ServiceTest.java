package ifpe.surpriseme.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceTest extends Service {
    boolean createThread = true;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Script", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("Script", "onStartCommand()");

        // Isso faz com que crie apenas umas thread;
        if(createThread)
        {
            Log.i("Script", "Criou apenas 1 ");
            Worker worker = new Worker(1);
            worker.run();
            createThread = false;
        }

        return(super.onStartCommand(intent, flags, startId));
        // START_NOT_STICKY
        // START_STICKY
        // START_REDELIVER_INTENT
    }


    class Worker extends Thread{
        public int count = 0;
        public int startId;
        public boolean ativo = true;

        public Worker(int startId){
            this.startId = startId;
        }

        public void run(){
            while(ativo && count < 1000){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                count++;
                Log.i("Script", "COUNT: "+count);
            }
            stopSelf(startId);
        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();

    }
}


