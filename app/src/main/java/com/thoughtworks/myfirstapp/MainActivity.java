package com.thoughtworks.myfirstapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.thoughtworks.myfirstapp.MESSAGE";

    @InjectView(R.id.send_button )
    Button send_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        send_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        View button = findViewById(R.id.send_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }

//    @OnClick(R.id.button)
//    public void show(){
//
//    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.input_message);
        String message = editText.getText().toString();
        if (TextUtils.isEmpty(message)) {
            message = "asaa";
        }
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}
