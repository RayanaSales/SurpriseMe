package ifpe.surpriseme.time;

import android.os.CountDownTimer;

import ifpe.surpriseme.fragments.CurrentBackgroundFragment;

public class MyCountDownTimer extends CountDownTimer {

   public MyCountDownTimer(long timeFuture, long interval) {
       super(timeFuture, interval);
   }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish()
    {
    }
}
