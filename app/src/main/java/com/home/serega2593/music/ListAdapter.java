package com.home.serega2593.music;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by serega2593 on 9/2/14.
 */
public class ListAdapter extends GrandAdapter<Model> {

    public ListAdapter(Context context, List<Model> list) {
        super(context, list, R.layout.list_row);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = getLayout();

        TextView mDirName = (TextView) rowView.findViewById(R.id.dirname_textView);
        TextView mDirs = (TextView) rowView.findViewById(R.id.dirs_textView);
        TextView mFiles = (TextView) rowView.findViewById(R.id.files_textView);
        mDirName.setText(getItem(position).getDir_name());
        mDirs.setText(getItem(position).getDirs());
        mFiles.setText(getItem(position).getFiles());

        return rowView;
    }
}
