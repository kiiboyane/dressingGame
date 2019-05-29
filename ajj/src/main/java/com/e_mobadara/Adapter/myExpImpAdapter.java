package com.e_mobadara.Adapter;


import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.audiomanaging.R;

import java.util.ArrayList;
import java.util.List;


public class myExpImpAdapter extends RecyclerView.Adapter<myExpImpAdapter.ViewHolder> {
    private List<AudioFile> _itemsData;
    private List<AudioFile> checkedReleveStocker = new ArrayList<>();

    public myExpImpAdapter(List<AudioFile> _itemsData) {
        this._itemsData = _itemsData;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public myExpImpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row_check, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(_itemsData.get(position).getafName());
        viewHolder.txtViewDesc.setText("Langue: "+ _itemsData.get(position).getafLangue()
                + " | type: " + _itemsData.get(position).getafType());
        viewHolder.check_box.setFocusable(false);
        viewHolder.check_box.setFocusableInTouchMode(false);
        viewHolder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    checkedReleveStocker.add(_itemsData.get(position));
                else
                    checkedReleveStocker.remove(_itemsData.get(position));
            }
        });
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;
        public TextView txtViewDesc;
        public CheckBox check_box;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = itemLayoutView.findViewById(R.id.textView);
            txtViewDesc = itemLayoutView.findViewById(R.id.text_view_desc);
            check_box = itemLayoutView.findViewById(R.id.checkbox);

        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return _itemsData.size();
    }

    public List<AudioFile> getCheckedReleveStocker() {
        return checkedReleveStocker;
    }

}


