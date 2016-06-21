package cn.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;



public class SocketIOService extends Service {
    private MyBinder binder;

    public SocketIOService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public class MyBinder extends Binder {
        public SocketIOService getService()
        {
            return SocketIOService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public void socketioPost() {
        //this function is used to realise the sockeio communication with the server.




        
    }
}
