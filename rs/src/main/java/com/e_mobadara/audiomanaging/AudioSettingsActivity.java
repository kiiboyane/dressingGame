package com.e_mobadara.audiomanaging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AudioSettingsActivity extends AppCompatActivity {

    Button export_button, import_button, managing_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_settings);


        export_button = findViewById(R.id.audio_file_export_button);
        import_button = findViewById(R.id.audio_file_import_button);
        managing_button = findViewById(R.id.audio_file_managing_button);


        /*export_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),ExportImportActivity.class);
                i.putExtra("WorkToDo","export");
                startActivity(i);
                finish();
            }
        });


        import_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),ExportImportActivity.class);
                i.putExtra("WorkToDo","import");
                startActivity(i);
                finish();
            }
        });
        */
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
