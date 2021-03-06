package com.example.remo.interviewunleashed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class DashBoard extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toast.makeText(DashBoard.this,"Welcome "+ ParseUser.getCurrentUser().getUsername(),Toast.LENGTH_LONG).show();
        DrawerLayout dl=(DrawerLayout)findViewById(R.id.drawer_layout);
        dl.openDrawer(GravityCompat.START);

        float[] values = new float[] { 2.0f,1.5f, 2.5f, 1.0f , 3.0f };
        String[] verlabels = new String[] { "great", "ok", "bad" };
        String[] horlabels = new String[] { "today", "tomorrow", "next week", "next month" };
        GraphView graphView = new GraphView(this, values, "GraphViewDemo",horlabels, verlabels, GraphView.BAR);

    }
    @Override
    public boolean onKeyDown(int keycode,KeyEvent ke)
    {
        if(keycode==KeyEvent.KEYCODE_BACK)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage("Are you sure you wanna Exit ?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            AlertDialog ad=alert.create();
            ad.show();
        }
        return super.onKeyDown(keycode, ke);
    }
int userprof;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       userprof= menu.add("Remo").getItemId();
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logoutt) {
            ParseUser.logOut();
            Toast.makeText(DashBoard.this,"Logged Out",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(DashBoard.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        if(id==userprof)
        {
            Toast.makeText(DashBoard.this,"Hello",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void adgraphln(View view)
    {
        //   view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "graph");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title") + "");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description") + " ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });

    }

    public void adtreeln(View view)
    {
        //   view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "tree");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title") + "");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description") + " ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });

    }

    public void videoln(View view)
    {
        //   view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "tree");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title") + "");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description") + " ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });

    }




    public void dsln(View view)
    {
     //   view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "ds");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                   // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title") + "");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description") + " ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });

    }
    public void cln(View view)
    {
       // view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "c");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title")+"");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description")+" ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });

    }
    public void cppln(View view)
    {
        //view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "c++");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title")+"");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description")+" ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });


    }
    public void algoln(View view)
    {
  //      view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "algo");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    // TextView tv=(TextView)findViewById(R.id.content_frame);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title")+"");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description")+" ");
                        LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv = (TextView) v2.findViewById(R.id.titlelist);
                                TextView tv2 = (TextView) v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DashBoard.this, displayfile.class);
                                intent.putExtra("Title", tv.getText().toString());
                                intent.putExtra("description", tv2.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });


    }
    public void javaln(View view)
    {
    //    view.setBackgroundColor(13369344);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Home");
        query.whereEqualTo("Filecategory", "java");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("MEWTWO", "Retrieved " + scoreList.size() + " scores");
                    LayoutInflater lin=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    LinearLayout sv2=(LinearLayout)findViewById(R.id.content_frame);
                    sv2.removeAllViews();
                    for(int i=0;i<scoreList.size();i++)
                    {
                        View v=lin.inflate(R.layout.homelist,null);
                        TextView tv=(TextView)v.findViewById(R.id.titlelist);
                        tv.setText(scoreList.get(i).get("Title")+"");
                        TextView tv2=(TextView)v.findViewById(R.id.desclist);
                        tv2.setText(scoreList.get(i).get("description")+" ");
                       LinearLayout sv=(LinearLayout)findViewById(R.id.content_frame);
                        sv.addView(v);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v2) {
                                TextView tv=(TextView)v2.findViewById(R.id.titlelist);
                                TextView tv2=(TextView)v2.findViewById(R.id.desclist);
                                //Toast.makeText(DashBoard.this,""+tv.getText()+tv2.getText(),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(DashBoard.this,displayfile.class);
                                intent.putExtra("Title",tv.getText().toString());
                                intent.putExtra("description",tv2.getText().toString());
                                startActivity(intent);
                            }
                        });
                    }

                } else {
                    Log.d("PIKACHU", "Error: " + e.getMessage());
                    //TextView tv=(TextView)findViewById(R.id.content_frame);
                    //tv.setText("Pikachu");

                }
            }
        });


    }
}
