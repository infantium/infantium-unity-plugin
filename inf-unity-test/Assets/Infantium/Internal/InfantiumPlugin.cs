/* 
 * INFANTIUM UNITY PLUGIN
 * Author:  Marc Pomar (marc@infanitum.com)
 *
 */

using UnityEngine;
using System;
using System.Collections;
using System.Collections.Generic;

static class Constants{
	public const string INF_JAVA_CLASS = "com.infantium.unityplugin.InfantiumPlugin";
}

public class InfantiumPlugin{
	
	private static InfantiumPlugin instance;
	
	AndroidJavaObject infPlugin;
	AndroidJavaObject infantiumSDK;
	public string api_user;
	public string api_key;
	public int device_width;
	public int device_height;
	public string server_mode;
	public Boolean debug;
	
	public InfantiumPlugin(string api_user, string api_key, int device_width = -1, int device_height = -1, string server="beta", Boolean debug=true){
		AndroidJNI.AttachCurrentThread();
		infPlugin = new AndroidJavaObject(Constants.INF_JAVA_CLASS);
		
		this.api_user = api_user;
		this.api_key = api_key;
		this.device_width = device_width;
		this.device_height = device_height;
		this.server_mode = server;
		this.debug = debug;
		this.initInfantium();
	}
	
	public void initInfantium(){
		Debug.Log(string.Format("Init Infantium with server {0}",this.server_mode));
		infantiumSDK = infPlugin.Call<AndroidJavaObject>("initInfantium", this.api_user, this.api_key, this.device_width, this.device_height, this.server_mode, this.debug);
	}
	
	public InfantiumResponse callInfantium(string methodName, params object[] args){
		try{
			Debug.Log(string.Format("Calling Infantium SDK Method {0} with parameters {1}", methodName, args));
			AndroidJavaObject response;
			if (args.Length != 0){ 
				Debug.Log(string.Format("Adding arguments, number of args={0}",args.Length));
				response = infantiumSDK.Call<AndroidJavaObject>(methodName, args);
			}else{
				response = infantiumSDK.Call<AndroidJavaObject>(methodName);
			}
			return (InfantiumResponse)response.Get<int>("ordinal");
		}catch (Exception ex){
			Debug.LogError(string.Format("Infantium Unity SDK exception={0} method={1}", ex, methodName));
		}
		return InfantiumResponse.NullObject;
	}
	
	
	public InfantiumResponse callPlugin(string methodName, params object[] args){
		try{
			Debug.Log(string.Format("Calling Infantium Plugin Method {0} with parameters {1}", methodName, args));
			AndroidJavaObject response;
			if (args.Length != 0){
				Debug.Log(string.Format("Adding arguments, number of args={0}",args.Length));
				response = infPlugin.Call<AndroidJavaObject>(methodName, args);
			}else{
				response = infPlugin.Call<AndroidJavaObject>(methodName);
			}
			return (InfantiumResponse)response.Get<int>("ordinal");
		}catch (Exception ex){
			Debug.LogError(string.Format("Infantium Unity Plugin exception={0} method={1}", ex, methodName));
		}
		return InfantiumResponse.NullObject;
	}
	
	
	/********************
	   INFANTIUM CALLS
	 ********************/
	
	public InfantiumResponse setContentAppUUID(string contentappUUID){ 
		return this.callInfantium("setContentAppUUID", contentappUUID); 
		//infantiumSDK.Call<AndroidJavaObject>("setContentAppUUID", contentappUUID);
		//Debug.Log("CALLED");
		//return InfantiumResponse.NullObject;
	}
	
	public InfantiumResponse setSubContentUUID(string subcontentUUID){ 
		return this.callInfantium("setSubContentUUID", subcontentUUID); 
	}
	
	
	/***********************
	   INFANTIUM ELEMENTS
	 ***********************/
	
	public InfantiumResponse addElement(string element_id, int width, int height, string movement){
		return this.callPlugin("addElement", element_id, width, height, movement); 
	}
	
	public InfantiumResponse addTextElement(string element_id, int width, int height, string movement, string text, string lang){ 
		return this.callPlugin("addTextElement", element_id, width, height, movement, text, lang); 
	}
	
	public InfantiumResponse addShapeElement(string element_id, int width, int height, string movement, int n_sides){ 
		return this.callPlugin("addShapeElement", element_id, width, height, movement, n_sides); 
	}
	
	public InfantiumResponse addPaintedElement(string element_id, int width, int height){ 
		return this.callPlugin("addPaintedElement", element_id, width, height); 
	}
	
	public InfantiumResponse addPictureElement(string element_id, int width, int height){ 
		return this.callPlugin("addPictureElement", element_id, width, height); 
	}
	
	/***********************
	   INFANTIUM GOALS
	 ***********************/
	
	public InfantiumResponse addGoal(string goal_id, long time_limit, Boolean auto_eval, string instructions){
		return this.callPlugin("addGoal", goal_id, time_limit, auto_eval, instructions);
	}
	
	public InfantiumResponse addSelectionGoal(string goal_id, long time_limit, Boolean auto_eval, string instructions, int n_correct_choices, int n_incorrect_choices, Boolean unique_solutions, string needed_action){ 
		return this.callPlugin("addSelectionGoal", goal_id, time_limit, auto_eval, instructions, n_correct_choices, n_incorrect_choices, unique_solutions,needed_action); 
	}
	
	public InfantiumResponse addMatchingGoal(string goal_id, long time_limit, Boolean auto_eval, string instructions, string matching_element, string correspondence_type){ 
		return this.callPlugin("addMatchingGoal", goal_id, time_limit, auto_eval, instructions, matching_element, correspondence_type); 
	}
	
	public InfantiumResponse addTappingGoal(string goal_id, long time_limit, Boolean auto_eval, string instructions, string element_to_tap){ 
		return this.callPlugin("addTappingGoal", goal_id, time_limit, auto_eval, instructions, element_to_tap); 
	}

	public InfantiumResponse addNBackGoal(string goal_id, int nback, string[] elements, Boolean dual, Boolean requires_match, string[] properties){
		return this.callPlugin ("addNBackGoal", goal_id, elements, nback, dual, requires_match, properties); //, elem); //, dual, requires_match, prop);
	}
	
	public InfantiumResponse newBasicInteraction(string interaction_t, string object_type, string goal_type, int lifetime, int n_concurrent_oks, int n_concurrent_kos){ 
		return this.callPlugin("newBasicInteraction", interaction_t, object_type, goal_type, lifetime, n_concurrent_oks, n_concurrent_kos); 
	}

	/***********************
	   INFANTIUM GAMEPLAY
	 ***********************/
	
	public InfantiumResponse createGameplay(string subContentUUID){ 
		return this.callInfantium("createGameplay", subContentUUID); 
	}
	
	public InfantiumResponse startPlaying(){ 
		return this.callInfantium("startPlaying"); 
	}
	
	public InfantiumResponse closeGameplay(){ 
		return this.callInfantium("closeGameplay"); 
	}
	
	/***********************
	   INFANTIUM RAWDATA
	 ***********************/
	
	public InfantiumResponse sendGameRawdata(){
		infantiumSDK.Call("sendGameRawData");
		return InfantiumResponse.Valid;
	}
	public InfantiumResponse sendEbookRawdata(){ 
		infantiumSDK.Call("sendEbookRawData");
		return InfantiumResponse.Valid;
	}
	
	/***************************
	   INFANTIUM APP BEHAVIOURS
	 ***************************/
	
	public Boolean returnToInfantiumApp(){
		Debug.Log(string.Format("Returning to infantium app"));
		AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
		AndroidJavaObject context = jc.GetStatic<AndroidJavaObject>("currentActivity");
		AndroidJavaObject resp = infantiumSDK.Call<AndroidJavaObject>("returnToInfantiumApp", context);
		return true;
	}
	
	public InfantiumResponse onPauseInfantium(){
		infantiumSDK.Call("onPauseInfantium");
		return InfantiumResponse.Valid;
	}
	
	public InfantiumResponse onResumeInfantium(){ 
		infantiumSDK.Call("onResumeInfantium");
		return InfantiumResponse.Valid; 
	}
	
}

