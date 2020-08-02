//package com.playronav.barterapp;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//
//    MyDBManager myDB;
//    EditText a;
//    EditText b;
//    EditText c;
//    Button d;
//    Button e;
//    Button f;
//    EditText g;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        myDB = new MyDBManager(this);
//
//        a = findViewById(R.id.editText);
//        c = findViewById(R.id.editText3);
//        b = findViewById(R.id.editText2);
//        d = findViewById(R.id.button);
//        e = findViewById(R.id.vals);
//        f = findViewById(R.id.button3);
//        g = findViewById(R.id.editText4);
//
//        d.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addData();
//            }
//        });
//        e.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                revealData();
//            }
//        });
//        f.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteData();
//            }
//        });
//    }
//    public void deleteData() {
//        Integer deletedRows = myDB.deleteData(g.getText().toString());
//        if (deletedRows > 0) {
//            Toast.makeText(MainActivity.this, "DEL TRUE", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(MainActivity.this, "DEL FALSE", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void revealData() {
//        Cursor res = myDB.getAllData();
//        if (res.getCount() == 0) {
//            showMessage("ERROR", "Nothing found");
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while(res.moveToNext()) {
//            buffer.append("ID :" + res.getString(0) + "\n");
//            buffer.append("Name :" + res.getString(1) + "\n");
//            buffer.append("Surname :" + res.getString(2) + "\n");
//            buffer.append("Marks :" + res.getString(3) + "\n\n");
//        }
//        showMessage("DATA", buffer.toString());
//    }
//    public void showMessage(String title, String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }
//    public void addData() {
//        boolean isInserted = myDB.insertData(a.getText().toString(), b.getText().toString(), c.getText().toString());
//        if (isInserted) {
//            Toast.makeText(MainActivity.this, "TRUE", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(MainActivity.this, "FALSE", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
