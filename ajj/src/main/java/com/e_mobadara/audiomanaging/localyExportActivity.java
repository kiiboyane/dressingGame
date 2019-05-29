package com.e_mobadara.audiomanaging;

import android.Manifest;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e_mobadara.Adapter.myExpImpAdapter;
import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.Database.MyDatabase;


import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class localyExportActivity extends AppCompatActivity {

    Button buttonConfirmation;
    private  static final String TAG="ExportLocalyActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    myExpImpAdapter mAdapter;
    List<AudioFile> itemsData  = new ArrayList<AudioFile>();
    List<AudioFile> imp_files = null;
    RecyclerView recyclerView;
    private String root_folder = "Audio_Files";
    private String outputFileDR;
    File audioFile;
    private AudioFile downloadedFileData;

    MyDatabase dbInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localy_export);
        buttonConfirmation = findViewById(R.id.exp_localy_confirmation_btn);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);

        dbInstance = Room.databaseBuilder(this,
                MyDatabase.class, "AudioFiles")
                .allowMainThreadQueries()
                .build();

        // 1. get a reference to recyclerView
        recyclerView =  findViewById(R.id.exp_localy_my_recycler_view);
        // this is data fr  o recycler view
        /**
         * you need to add audio files here:
         * from internal storage for exporting
         */
        adapter();

        buttonConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imp_files = mAdapter.getCheckedReleveStocker();
                for(AudioFile af:imp_files){
                    if(createFolder(af.getafType())){
                        saveFile(af);
                    }
                }
                createXMLFile(imp_files);
                showNotification();
            }
        });

    }

    private void showNotification() {
        AlertDialog ad = new AlertDialog.Builder(this)
                .create();
        ad.setCancelable(true);
        ad.setTitle("Effectuée avec succès");
        ad.setMessage("Vous pouvez accéder à ce dossier dans: "
                +Environment.getExternalStorageDirectory()+separator+"e-mobadara-exported");
        ad.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
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

    void adapter(){
        /*
        AudioFile i = new AudioFile("winAudioFile","fileName") ;
        AudioFile j = new AudioFile("winAudioFile2","fileName2") ;
        itemsData.add(i);
        itemsData.add(j);
        */
        itemsData = loadDataFromDatabase();
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        mAdapter = new myExpImpAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView = findViewById(R.id.exp_localy_my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new myExpImpAdapter(itemsData);
        mRecyclerView.setAdapter(mAdapter);
    }

    void reloadActivity(){
        Intent intent = new Intent(this,AudioSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    List<AudioFile> loadDataFromDatabase() {
        /* To query all records */
        Log.d(TAG, " fetching data from database :");
        List<AudioFile> afs = dbInstance.AudioFileDao().getAudioFiles();
        final List<AudioFile> audioFile = new ArrayList<>();
        //AudioFile e = new AudioFile(...) ;
        //dbInstance.etabDao().addAudioFile(e);
        if(afs.size()==0){
            AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(true);
            ad.setTitle("No audio files found");
            ad.setMessage(" Try to add some to the database first ");
            ad.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }
        else {
            for(AudioFile af : afs) {
                if(checkAudioFileIfExist(af.getafPath())){
                    AudioFile itm = new AudioFile(af.getafId(), af.getafName(), af.getafType(),af.getafPath(), af.getafLangue());
                    audioFile.add(itm);
                    Log.d(TAG,af.toString());
                }else{
                    dbInstance.AudioFileDao()
                            .deleteAudioFile(af);
                }
            }
        }
        return audioFile;
    }

    boolean createXMLFile(List<AudioFile> _audioFile){

        File newxmlfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/e-mobadara-exported/audioFilesData.xml");
            try{
            newxmlfile.createNewFile();
        }catch(IOException e)
        {
            Log.e("IOException", "Exception in create new File :(");
            return false;
        }
        FileOutputStream fileos = null;
        try{
            fileos = new FileOutputStream(newxmlfile);

        }catch(FileNotFoundException e)
        {
            Log.e("FileNotFoundException:(",e.toString());
            return false;
        }
        XmlSerializer serializer = Xml.newSerializer();
        try{
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "audiofiles");
            for(AudioFile af : _audioFile) {

                serializer.startTag(null, "audiofile");
                serializer.attribute(null, "name", af.getafName());
                serializer.attribute(null, "type", af.getafType());
                serializer.attribute(null, "langue", af.getafLangue());
                serializer.endTag(null, "audiofile");

            }
            serializer.endTag(null, "audiofiles");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
            //TextView tv = (TextView)findViewById(R.);
        }catch(Exception e)
        {
            Log.e("Exception","Exception occured in wroting");
            return false;
        }
        return true;
    }

    boolean createFolder(String folder_name){
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/e-mobadara-exported/" +
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

    void saveFile(AudioFile audiofile){
        OutputStream out = null;
        InputStream in =null;
        outputFileDR = Environment.getExternalStorageDirectory() +"/e-mobadara-exported/"+
                root_folder+"/"+audiofile.getafType()+"/";
        // the file to be moved or copied
        File sourceLocation = new File ( audiofile.getafPath());
        // make sure your target location folder exists!
        File targetLocation = new File (outputFileDR,audiofile.getafName());
        // just to take note of the location sources
        Log.v(TAG, "sourceLocation: " + sourceLocation);
        Log.v(TAG, "targetLocation: " + targetLocation);
        try {
            // make sure the target file exists
            if(sourceLocation.exists()){
                in = new FileInputStream(audiofile.getafPath());
                out = new FileOutputStream(targetLocation);
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
    boolean checkAudioFileIfExist(String folder_path){
        File folder = new File( folder_path );
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(this, "audio-file Does Not Exist...", Toast.LENGTH_SHORT).show();
            success = false;
        }
        //Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
        return success;
    }
}
