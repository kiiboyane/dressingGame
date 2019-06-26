package com.e_mobadara.audiomanaging;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioSettingsActivity extends AppCompatActivity {

    Button server_button, localy_button, managing_button , retour ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_settings);


        server_button = findViewById(R.id.audio_file_server_button);
        localy_button = findViewById(R.id.audio_file_localy_button);
        managing_button = findViewById(R.id.audio_file_managing_button);
        retour  = findViewById(R.id.retour);



        /*server_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),ExportImportActivity.class);
                i.putExtra("WorkToDo","server");
                startActivity(i);
                finish();
            }
        });
        */

        localy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),chooseImportOrExportActivity.class);
                i.putExtra("WorkToDo","localy");
                startActivity(i);
                finish();
            }
        });
/*
        retour.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),OpeningActivity.class);
                i.putExtra("WorkToDo","localy");
                startActivity(i);
                finish();
                startActivity(new Intent(,AudioSettingsActivity.class));

            }
        });*/

        managing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),MainAudioModuleActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    public void go(View view)
    {
        onBackPressed();
    }
}
