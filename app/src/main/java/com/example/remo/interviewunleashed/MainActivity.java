package com.example.remo.interviewunleashed;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends ActionBarActivity {
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "vm2vDibsue87M6FEtVDFDfoJKQ3LMhVh7KfoP7lP", "t46NdfdI9OOdV1eXPeMgIaQh16JQkAJIfLMLctFQ");
        }catch (Exception e){}
            setContentView(R.layout.activity_main);
        fm=getFragmentManager();
      FragmentTransaction  ft=fm.beginTransaction();
        Login login=new Login();
        ft.add(R.id.frag, login);
        ft.commit();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent=new Intent(MainActivity.this,DashBoard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    public void createaccount(View view)
    {
        FragmentTransaction ftt=getFragmentManager().beginTransaction();
        Account ac=new Account();
        ftt.replace(R.id.frag,ac);
        ftt.commit();
    }
    EditText logname;
    public void login(View view)
    {
         logname=(EditText)findViewById(R.id.useedit);
        EditText logpass=(EditText)findViewById(R.id.passedit);
        ParseUser.logInInBackground(logname.getText().toString(), logpass.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(MainActivity.this,"Welcome "+logname.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,DashBoard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                   Toast.makeText(MainActivity.this,"Sign In Unsuccessfull",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void signup(View view)
    {
        EditText et=(EditText)findViewById(R.id.passreg);
        EditText et2=(EditText)findViewById(R.id.conpassreg);
        String pass=et.getText().toString();
        String cpass=et2.getText().toString();
        if(!pass.equals(cpass))
        {
            Toast.makeText(this,"Passwords Do not Match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            EditText uname=(EditText)findViewById(R.id.uname);
            EditText mailid=(EditText)findViewById(R.id.emailid);
            ParseUser user = new ParseUser();
            user.setUsername(uname.getText().toString());
            user.setPassword(cpass);
            user.setEmail(mailid.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this,"Sign Up Successfull",Toast.LENGTH_LONG).show();
                        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frag,new Login());
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(MainActivity.this,"Sign Up Unsuccessfull",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}
