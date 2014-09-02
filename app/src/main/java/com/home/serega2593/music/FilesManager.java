package com.home.serega2593.music;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by serega2593 on 9/2/14.
 */

public class FilesManager {

    public final File ROOT = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    private ArrayList<File> fileList = new ArrayList<File>();

    private ArrayList<File> getAll(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    fileList.add(listFile[i]);
                    getAll(listFile[i]);
                } else {
                    fileList.add(listFile[i]);
                }

            }
        }
        return fileList;
    }

    public ArrayList<File> getDirs(File dir) {
        File listFile[] = dir.listFiles();
        ArrayList<File> dirList = new ArrayList<File>();
        for (File aListFile : listFile) {
            if (aListFile.isDirectory()) {
                dirList.add(aListFile);
            }
        }
        return dirList;
    }

    public ArrayList<Model> getNumber(ArrayList<String> dirList) {
        ArrayList<Model> all = new ArrayList<Model>();
        for (String dir : dirList) {
            int[] num = filesDirsInFolder(getAll(new File(ROOT + "/" + dir)));
            Model model = new Model();
            model.setDir_name(dir);
            model.setDirs(String.valueOf(num[0]));
            model.setFiles(String.valueOf(num[1]));
            all.add(model);
        }
        return all;
    }

    private int[] filesDirsInFolder(ArrayList<File> arrayList) {
        int dir = 0;
        int files = 0;
        for (File aListFile : arrayList) {
            if (aListFile.isDirectory()) {
                dir++;
            } else files++;
        }
        int[] num = new int[2];
        num[0] = dir;
        num[1] = files;

        return num;
    }
}
