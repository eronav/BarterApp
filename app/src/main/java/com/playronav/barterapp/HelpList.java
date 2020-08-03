package com.playronav.barterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HelpList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);

        GameEnvironment.phoneDims = getWindowDims(GameEnvironment.c);

        fillHelpList(false);
    }
    private void fillHelpList(boolean local) {
        LinearLayout help_list = findViewById(R.id.help_lst);
        help_list.removeAllViews();

        if (local) {
            GameEnvironment.db.getAllData(this);
            return;
        }
        GameEnvironment.db.getAllDataFromRemote(this);
    }


    public void addEntry(String[] s) {
        LinearLayout help_list = findViewById(R.id.help_lst);

        LinearLayout ll = new LinearLayout(this);

        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llp0.gravity = Gravity.CENTER_HORIZONTAL;
        ll.setLayoutParams(llp0);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        help_list.addView(ll);

        addLayout(s, ll);
    }

    public void addLayout(String[] s, LinearLayout ll) {
        Drawable border = ContextCompat.getDrawable(this, R.drawable.custom_border);

        // initializing the parameters for the views
        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        llp1.weight = 1;
        llp1.setMargins(0, 0,0,0);
        LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        llp2.weight = 2;
        llp2.setMargins(0, 0,0,0);
        LinearLayout.LayoutParams llp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        llp3.weight = 2;
        llp3.setMargins(9, 0,0,0);
        LinearLayout.LayoutParams llp4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        llp4.weight = (float) 1.5;
        llp4.setMargins(6, 0,0,0);

        // creating the views and setting the text and border
        TextView v = new TextView(this);
        v.setText(s[0]);
        v.setTextSize(18);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(border);
        }
        v.setTextColor(Color.BLACK);
        v.setLayoutParams(llp1);
        v.setPadding(10,0,0,0);

/////////////////////////////////////////////////////////////////////

        TextView v2 = new TextView(this);
        v2.setText(s[1]);
        v2.setTextSize(18);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v2.setBackground(border);
        }
        v2.setTextColor(Color.BLACK);
        v2.setLayoutParams(llp2);
        v2.setPadding(10,0,0,0);

/////////////////////////////////////////////////////////////////////

        TextView v3 = new TextView(this);
        v3.setText(s[2]);
        v3.setTextSize(18);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v3.setBackground(border);
        }
        v3.setTextColor(Color.BLACK);
        v3.setLayoutParams(llp3);
        v3.setPadding(10,0,0,0);

/////////////////////////////////////////////////////////////////////

        TextView v4 = new TextView(this);
        v4.setText(s[3]);
        v4.setTextSize(18);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v4.setBackground(border);
        }
        v4.setTextColor(Color.BLACK);
        v4.setLayoutParams(llp4);
        v4.setPadding(10,0,0,0);

        //adding the view to the outer layout
        ll.addView(v);
        ll.addView(v2);
        // ll.addView(vvv);
        ll.addView(v4);
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
