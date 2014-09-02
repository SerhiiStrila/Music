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

        View rowView = view;
        ViewHolder h = new ViewHolder();
        if (view == null || view.getTag() == null) {
            rowView = getLayout();
            h.mDirName = (TextView) rowView.findViewById(R.id.dirname_textView);
            h.mDirs = (TextView) rowView.findViewById(R.id.dirs_textView);
            h.mFiles = (TextView) rowView.findViewById(R.id.files_textView);
            rowView.setTag(h);
        }

        h = (ViewHolder) rowView.getTag();
        Model model = getNumber(getItem(position));
        h.mDirName.setText(getItem(position).getName());
        h.mDirs.setText(String.valueOf(model.folders));
        h.mFiles.setText(String.valueOf(model.files));

        return rowView;
    }

    private Model getNumber(File dir) {
        int folder = 0;
        int files = 0;
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File aListFile : listFile) {
                if (aListFile.isDirectory()) {
                    folder++;
                    Model model = getNumber(aListFile);
                    folder += model.folders;
                    files += model.files;
                } else {
                    files++;
                }

            }
        }
        return new Model(folder, files);
    }

    static class ViewHolder {
        public TextView mDirName;
        public TextView mDirs;
        public TextView mFiles;
    }
}

