package cn.comm.activity;

import android.app.Activity;
import android.content.ComponentName;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cn.comm.R;
import cn.comm.service.VolleyService;

//test the lifetime of Service
public class TestServiceActivity extends Activity implements View.OnClickListener {
    private Button btnStart;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnbind;
    private Button btnJlink;
    private VolleyService myservice;
    private ServiceConnection mc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("Activity","serviceconnected");
           myservice =  ((VolleyService.MyBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("Activity","servicedisconnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        btnStart = (Button)findViewById(R.id.bu1);
        btnStop = (Button)findViewById(R.id.bu2);
        btnBind =(Button)findViewById(R.id.bu3);
        btnUnbind = (Button)findViewById(R.id.bu4);
        btnJlink = (Button)findViewById(R.id.bu5);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnJlink.setOnClickListener(this);

        Log.e("Activity","create");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent in = new Intent(this, VolleyService.class);

        if(id==R.id.bu1)
        {
            Log.e("Activity", "startService");
            startService(in);
            Log.e("Activity", "startServiceafterstart");

        }
        if(id == R.id.bu2)
        {
            Log.e("Activity", "stopService");
            stopService(in);
            Log.e("Activity", "startServiceafterstop");
        }
        if(id == R.id.bu3)
        {
            Log.e("Activity", "binder button");
            bindService(in,mc, Context.BIND_AUTO_CREATE);
        }
        if(id == R.id.bu4)
        {
            Log.e("Activity", "unbinder button");
            unbindService(mc);
        }
        if(id == R.id.bu5)
        {
            Log.e("Activity","Run Server request");
            myservice.volleyPost();// use the postmethod to send data to the server
        }

    }
}
