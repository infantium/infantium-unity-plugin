package com.infantium.unityplugin;

import com.infantium.android.sdk.InfantiumSDK;
import com.infantium.android.sdk.InfantiumResponse;
import com.infantium.unityplugin.InfantiumCallbackHandler;
import android.app.Activity;

import android.content.Context;
import android.util.Log;

import com.infantium.unityplugin.InfantiumUnity;
import com.unity3d.player.UnityPlayer;


public class InfantiumPlugin {
    InfantiumSDK infantium;
	
    public InfantiumPlugin(){
        
        final Activity a = UnityPlayer.currentActivity;
        final Context appContext = a.getApplicationContext();
        a.runOnUiThread(new Runnable() {public void run() {
        	infantium = InfantiumSDK.getInfantiumSDK(appContext);
    		Log.i(InfantiumUnity.TAG, "Starting infantium sdk singleton");
        }});
    }
    
    public String initInfantium(String api_user, String api_key, int device_width, int device_height){
		Log.i(InfantiumUnity.TAG, "Call to InitInfantium");
    	try{
    		infantium.setDeveloperCredentials(api_user, api_key);
			infantium.setDeviceInfo(device_width, device_height);
			infantium.setDeveloperHandler(InfantiumCallbackHandler.handler);
			return InfantiumResponse.Valid.toString();
    	
    	} catch (Exception e){
			Log.e(InfantiumUnity.TAG, "Initial setup failed. Exception " + e.getClass().getName() + ": " + e.getMessage());
    	}
    	return null;
    }
}
