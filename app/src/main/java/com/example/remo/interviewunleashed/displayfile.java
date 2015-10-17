package com.example.remo.interviewunleashed;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class displayfile extends ActionBarActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayfile);
        String title, desc;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                title = null;
                desc = null;
            } else {
                title = extras.getString("Title");
                desc = extras.getString("description");
            }
        } else {
            title = (String) savedInstanceState.getSerializable("Title");
            desc = (String) savedInstanceState.getSerializable("description");
        }
      //  Toast.makeText(displayfile.this, "" + desc + title, Toast.LENGTH_LONG).show();
        tv=(TextView)findViewById(R.id.readfile);
        String data=null;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Title",title);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for(int i=0;i<scoreList.size();i++)
                    tv.setText(Html.fromHtml(scoreList.get(i).get("textinfile").toString()) + " ");
                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_displayfile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
