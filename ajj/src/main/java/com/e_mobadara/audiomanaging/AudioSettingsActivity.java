package com.e_mobadara.audiomanaging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AudioSettingsActivity extends AppCompatActivity {

    Button server_button, localy_button, managing_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_settings);


        server_button = findViewById(R.id.audio_file_server_button);
        localy_button = findViewById(R.id.audio_file_localy_button);
        managing_button = findViewById(R.id.audio_file_managing_button);


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

        managing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
