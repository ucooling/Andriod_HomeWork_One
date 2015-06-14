package com.thoughtworks.myfirstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SecondActivity extends AppCompatActivity {

    @InjectView(R.id.list)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        listView.setAdapter(new MyAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(SecondActivity.this)
                        .setTitle("Hello App")
                        .setMessage("" + parent.getItemIdAtPosition(position))
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(R.mipmap.ic_launcher)
                        .show();
//                Dialog dialog = new Dialog(SecondActivity.this);
//                dialog.setTitle("Hello App");
//                dialog.setCancelable(true);
//                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//                Toast.makeText(SecondActivity.this, "" + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//        setContentView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
//            TextView text = (TextView) convertView.findViewById(R.id.item_text);
            ((ViewHolder) convertView.getTag()).populate(getItem(position));
            return convertView;


//            TextView textView = new TextView(parent.getContext());
//            textView.setText(""+position);
//            return textView;
        }

        class ViewHolder {
            @InjectView(R.id.item_text)
            TextView textView;

            public ViewHolder(View view){
                ButterKnife.inject(this, view);
            }

            public void populate(Object item){
                textView.setText(item.toString());
            }
        }
    }
}
