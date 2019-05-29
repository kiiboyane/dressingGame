package com.e_mobadara.audiomanaging;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class chooseImportOrExportActivity extends AppCompatActivity {

    Button  import_button, export_button;
    String localy_or_server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_import_or_export);


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras()!=null){localy_or_server = getIntent().getStringExtra("WorkToDo");}

        import_button = findViewById(R.id.audio_file_import_button);
        export_button = findViewById(R.id.audio_file_export_button);

        import_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(localy_or_server.equals("localy")) {
                    Intent i = new Intent(getApplication(), localyImportActivity.class);
                    startActivity(i);
                    finish();
                }else if(localy_or_server.equals("server")){
                    Intent i = new Intent(getApplication(), ExportImportActivity.class);
                    i.putExtra("WorkToDo","import");
                    startActivity(i);
                    finish();
                }
            }
        });

        export_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(localy_or_server.equals("localy")) {
                    Intent i = new Intent(getApplication(), localyExportActivity.class);
                    startActivity(i);
                    finish();
                }else if(localy_or_server.equals("server")){
                    Intent i = new Intent(getApplication(), ExportImportActivity.class);
                    i.putExtra("WorkToDo","export");
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(this,AudioSettingsActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
