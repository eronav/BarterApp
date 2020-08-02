package com.playronav.barterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Thread.sleep;

public class PostNeed extends AppCompatActivity {


    EditText name;
    EditText obj;
    EditText street;
    EditText city;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_need);
        name = findViewById(R.id.editText5);
        obj = findViewById(R.id.editText6);
        street = findViewById(R.id.editText7);
        city = findViewById(R.id.editText9);
        next = findViewById(R.id.button2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().length() == 0 || obj.getText().toString().length() == 0 || street.getText().toString().length() == 0 || city.getText().toString().length() == 0) {
                    return;
                }
                GameEnvironment.db.insertDataRemote(name.getText().toString(), obj.getText().toString(), street.getText().toString(), city.getText().toString());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(GameEnvironment.c, HelpList.class);
                intent.putExtra("com.playronav.barterapp.afterpost", "");
                startActivity(intent);
            }
        });
    }
}
