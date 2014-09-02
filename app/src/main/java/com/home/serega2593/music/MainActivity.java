package com.home.serega2593.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListAdapter mAdapter;
    private final File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, PlayService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(intent);

        Button mStart = (Button) findViewById(R.id.start_button);
        Button mStop = (Button) findViewById(R.id.stop_button);
        ListView mListView = (ListView) findViewById(R.id.folder_listView);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);

        mAdapter = new ListAdapter(this, getDirs(root));
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, PlayService.class));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("BROADCAST_ACTION");
        switch (v.getId()) {
            case R.id.start_button:
                intent.putExtra("play", true);
                sendBroadcast(intent);
                break;
            case R.id.stop_button:
                intent.putExtra("play", false);
                sendBroadcast(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, mAdapter.getItem(i).getName());
        startActivity(intent);
    }

    private ArrayList<File> getDirs(File dir) {
        File listFile[] = dir.listFiles();
        ArrayList<File> dirList = new ArrayList<File>();
        for (File aListFile : listFile) {
            if (aListFile.isDirectory()) {
                dirList.add(aListFile);
            }
        }
        return dirList;
    }
}
