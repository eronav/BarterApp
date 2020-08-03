package com.playronav.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

public class MainMenu extends AppCompatActivity {

    TextView needHelp;
    TextView canHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        needHelp = findViewById(R.id.textView);
        canHelp = findViewById(R.id.textView2);
        needHelp.setClickable(true);
        canHelp.setClickable(true);

        GameEnvironment.c =  getApplicationContext();
        GameEnvironment.db = new MyDBManager(GameEnvironment.c);

        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameEnvironment.c, PostNeed.class);
                intent.putExtra("com.playronav.barterapp.topost", "");
                startActivity(intent);
            }
        });

        canHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameEnvironment.c, HelpList.class);
                intent.putExtra("com.playronav.barterapp.tohelp", "");
                startActivity(intent);
            }
        });
    }

}
