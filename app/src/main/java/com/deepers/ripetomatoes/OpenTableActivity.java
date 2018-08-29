package com.deepers.ripetomatoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main activity when this app is launched.
 */
public class OpenTableActivity extends AppCompatActivity {
    Button mButtonLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_table);

        mButtonLaunch = findViewById(R.id.button_launch);
        mButtonLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpenTableActivity.this, ListActivity.class));
            }
        });
    }
}
