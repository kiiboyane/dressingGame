package fragments;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import com.e_mobadara.Adapter.myAdapter;
import com.e_mobadara.Data.AudioFile;
import com.e_mobadara.Data.AudioFilesTable;
import com.e_mobadara.audiomanaging.MainActivity;
import com.e_mobadara.audiomanaging.R;
import com.e_mobadara.audiomanaging.addAudioFile;
import com.e_mobadara.utils.RecyclerItemClickListener;

import java.io.File;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_excellent, container, false);

        Log.d(TAG, " inside_oncreateView");

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
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        Log.d(TAG, "RecyclerItemClickListener");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.d(TAG, " onLongItemClick");
                        _position = position;
                        /**
                         * ici on va impléménter le code de suppression d'un fichier audio séléctionné
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
                                File fileToDelete = new File (itemsData.get(_position).getafPath());
                                Log.d(TAG, itemsData.get(_position).getafPath());
                                fileToDelete.delete();
                                ContentResolver cr = getActivity().getContentResolver();
                                String[] crParam = {i.getAfId()};
                                cr.delete(AudioFilesTable.AudioFilesEntry.CONTENT_URI,"id = ?",crParam );
                                reloadActivity();
                                dialog.dismiss();
                            }
                        });
                        ad.show();
                    }

                }));


        return view;
    }

    void reloadActivity(){
        Intent intent = new Intent(getContext(),MainActivity.class);
        intent.putExtra("langue",MainActivity.getLangue());
        startActivity(intent);
        getActivity().finish();
    }
    List<AudioFile> loadDataFromDatabase() {
        /* To query all records */
        String[] crParam = {current_folder, MainActivity.getLangue()};
        Cursor cursor = getActivity().getContentResolver()
                .query(AudioFilesTable.AudioFilesEntry.CONTENT_URI,null,"type = ? and langue = ?",crParam,null);
        List<AudioFile> afl = new ArrayList<AudioFile>();
        AudioFile af = new AudioFile();
        Log.d(TAG, " fetching data from database :"+cursor.getCount());
        if( cursor != null && cursor.getCount()>0) {
            // Loop in the cursor to get each row.
            do {
                cursor.moveToFirst();
                // Get columns values.
                Log.d(TAG, " cursor");
                Log.d(TAG, cursor.getString(cursor.getColumnIndex("name")));
                af.setafName(cursor.getString(cursor.getColumnIndex("name")));
                af.setafType(cursor.getString(cursor.getColumnIndex("type")));
                af.setafLangue(cursor.getString(cursor.getColumnIndex("langue")));
                af.setafPath(cursor.getString(cursor.getColumnIndex("path")));
                af.setAfId(cursor.getString(cursor.getColumnIndex("id")));
                afl.add(af);
                Log.d(TAG,af.toString());
            } while (cursor.moveToNext());
        }
        return afl;
    }

}
