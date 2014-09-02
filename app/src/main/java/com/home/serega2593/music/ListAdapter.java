package com.home.serega2593.music;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by serega2593 on 9/2/14.
 */
public class ListAdapter extends GrandAdapter<File> {


    public ListAdapter(Context context, List<File> list) {
        super(context, list, R.layout.list_row);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = getLayout();

        TextView mDirName = (TextView) rowView.findViewById(R.id.dirname_textView);
        TextView mDirs = (TextView) rowView.findViewById(R.id.dirs_textView);
        TextView mFiles = (TextView) rowView.findViewById(R.id.files_textView);


        getNumber(getItem(position));
        mDirName.setText(getItem(position).getName());
        mDirs.setText(folder);
        mFiles.setText(files);

        return rowView;
    }

    int folder = 0;
    int files = 0;
    private void getNumber(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    folder++;
                    getNumber(listFile[i]);
                } else {
                    files++;
                }

            }
        }
    }
}
