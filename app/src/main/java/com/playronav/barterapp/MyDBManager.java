package com.playronav.barterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class MyDBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BarterBuddies_2.db";

    public static final String TABLE_NAME = "Barter_table_2";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ITEM";
    public static final String COL_4 = "STREET";
    public static final String COL_5 = "CITY";

    public MyDBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String object, String street, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, object);
        contentValues.put(COL_4, street);
        contentValues.put(COL_5, city);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result == -1 ? false : true;
    }

    public void insertDataRemote(String name, String item, String street, String city) {
        RequestQueue rq = Volley.newRequestQueue(GameEnvironment.c);
        Date currDate = Calendar.getInstance().getTime();
        String url = "http://192.168.1.94/addEntry.php?";

        url += "name="+name;
        url += "&item="+item;
        url += "&street="+street;
        url += "&city="+city;
        url += "&date="+currDate.toString();

        StringRequest srq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(GameEnvironment.c, "YAY", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError r) {
                Log.e("error", r.toString());
            }
        });
        rq.add(srq);
    }



    public void getAllDataFromRemote(final HelpList hl) {
        String url = "http://192.168.1.94/getData.php";
        RequestQueue rq = Volley.newRequestQueue(hl);
        StringRequest srq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document doc = Jsoup.parseBodyFragment(response);
                Element text = doc.body();
                String bodyStr = text.text();
                if (bodyStr.length() > 0) {
                    try {
                        JSONObject reader = new JSONObject(bodyStr);
                        // Getting JSON Array node
                        JSONArray entries = reader.getJSONArray("entries");

                        // looping through All Contacts
                        for (int i = 0; i < entries.length(); i++) {
                            JSONObject c = entries.getJSONObject(i);
                            hl.addEntry(new String[]{
                                c.getString("NAME"),
                                c.getString("ITEM"),
                                c.getString("STREET"),
                                c.getString("CITY")
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError r) {
                Log.e("error", r.toString());
            }
        });
        rq.add(srq);
    }

    public Cursor getAllData(HelpList hl) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        while(res.moveToNext()) {
            hl.addEntry(new String[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
        }
        return res;
    }

//    public Integer deleteData(String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, COL_1 + " = ?", new String[] {id});
//    }
//    public void deleteAllData() {
//        int length = GameEnvironment.db.getAllData().getCount();
//        for (int i = 0; i < length; i++) {
//            deleteData(String.valueOf(i));
//        }
//    }
}