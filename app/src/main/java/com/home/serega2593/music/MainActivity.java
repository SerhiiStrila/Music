package com.home.serega2593.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, PlayService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startService(intent);

        Button mStart = (Button) findViewById(R.id.start_button);
        Button mStop = (Button) findViewById(R.id.stop_button);
        ListView mListView = (ListView) findViewById(R.id.folder_listView);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);

        FilesManager manager = new FilesManager();
        ArrayList<String> listDirec = manager.getDirs(manager.ROOT);
        ArrayList<Model> models = manager.getNumber(listDirec);
        mAdapter = new ListAdapter(this, models);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
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
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, PlayService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, DetialActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, mAdapter.getItem(i).getDir_name());
        startActivity(intent);
    }
}
