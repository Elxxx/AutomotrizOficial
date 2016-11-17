package com.automotriz.automotriz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class introScrean extends AppCompatActivity {

    public static final int segundos=5;
    public static final int milisegundos=segundos*1000;
    private ProgressBar pbProgreso1;
    public static final int delay=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screan);
        pbProgreso1 =(ProgressBar)findViewById(R.id.pbProgreso);
        pbProgreso1.setMax(maximo_progreso());
        empezaranimacion();
    }


    private void empezaranimacion() {
        new CountDownTimer(milisegundos,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                pbProgreso1.setProgress(establecer_progreso(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent nuevaActividad=new Intent(introScrean.this,MenuLogin.class);
                startActivity(nuevaActividad);
                finish();
            }

        }.start();
    }

    public int establecer_progreso(long miliseconds)
    {
        return (int) ((miliseconds-miliseconds)/1000);
    }

    public int maximo_progreso()
    {
        return segundos-delay;
    }


}
