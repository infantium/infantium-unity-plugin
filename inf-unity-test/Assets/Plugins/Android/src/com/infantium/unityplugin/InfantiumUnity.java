package com.infantium.unityplugin;
 
import com.unity3d.player.UnityPlayerActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;


public class InfantiumUnity extends UnityPlayerActivity
{
    public static final String TAG = "Inf-Unity-Plugin";
    
    @Override
    protected void onCreate(Bundle myBundle) {
        Log.d(TAG, "onCreate");
        super.onCreate(myBundle);
    }
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }
    
    public void dummyTest(String logg){
		Log.i(TAG, "DummyLog");
		Log.i(TAG, logg);
    }
    
}