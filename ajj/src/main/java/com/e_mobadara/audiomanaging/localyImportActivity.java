package com.e_mobadara.audiomanaging;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static java.io.File.separator;

public class localyImportActivity extends AppCompatActivity {

    TextView fileName ;
    Button chercherAudio, confirmer;
    AudioFile audio = new AudioFile();
    private static final int REQUEST_DIRECTORY = 0;


    private static final int PICK_AUDIO = 100 ;
    private static final int PERMISSION_CODE = 1001 ;

    private static final String TAG = "importDirectory" ;

    private String outputFileDR;

    private String current_folder,
            folder_excellent = "excellent",
            folder_good = "good",
            folder_encouragement = "encouragement",
            root_folder = "Audio_Files",
            importedDirectoryPath;
    ProgressDialog pDialog ;
    private static final int CURSOR_LOADER_ID = 0;
    LoadData task;
    private MyDatabase dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localy_import);
        Log.d(TAG, "on create localyImportActivity");

        fileName = findViewById(R.id.folder_name);
        chercherAudio = findViewById(R.id.btn_pick_folder);
        confirmer = findViewById(R.id.btn_confirmer_folder_importation);

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
                /**
                 *ici, il faut chercher le dossier "e-mobadara" :
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
                 * Au cas de confirmation du dossier qui contient les fichiers audio par l'utilisateur, on doit :
                 * Vérifier le nom du dossier "e-mobadara"
                 * verifier la présence d'un fihcier XML "audioData" dans ce dossier.
                 * verifier la présence d'un répértoire "audio files" dans ce dossier.
                 * Vérifier que les fichiers audio qui sont dans le fichier XML existent dans le répértoire "audio files".
                 * Ajouter ces fichiers audio dans la base de données.
                 * valider l'opération et dériger l'utilisateur vers l'activité "AudioSettingsActivity".
                 */
                if(TestImportedFolderContent(importedDirectoryPath)){
                    task  = new LoadData();
                    task.execute();
                }
                else{
                    showInfos(getApplication(),"No audio files data in that directory :(");
                }

            }
        });
    }
    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    private void showProgressDialog() {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
        }
        pDialog.show();
    }

    private void dismissProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    class LoadData extends AsyncTask<String, String, String> {

        // Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        //getting All products from url
        protected String doInBackground(String... args) {
            importDataFromDirectory();
            return null;
        }

        // After completing background task Dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            if (localyImportActivity.this.isDestroyed()) { // or call isFinishing() if min sdk version < 17
                return;
            }
            dismissProgressDialog();
            reloadActivity();
            task.cancel(true);
        }
    }

    void importDataFromDirectory(){
            ArrayList<AudioFile> files = parseXML(importedDirectoryPath+ separator + "audioFilesData.xml" );
            Log.d("size:",files.toString());
            if(!(files==null)){
                for(AudioFile af:files){
                    Log.d("nbrOfFilesStored:","This is one of them");
                    String audio_path = importedDirectoryPath + separator + root_folder +
                            separator +af.getafType()+separator + af.getafName() ;
                    String targetDestination = Environment.getExternalStorageDirectory() +"/e-mobadara/"+
                            root_folder+ separator +af.getafType()+separator + af.getafName();
                    Log.d("hello errors", audio_path);
                    if( checkAudioFileIfExist(audio_path)){
                        af.setafPath(targetDestination);
                        if( af.getafType().equals(folder_excellent)||
                                af.getafType().equals(folder_good)||
                                af.getafType().equals(folder_encouragement)) {
                            if(af.getafLangue().equals("AR")||af.getafLangue().equals("FR")||af.getafLangue().equals("AN")
                                    ||af.getafLangue().equals("DA")||af.getafLangue().equals("TA")){
                                if (creerFolder(af.getafType())) {
                                    if(!checkAudioFileIfExist(targetDestination)){
                                        saveFile(af, audio_path);}
                                    insertData(af);
                                }
                            }
                        }
                    }
                }
            }else{

            }
    }
    void showInfos(Context c, String info){
        Toast.makeText(c, info, Toast.LENGTH_LONG).show();
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

    void saveFile(AudioFile audiofile, String targetSrc){
        OutputStream out = null;
        InputStream in = null;
        outputFileDR = Environment.getExternalStorageDirectory() +"/e-mobadara/"+
                root_folder+ separator +audiofile.getafType()+ separator;
        // the file to be moved or copied
        File sourceLocation = new File ( targetSrc);
        // make sure your target location folder exists!
        File targetLocation = new File (outputFileDR,audiofile.getafName());
        // just to take note of the location sources
        Log.v(TAG, "sourceLocation: " + sourceLocation);
        Log.v(TAG, "targetLocation: " + targetLocation);
        try {
            // make sure the target file exists
            if(sourceLocation.exists()){
                in = new FileInputStream(targetSrc);
                out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                Log.v(TAG, "Copy file successful.");
                in.close();
                out.close();
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
                //Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
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
        final Intent chooserIntent = new Intent(
                localyImportActivity.this,
                DirectoryChooserActivity.class);

        final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                .newDirectoryName("DirChooserSample")
                .allowReadOnlyDirectory(true)
                .allowNewDirectoryNameModification(true)
                .build();

        chooserIntent.putExtra(
                DirectoryChooserActivity.EXTRA_CONFIG,
                config);

        startActivityForResult(chooserIntent, REQUEST_DIRECTORY);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "on acticity result");
        if (requestCode == REQUEST_DIRECTORY) {
            Log.i(TAG, String.format("Return from DirChooser with result %d",
                    resultCode));

            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                importedDirectoryPath = data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);
                fileName
                        .setText(importedDirectoryPath);
            } else {
                fileName.setText("nothing selected");
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

    void reloadActivity(){
        Intent intent = new Intent(this,AudioSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    // insert data into database
    public void insertData(AudioFile audioFile){
        Log.d(TAG, "data to store ...");
        Log.d(TAG,audio.toString());

        dbInstance.AudioFileDao().addAudioFile(audioFile);
    }

    boolean TestImportedFolderContent(String folder_name){
        return TestXMLFileIfExist(folder_name) && TestAudioFilesFolderIfExist(folder_name);
    }

    boolean TestXMLFileIfExist(String folder_name){
        Log.d("HELLO ERRORS", folder_name + separator +  "audioFilesData.xml" );
        File folder = new File(folder_name + separator +  "audioFilesData.xml" );
        boolean success = true;
        if (isStoragePermissionGranted()) {
            if (!folder.exists()) {
                //Toast.makeText(this, "XML file Does Not Exist...", Toast.LENGTH_SHORT).show();
                success = false;
            }
            if (success) {
                return success;
            } else {
                //Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
                return success;
            }
        }
        return false;
    }

    boolean TestAudioFilesFolderIfExist(String folder_name){
        File folder = new File( folder_name + separator + root_folder + separator  );
        boolean success = true;
        if (isStoragePermissionGranted()) {
            if (!folder.exists()) {
                //Toast.makeText(this, "audio-files folder Does Not Exist...", Toast.LENGTH_SHORT).show();
                success = false;
            }
            if (success) {
                return success;
            } else {
                //Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
                return success;
            }
        }
        return false;
    }

    boolean checkAudioFileIfExist(String folder_path){
        File folder = new File( folder_path );
        boolean success = true;
        if (isStoragePermissionGranted()) {
            if (!folder.exists()) {
                //Toast.makeText(this, "audio-file Does Not Exist...", Toast.LENGTH_SHORT).show();
                success = false;
            }
            if (success) {
                return success;
            } else {
                //Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
                return success;
            }
        }
        return false;
    }

    private ArrayList<AudioFile> parseXML(String path) {
        XmlPullParserFactory parserFactory;
        InputStream is = null;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            Log.d("hello errors", " processingXMLParsing:"+path);
            is = new BufferedInputStream(new FileInputStream(path));
            Log.d("hello errors", " after:"+path);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            return processParsingXMLFile(parser);

        } catch (XmlPullParserException e) {
            Log.d("hello errors", "inside checkAudioFileIfExist XmlPullParserException");
        } catch (IOException e) {
            Log.d("hello errors", "inside checkAudioFileIfExist IOException");
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null ;
    }

    private ArrayList<AudioFile> processParsingXMLFile(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<AudioFile> audioFiles = new ArrayList<>();
        int eventType = parser.getEventType();
        AudioFile afile = null;
        Log.d("hello errors", "inside processParsingXMLFile ");
        while (eventType != XmlPullParser.END_DOCUMENT) {
            Log.d("hello errors", "inside while ");
            switch (eventType) {
                case XmlPullParser.START_TAG: // getting the xml element name
                    if("audiofile".equals(parser.getName())){
                            afile = new AudioFile();
                            afile.setafName(parser.getAttributeValue(null, "name"));
                            afile.setafType(parser.getAttributeValue(null, "type"));
                            afile.setafLangue(parser.getAttributeValue(null, "langue"));
                            audioFiles.add(afile);
                        }
                    break;
            }
            eventType = parser.next();
        }
        return audioFiles;
    }
}
