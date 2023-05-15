package ru.mirea.kokorevkv.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.kokorevkv.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        final Runnable run1 = new Runnable() {
            public void run() {
                activityMainBinding.textView.setText("run1");
            }
        };
        final Runnable run2 = new Runnable() {
            public void run() {
                activityMainBinding.textView.setText("run2");

            }
        };
        final Runnable run3 = new Runnable() {
            public void run() {
                activityMainBinding.textView.setText("run3");
            }
        };
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(run1);
                    TimeUnit.SECONDS.sleep(1);
                    activityMainBinding.textView.postDelayed(run3, 2000);
                    activityMainBinding.textView.post(run2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}