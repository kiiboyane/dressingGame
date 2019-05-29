package com.e_mobadara.audiomanaging;


import android.Manifest;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.Database.MyDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static java.io.File.separator;

public class addAudioFile extends AppCompatActivity {

    TextView fileName ;
    Button chercherAudio, confirmer;
    AudioFile audio = new AudioFile();

    private static final int PICK_AUDIO = 100 ;
    private static final int PERMISSION_CODE = 1001 ;

    private static final String TAG = "AjouterUnFich_audio" ;

    private String outputFileDR;

    private String current_folder,
            folder_excellent = "excellent",
            folder_good = "good",
            folder_encouragement = "encouragement",
            root_folder = "Audio_Files",
            audioLangue,audioType;

    File audioFile;
    private MyDatabase dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audio_file);
        Log.d(TAG, "on create");

        if(getIntent().getExtras()!=null){
            audioLangue=getIntent().getStringExtra("langue");
            audioType=getIntent().getStringExtra("audio_type");
        }

        fileName = findViewById(R.id.file_name);
        chercherAudio = findViewById(R.id.btn_pick_audio);
        confirmer = findViewById(R.id.btn_confirmer);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);

        dbInstance = Room.databaseBuilder(this,
                MyDatabase.class, "AudioFiles")
                .allowMainThreadQueries()
                .build();

        chercherAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** ici, il faut chercher le fichier audio:
                 *lister les fichiers audio (mp3, wav, gp3)
                 *verifier le type du fichier
                 *charger ses données dans une variable "audio"
                 **/

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{
                        openGallery();
                    }
                }else{
                    openGallery();
                }
            }
        });

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Au cas de confirmation du fichier audio, on doit :
                 * verifier la présence d'un répértoire "audio files" dans "external storage",sinon créer le.
                 * Vérifier si le fichier et à l'interieur du répértoire "audio files",sinon créer le.
                 * valider l'opération et dériger l'utilisateur vers l'activité "MainActivity".
                 */
                if(audioType.equals(folder_excellent)||audioType.equals(folder_good)||audioType.equals(folder_encouragement)) {
                    current_folder = audioType;
                            if (creerFolder(current_folder)) {
                                saveFile();
                                insertData();
                            }
                    reloadActivity();
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
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void saveFile(){
        outputFileDR = Environment.getExternalStorageDirectory() +"/e-mobadara/"+
                root_folder+"/"+current_folder+"/";
        // the file to be moved or copied
        File sourceLocation = new File ( audioFile.getPath());
        // make sure your target location folder exists!
        File targetLocation = new File (outputFileDR,audioFile.getName());
        // just to take note of the location sources
        Log.v(TAG, "sourceLocation: " + sourceLocation);
        Log.v(TAG, "targetLocation: " + targetLocation);
        try {
            // make sure the target file exists
            if(sourceLocation.exists()){
                InputStream in = new FileInputStream(audioFile.getPath());
                OutputStream out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.v(TAG, "Copy file successful.");

            }else{
                Log.v(TAG, "Copy file failed. Source file missing.");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.v(TAG, "NullPointerException "+e);
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, "Exception "+e);
        }
    }

    void changeTextView(){
        fileName.setText(audio.getafName());
    }

    boolean creerFolder(String folder_name){
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/e-mobadara/" +
                root_folder + separator + folder_name);
        boolean success = true;
        if (isStoragePermissionGranted()) {
            if (!folder.exists()) {
                //Toast.makeText(this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
                success = folder.mkdirs();
            }
            if (success) {
                //Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
                return success;
            } else {
                Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
                return success;
            }
        }
        return false;
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_AUDIO);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "on acticity result");
        if (requestCode == PICK_AUDIO) {
            Log.d(TAG, "requestcode verefied");
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "resultcode verefied");
                try {
                    Log.d(TAG, ""+data.getData());
                    _getRealPathFromURI(this, data.getData());
                    audioFile = new File(audio.getafPath());
                    audio.setafPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/e-mobadara/" +
                            root_folder + separator + audioType + separator + audio.getafName());
                    changeTextView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //pick audio file
                    openGallery();
                }else{
                    Toast.makeText(getBaseContext(), "Permission denied ..", Toast.LENGTH_LONG).show();

                }

            }
        }

    }

    private void _getRealPathFromURI(Context context, Uri contentUri) {
        Log.d(TAG, " getRealPathFromURI");
        String[] proj = { MediaStore.Audio.Media.DATA,  MediaStore.Audio.Media.DISPLAY_NAME };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        Cursor cursor = loader.loadInBackground();
        if(cursor.getCount()>0 && cursor != null )
        {
            cursor.moveToFirst();
            Log.d(TAG, "cursor on the first");
            audio.setafPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            Log.d(TAG, audio.getafPath());
            audio.setafName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
            Log.d(TAG, audio.getafName());
            audio.setafLangue(audioLangue);
            audio.setafType(audioType);
            cursor.close();
        }
        else{
            cursor.close();
            Log.d(TAG, "problem with the cursor");
        }
    }
    void reloadActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("langue",audioLangue);
        startActivity(intent);
        finish();
    }

    // insert data into database
    public void insertData(){
        Log.d(TAG, "data to store ...");
        Log.d(TAG,audio.toString());

        dbInstance.AudioFileDao().addAudioFile(audio);
    }
}
