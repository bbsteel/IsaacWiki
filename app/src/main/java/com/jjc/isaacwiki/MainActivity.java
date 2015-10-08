package com.jjc.isaacwiki;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    GridLayout gridlayout;

    Intent itemIntent;

    ImageButton itemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("Test", "1");
        gridlayout = (GridLayout) findViewById(R.id.gridlayout);
        Log.d("Test", "2");
        ViewGroup.LayoutParams params =  gridlayout.getLayoutParams();
        Log.d("Test", "3");
        Log.d("Columns", ""+gridlayout.getChildCount());
        for (int i=0; i<gridlayout.getChildCount()-1; i++){
            Log.d("Test", "4");
            ImageButton v = (ImageButton)gridlayout.getChildAt(i);
            v.setMaxWidth(gridlayout.getWidth()/gridlayout.getColumnCount() - v.getPaddingLeft() - v.getPaddingRight());
            Log.d("Test", "5");
            v.setMinimumWidth(gridlayout.getWidth() / gridlayout.getColumnCount() - v.getPaddingLeft() - v.getPaddingRight());
            Log.d("Test", "6");
        }


        //Voor joris om de singelItem Activity te maken
        itemIntent = new Intent(this, singleItemActivity.class);
        itemButton = (ImageButton) findViewById(R.id.switchIntent);
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(itemIntent);
            }
        });
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
}
