package com.playronav.barterapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);

        GameEnvironment.phoneDims = getWindowDims(GameEnvironment.c);

//        if (getIntent().hasExtra("com.playronav.barterapp.afterpost")) {
////
////        }

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
        LinearLayout ll = addLayout(s);
        help_list.addView(ll);
    }

    public LinearLayout addLayout(String[] s) {

        LinearLayout ll = new LinearLayout(this);
        TextView v = new TextView(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        v.setTextColor(Color.BLACK);
        TextView vv = new TextView(this);
        vv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vv.setTextColor(Color.BLACK);
        TextView vvv = new TextView(this);
        vvv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vvv.setTextColor(Color.BLACK);
        TextView vvvv = new TextView(this);
        vvvv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vvvv.setTextColor(Color.BLACK);

        ImageView i = new ImageView(GameEnvironment.c);
        i.setLayoutParams(get_layout_params());
        ImageView ii = new ImageView(GameEnvironment.c);
        ii.setLayoutParams(get_layout_params());
        ImageView iii = new ImageView(GameEnvironment.c);
        iii.setLayoutParams(get_layout_params());
        ImageView iv = new ImageView(GameEnvironment.c);
        iv.setLayoutParams(get_layout_params());

        v.setText(s[0]);
        vv.setText(s[1]);
        vvv.setText(s[2]);
        vvvv.setText(s[3]);

        v.setTextSize(18);
        vv.setTextSize(18);
        vvv.setTextSize(18);
        vvvv.setTextSize(18);

        ll.addView(i);
        ll.addView(v);
        ll.addView(ii);
        ll.addView(vv);
        ll.addView(iii);
        ll.addView(vvv);
        ll.addView(iv);
        ll.addView(vvvv);

        return ll;
    }
//    public void printData() {
//        Cursor res = GameEnvironment.db.getAllData();
//        if (res.getCount() == 0) {
//            showMessage("ERROR", "Nothing found");
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while(res.moveToNext()) {
//            buffer.append(MyDBManager.COL_1 + " :" + res.getString(0) + "\n");
//            buffer.append(MyDBManager.COL_2 + " :" + res.getString(1) + "\n");
//            buffer.append(MyDBManager.COL_3 + " :" + res.getString(2) + "\n");
//            buffer.append(MyDBManager.COL_4 + " :" + res.getString(3) + "\n");
//            buffer.append(MyDBManager.COL_5 + " :" + res.getString(4) + "\n\n");
//        }
//        showMessage("DATA", buffer.toString());
//    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public LinearLayout.LayoutParams get_layout_params () {
        LinearLayout.LayoutParams img_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        img_layout.width = GameEnvironment.phoneDims[0] / 15;
        img_layout.height = img_layout.width;
        img_layout.setMargins(0,0, 5, 10);
        return img_layout;
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
