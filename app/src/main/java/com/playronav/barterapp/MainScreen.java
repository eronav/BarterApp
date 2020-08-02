package com.playronav.barterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton add;    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        toolbar = findViewById(R.id.toolbar);
        add = toolbar.findViewById(R.id.add);
        add.setPadding(getWindowDims(this)[0]-getWindowDims(this)[0]/4, 0, 0, 0);
        setSupportActionBar(toolbar);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainScreen.this, "TRUE", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int[] getWindowDims (Context myappctxt) {
        int[] phoneDims = new int[2];

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) myappctxt.getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        phoneDims[0] = displayMetrics.widthPixels;
        phoneDims[1] = displayMetrics.heightPixels;
        return phoneDims;
    }
}
