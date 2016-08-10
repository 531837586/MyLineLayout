package com.yitu8.mylinelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.yitu8.mylinelayout.layout.AutoLinearLayout;

public class MainActivity extends AppCompatActivity {

    private AutoLinearLayout strom_list;

    private String[] strings = { "中", "中国", "中国人",  "welcome","android","TextView",
            "apple","jamy","kobe bryant",
            "jordan","layout","viewgroup",
            "margin","padding","text",
            "name","type","search","logcat" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strom_list = (AutoLinearLayout) findViewById(R.id.strom_list);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 10;
        lp.bottomMargin = 10;
        strom_list.removeAllViews();
        for( int i = 0; i < strings.length; i++ ){
            TextView tv = new TextView(this);
            tv.setText(strings[i]);
            tv.setBackgroundResource(R.drawable.frame_text_bg);
            strom_list.addView(tv, lp);
        }
    }
}
