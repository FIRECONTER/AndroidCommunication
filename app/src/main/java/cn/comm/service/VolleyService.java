package cn.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyService extends Service {
    private MyBinder binder;
    private RequestQueue qu;
    public VolleyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("volleyservice","binder");
        return binder;
    }

    public class MyBinder extends Binder
    {
        public VolleyService getService()
        {
            return VolleyService.this;//
        }
    }

    public void display()
    {
        Log.e("volleyservice","display");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("VolleyService", "runcommand");
        if(intent==null)
        {
            Log.e("VolleyService", "intentis null");
        }
        else
        {
            Log.e("VolleyService", "intent is ok");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopSelf();
        return Service.START_STICKY;//normal
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("VolleyService", "create");
        qu = Volley.newRequestQueue(this);// initialize the requestqueue
        binder = new MyBinder();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("VolleyService", "destory");
    }


    public void volleyPost()
    {
        String url = "http://42.96.149.197:3000";

        StringRequest req = new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                // string s is a jsonstring.

                // json parse in android
                Log.e("Back",s);
                try {
                    JSONObject ob = new JSONObject(s);
                    String name = ob.getString("name");
                    int age = ob.getInt("age");// It is ok

                } catch (JSONException e) {
                    Log.e("Back",e.toString());
                }


            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Back",error.toString());
            }

        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();// put the post data
                map.put("name", "alex");
                map.put("age", "18");
                return map;
            }
        };

        qu.add(req);// add the request
    }

    public void dis()
    {
        Log.e("Back","hello");
    }

}
