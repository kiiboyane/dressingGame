package fragments;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.e_mobadara.Adapter.myAdapter;
import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.Database.MyDatabase;
import com.e_mobadara.audiomanaging.MainActivity;
import com.e_mobadara.audiomanaging.R;
import com.e_mobadara.audiomanaging.addAudioFile;
import com.e_mobadara.utils.RecyclerItemClickListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcellentActivity extends Fragment {
    private  static final String TAG="excellentActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    List<AudioFile> itemsData  = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    private String current_folder = "excellent";
    private int _position;

    private static final int CURSOR_LOADER_ID = 0;
    private MyDatabase dbInstance;

    private MediaPlayer mp = new MediaPlayer();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_excellent, container, false);

        Log.d(TAG, " inside_oncreateView");

        dbInstance = Room.databaseBuilder(view.getContext(),
                MyDatabase.class, "AudioFiles")
                .allowMainThreadQueries()
                .build();

        fab = view.findViewById(R.id.excellent_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add", Snackbar.LENGTH_LONG)
                        .setAction("Add audio file", null).show();
                /**
                 * ici il faut appaler l'activité qui va ajouter un nouveau fichier audio
                 */
                Intent intent = new Intent(getContext(),addAudioFile.class);
                intent.putExtra("audio_type",current_folder);
                intent.putExtra("langue",MainActivity.getLangue());
                startActivity(intent);
                getActivity().finish();
            }
        });


        // 1. get a reference to recyclerView
        recyclerView =  view.findViewById(R.id.excellent_my_recycler_view);
        // this is data fr  o recycler view
        /**
         * you need to add audio files here.
         */


        itemsData = loadDataFromDatabase();

        /**
         * you can always add some defaults audio files here
         */
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. create an adapter
        myAdapter mAdapter = new myAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView = view.findViewById(R.id.excellent_my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new myAdapter(itemsData);
        mRecyclerView.setAdapter(mAdapter);
        recyclerView
                .addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, final int position) {
                                Log.d(TAG, "RecyclerItemClickListener");
                                _position = position;
                                if (mp.isPlaying()) {
                                    Log.d(TAG, "isplaying");
                                    if (mp != null) {
                                        Log.d(TAG, "stoping");
                                        mp.stop();
                                        mp.reset();
                                        //mPlayPause.setImageResource(R.drawable.ic_play);
                                    }
                                } else {
                                    Log.d(TAG, "starting");
                                    playSong(itemsData.get(_position).getafPath());
                                    //mPlayPause.setImageResource(R.drawable.ic_stop);
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.d(TAG, " onLongItemClick");
                                _position = position;
                                /**
                                 * ici on va impléménter le code de suppression d'un fichier audio séléctionné, on va
                                 * supprimer de la base de données, mais dans le dossier e-mobadara il faut tester tout d'abord
                                 * si ce fichier audio est indexé.
                                 */
                                AlertDialog ad = new AlertDialog.Builder(getContext())
                                        .create();
                                ad.setCancelable(true);
                                ad.setTitle("Delete audio file");
                                ad.setMessage("Are you sure");
                                ad.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        AudioFile i  = itemsData.get(_position);
                                        Log.d(TAG, " deleting audio file.");
                                        if(!checkIfAdioFileIsUsed(i)) {
                                            File fileToDelete = new File (i.getafPath());
                                            Log.d(TAG, itemsData.get(_position).getafPath());
                                            boolean success = fileToDelete.delete();
                                            Log.d(TAG, success + " : deleted file");
                                        }
                                        /* To query all records */
                                        Log.d(TAG, " delete data from database :");
                                        dbInstance.AudioFileDao()
                                                .deleteAudioFile(i);
                                        mp.release();
                                        reloadActivity();
                                    }
                                });
                                ad.show();
                            }
                        }));
        return view;
    }

    private boolean checkIfAdioFileIsUsed(AudioFile i) {
        /* To query all records */
        Log.d(TAG, " fetching data from database :");
        List<AudioFile> afs = dbInstance.AudioFileDao().getAudioFiles();
        //AudioFile e = new AudioFile(...) ;
        //dbInstance.etabDao().addAudioFile(e);
        for(AudioFile af : afs) {
            return true;
        }
        return false;
    }

    void reloadActivity(){
        Intent intent = new Intent(getContext(),MainActivity.class);
        intent.putExtra("langue",MainActivity.getLangue());
        startActivity(intent);
        getActivity().finish();
    }
    List<AudioFile> loadDataFromDatabase() {
        /* To query all records */
        Log.d(TAG, " fetching data from database :");
        List<AudioFile> afs = dbInstance.AudioFileDao().getAudioFilesType(current_folder, MainActivity.getLangue());
        final List<AudioFile> audioFile = new ArrayList<>();
        //AudioFile e = new AudioFile(...) ;
        //dbInstance.etabDao().addAudioFile(e);
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
        return audioFile;
    }

    private void playSong(String songPath) {
        try {
            mp.reset();
            mp.setDataSource(songPath);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            Log.v(getString(R.string.app_name), e.getMessage());
        }
    }

    boolean checkAudioFileIfExist(String folder_path){
        File folder = new File( folder_path );
        boolean success = true;
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

}
