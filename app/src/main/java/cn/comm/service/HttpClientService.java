package cn.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * this service uses HttpClient to communicaite with server.
 */
public class HttpClientService extends Service {
    private MyBinder binder;
    public HttpClientService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return binder;
    }

    public class MyBinder extends Binder
    {
        public HttpClientService getService()
        {
            return HttpClientService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();//initial the binder
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
