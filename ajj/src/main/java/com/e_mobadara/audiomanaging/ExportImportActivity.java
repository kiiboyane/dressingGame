package com.e_mobadara.audiomanaging;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e_mobadara.Adapter.myExpImpAdapter;
import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.utils.ApiService;
import com.e_mobadara.utils.AudioFileDTO;
import com.e_mobadara.utils.AudioList;
import com.e_mobadara.utils.ServiceGenerator;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExportImportActivity extends AppCompatActivity {

    Button buttonConfirmation;
    String activityTitle ;
    private  static final String TAG="ExportImportActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    myExpImpAdapter mAdapter;
    List<AudioFile> itemsData  = new ArrayList<AudioFile>();
    List<AudioFile> exp_imp_files = null;
    RecyclerView recyclerView;
    private String folder_excellent = "excellent", folder_good = "good",
            folder_encouragement = "encouragement",root_folder = "Audio_Files";
    private String current_folder, outputFileDR;
    File audioFile;
    private AudioFile downloadedFileData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_import);

        buttonConfirmation = findViewById(R.id.confirmation_btn);

        activityTitle = getIntent().getStringExtra("WorkToDo");
        changeTextView();

        // 1. get a reference to recyclerView
        recyclerView =  findViewById(R.id.exp_imp_my_recycler_view);
        // this is data fr  o recycler view
        /**
         * you need to add audio files here:
         * from internal storage if we are exporting
         * from external storage if we are importing
         */

        if(activityTitle.equals("export")){
            getSharedPreferencesData();
            adapter();
        }else if(activityTitle.equals("import")){
            downloadFiles(0);
        }

        /**
         * you can always add some defaults audio files here
         */


        buttonConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp_imp_files = mAdapter.getCheckedReleveStocker();
                if(activityTitle.equals("export")){
                    /**
                     * export files to the server
                     */
                    if (exp_imp_files != null) {
                        for (AudioFile i : exp_imp_files) {
                            uploadFile(i);
                        }
                        Toast.makeText(getApplicationContext(), "Audio files uploaded successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No audio files were selected. Try again",
                                Toast.LENGTH_SHORT).show();
                    }
                }else if(activityTitle.equals("import")){
                    /**
                     * import files from the server to local storage
                     */
                    if (exp_imp_files != null) {
                        for (AudioFile i : exp_imp_files) {
                            downloadedFileData = i;
                            if(i.getafType().equals(folder_excellent)||i.getafType().equals(folder_good)
                                    ||i.getafType().equals(folder_encouragement)) {
                                if(creerAppFolder()) {
                                    if(creerRootFolder()){
                                        if (creerFolder(i.getafType())) {
                                            //downloadFile(i.getAfServerId());
                                            saveSharedPreferences();
                                        }
                                    }
                                }
                                reloadActivity();
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Audio files downloaded successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No audio file wa selected. Try again",
                                Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    void adapter(){
        /*
        AudioFile i = new AudioFile("winAudioFile","fileName") ;
        AudioFile j = new AudioFile("winAudioFile2","fileName2") ;
        itemsData.add(i);
        itemsData.add(j);
        */

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        mAdapter = new myExpImpAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView = findViewById(R.id.exp_imp_my_recycler_view);
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

    void getSharedPreferencesData(){
        Map<String, ?> audio_files;
        SharedPreferences sharedPref;
        sharedPref = getSharedPreferences("good_AR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"good","AR"));
        sharedPref = getSharedPreferences("good_FR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"good","FR"));
        sharedPref = getSharedPreferences("good_TA",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"good","TA"));
        sharedPref = getSharedPreferences("excellent_FR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"excellent","FR"));
        sharedPref = getSharedPreferences("excellent_TA",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"excellent","TA"));
        sharedPref = getSharedPreferences("excellent_AR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"excellent","AR"));
        sharedPref = getSharedPreferences("encouragement_AR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"encouragement","AR"));
        sharedPref = getSharedPreferences("encouragement_FR",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"encouragement","FR"));
        sharedPref = getSharedPreferences("encouragement_TA",Activity.MODE_PRIVATE);
        audio_files = sharedPref.getAll();
        itemsData.addAll(getFilesFromInternalStorage(audio_files,"encouragement","TA"));
    }

    private List<AudioFile> getFilesFromInternalStorage(Map<String,?> audio_files,String type,String langue) {
        Iterator mymapIterator = audio_files.keySet().iterator();
        List<AudioFile> afl = new ArrayList<>();
        AudioFile af ;
        while(mymapIterator.hasNext()){
            String key=(String)mymapIterator.next();
            String path = (String)audio_files.get(key);
            Log.d(TAG, ""+path);
            af = _getRealPathFromURI(path);
            af.setafLangue(langue);
            af.setafType(type);
            if(af!=null){afl.add(af);}
        }

        return afl;
    }
    private AudioFile _getRealPathFromURI(String _path) {
        Log.d(TAG, "_getRealPathFromURI");
        AudioFile audio = new AudioFile();
        File f = new File(_path);
        audio.setafName(f.getName());
        Log.d(TAG, audio.getafName());
        audio.setafPath(_path);
        Log.d(TAG, audio.getafPath());
        return audio;
    }


    void changeTextView(){
        buttonConfirmation.setText(activityTitle);
    }

    boolean creerFolder(String folder_name){

        File folder = new File("/data/data/com.e_mobadara.audiomanaging/"+root_folder+ "/"+folder_name);
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
            return success;
        } else {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
            return success;
        }

    }
    boolean creerRootFolder(){

        File folder = new File("/data/data/com.e_mobadara.audiomanaging/"+root_folder);
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(this, "Root directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
            return success;
        } else {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
            return success;
        }

    }
    boolean creerAppFolder(){

        File folder = new File("/data/data/com.e_mobadara.audiomanaging");
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(this, "App directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            Toast.makeText(this, " App directory Created", Toast.LENGTH_SHORT).show();
            return success;
        } else {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
            return success;
        }

    }
    void saveSharedPreferences(){

        SharedPreferences sharedPref = getSharedPreferences(downloadedFileData.getafType()+"_"+
                        downloadedFileData.getafLangue(),
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(downloadedFileData.getafName(),
                "/data/data/com.e_mobadara.audiomanaging/"+root_folder+"/"+downloadedFileData.getafType()+
                        "/"+downloadedFileData.getafName());
        editor.commit();
    }

    private void uploadFile(AudioFile af) {
        // create upload service client
        ApiService service =
                ServiceGenerator.createService(ApiService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = new File ( af.getafPath());
        Log.d(TAG, ""+Uri.fromFile(file));
        Log.d(TAG, ""+encodeFile(file.getPath()));
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(okhttp3.MultipartBody.FORM
                       ,
                        encodeFile(file.getPath())
                );
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("audio_file", file.getName(), requestFile);

        // add another part within the multipart request
        RequestBody name =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, af.getafName());
        RequestBody langue =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, af.getafLangue());
        RequestBody type =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, af.getafType());

        // finally, execute the request
        Call<ResponseBody> call = service.uploadAudio(body, name, type,langue);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    void downloadFiles(int nbrOfTimes){
        ApiService downloadService = ServiceGenerator.createService(ApiService.class);
        final Call<AudioList> call = downloadService.downloadFiles(nbrOfTimes);
        Log.d(TAG, "call is been done:" + call);
        call.enqueue(new Callback<AudioList>() {
            @Override
            public void onResponse(Call<AudioList> call, Response<AudioList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server contacted and has files");
                    AudioList body = response.body();
                    Log.d(TAG, response.body() +":::"+ body.getAudiofiles() );
                    List<AudioFileDTO> audiof = body.getAudiofiles();
                    if(audiof == null){
                        Log.d(TAG, "no files from the server");
                        return;
                    }
                    for(AudioFileDTO i : audiof){
                        //AudioFile j = new AudioFile(i.getId(),i.getaName(),i.getAtype(),i.getAlangue());
                       // Log.d(TAG, "file from server : "+ j.toString());
                       // itemsData.add(j);
                    }
                } else {
                    Log.d(TAG, "server contact failed");
                }
                adapter();
            }

            @Override
            public void onFailure(Call<AudioList> call, Throwable t) {
                Log.e(TAG, "error : "+t);
            }
        });
    }

    void downloadFile(int url){
        ApiService downloadService = ServiceGenerator.createService(ApiService.class);
        final Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server contacted and has file");

                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                    Log.d(TAG, "file download was a success? " + writtenToDisk);
                } else {
                    Log.d(TAG, "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "error");
            }
        });
    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File fileToSave = new File("/data/data/com.e_mobadara.audiomanaging/"+root_folder+"/"
                    +downloadedFileData.getafType()+"/", downloadedFileData.getafName()+".wav");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(fileToSave);
                outputStream.write(Base64.decode(inputStream.toString(), Base64.DEFAULT));
                Log.d(TAG, "file download: ");
                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
    void reloadActivity(){
        Intent intent = new Intent(this,AudioSettingsActivity.class);
        intent.putExtra("WorkToDo",activityTitle);
        startActivity(intent);
        finish();
    }

    String encodeFile(String selectedPath){
        byte[] audioBytes;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(new File(selectedPath));
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();
            // Here goes the Base64 string
            return Base64.encodeToString(audioBytes, Base64.DEFAULT);
        } catch (Exception e) {
            Log.v(TAG, "Copy file successful.");
        }
        return null;
    }
}
