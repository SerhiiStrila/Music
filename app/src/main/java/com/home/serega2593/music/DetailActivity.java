package com.home.serega2593.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        Intent intent = getIntent();
        TextView mTextView = (TextView) findViewById(R.id.detail_textView);
        Button mBack = (Button) findViewById(R.id.back_button);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTextView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    }
}
