package cn.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class SocketIOService extends Service {
    private MyBinder binder;

    private Socket msocket;
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
        try {
            msocket = IO.socket("http://42.96.149.197:3001");
            msocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.e("socketio","connected");
                }
            });

            msocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.e("socketio","disconnected");
                }
            });

            msocket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //the useful data is args[0]
                    msocket.emit("push","hello");
                }
            });

            msocket.connect();// connect the server
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public void socketioPost() {
        //this function is used to realise the sockeio communication with the server.




        
    }
}
