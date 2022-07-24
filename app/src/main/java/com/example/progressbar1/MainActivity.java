package com.example.progressbar1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int a = 0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview1);
        progressBar=findViewById(R.id.progressbar);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = progressBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (a<100){
                            a+=1;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(a);
                                    textView.setText(a+"/"+progressBar.getMax());
                                    if (a==100)
                                        textView.setText("Progress Completed");
                                }
                            });
                            try {
                                Thread.sleep(50);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}