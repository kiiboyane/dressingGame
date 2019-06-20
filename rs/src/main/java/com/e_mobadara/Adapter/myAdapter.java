package com.e_mobadara.Adapter;


import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e_mobadara.Data.AudioFile;
import com.e_mobadara.audiomanaging.R;

import java.util.List;


public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<AudioFile> _itemsData;

    public myAdapter(List<AudioFile> _itemsData) {
        this._itemsData = _itemsData;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(_itemsData.get(position).getafName());
        viewHolder.txtViewDesc.setText(_itemsData.get(position).getafPath());
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;
        public TextView txtViewDesc;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = itemLayoutView.findViewById(R.id.textView);
            txtViewDesc = itemLayoutView.findViewById(R.id.text_view_desc);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return _itemsData.size();
    }

}


