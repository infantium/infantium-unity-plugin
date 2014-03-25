package com.infantium.unityplugin;
 
import com.unity3d.player.UnityPlayerActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;

public class InfantiumUnity extends UnityPlayerActivity
{
    private static final String TAG = "Inf-Unity-Plugin";
    private static int number = 0;
 
    @Override
    protected void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
 
    }
    @Override
    protected void onResume() {
        if (Config.DEBUG)
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
        if (Config.DEBUG)
            Log.d(TAG, "onStop");
        super.onStop();
    }
    public static int getNumber()
    {
        number++;
        return number;
    }
}