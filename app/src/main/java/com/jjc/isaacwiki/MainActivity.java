package com.jjc.isaacwiki;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ViewGroup.LayoutParams params;
    GridLayout gridlayout;

    Intent itemIntent;

    ImageButton itemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        code chiem om imageButtons erin te zetten
         */

        gridlayout = (GridLayout) findViewById(R.id.gridlayout);
        params =  gridlayout.getLayoutParams();
        for (int i=0; i<gridlayout.getChildCount(); i++) {
            View v = gridlayout.getChildAt(i);
            ImageButton imgBut = (ImageButton) findViewById(v.getId());
            imgBut.setOnClickListener(onImageButtonClick);
        }

        gridlayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int paddingTop = (int)(20 * getResources().getDisplayMetrics().density);
                int paddingBottom = (int)(20 * getResources().getDisplayMetrics().density);
                int width = gridlayout.getWidth();
                for (int i=0; i<gridlayout.getChildCount(); i++) {
                    View v = gridlayout.getChildAt(i);
                    ImageButton imgBut = (ImageButton) findViewById(v.getId());
                    Log.d("Test", "Width " + gridlayout.getWidth());
                    imgBut.setMaxWidth(gridlayout.getWidth() / gridlayout.getColumnCount() - imgBut.getPaddingLeft() - imgBut.getPaddingRight());
                    imgBut.setMinimumWidth(gridlayout.getWidth() / gridlayout.getColumnCount() - v.getPaddingLeft() - v.getPaddingRight());
                    if(i> gridlayout.getChildCount()){
                        imgBut.setPadding(0, paddingTop , 0 , paddingBottom);
                    }
                }
                if (Build.VERSION.SDK_INT < 16) {
                    gridlayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    gridlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        //Voor joris om de singelItem Activity te maken
        itemIntent = new Intent(this, singleItemActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener onImageButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(itemIntent);
        }
    };
}
