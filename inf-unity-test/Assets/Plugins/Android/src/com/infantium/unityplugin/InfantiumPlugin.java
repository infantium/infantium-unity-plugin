package com.infantium.unityplugin;

import com.infantium.android.sdk.InfantiumSDK;
import com.infantium.android.sdk.InfantiumResponse;
import com.infantium.android.sdk.constants.Conf;
import com.infantium.android.sdk.elements.Element;
import com.infantium.android.sdk.elements.ShapeElement;
import com.infantium.android.sdk.elements.PaintedElement;
import com.infantium.android.sdk.elements.PictureElement;

import com.infantium.android.sdk.elements.TextElement;
import com.infantium.android.sdk.goals.Goal;
import com.infantium.android.sdk.goals.MatchingGoal;
import com.infantium.android.sdk.goals.SelectionGoal;
import com.infantium.android.sdk.goals.TappingGoal;
import com.infantium.android.sdk.goals.NBackGoal;
import com.infantium.unityplugin.InfantiumCallbackHandler;
import android.app.Activity;

import android.content.Context;
import android.util.Log;
import java.util.List;
import java.util.Arrays;

import com.infantium.unityplugin.InfantiumUnity;
import com.unity3d.player.UnityPlayer;


public class InfantiumPlugin {
	
    public final static boolean INFANTIUM_SDK_DEBUG = true;
    
    /* Change for PRODUCTION or BETA: "api" means production, "beta" means BETA ;-) */
    public final static String INFANTIUM_SDK_SERVER = "beta";
	
    InfantiumSDK infantium;
	
    public InfantiumPlugin(){
        
        final Activity a = UnityPlayer.currentActivity;
        final Context appContext = a.getApplicationContext();
      
        a.runOnUiThread(new Runnable() {public void run() {
        	infantium = InfantiumSDK.getInfantiumSDK(appContext, INFANTIUM_SDK_SERVER, INFANTIUM_SDK_DEBUG);     	
    		Log.i(InfantiumUnity.TAG, "Starting infantium sdk singleton");
        }});
    }
    
    public InfantiumSDK initInfantium(String api_user, String api_key, int device_width, int device_height, String server, boolean debug){
		Log.i(InfantiumUnity.TAG, "Call to InitInfantium");
    	try{
            final Activity a = UnityPlayer.currentActivity;
            final Context appContext = a.getApplicationContext();
        	infantium = InfantiumSDK.getInfantiumSDK(appContext, server, debug);     	
    		infantium.setDeveloperCredentials(api_user, api_key);
			infantium.setDeviceInfo(device_width, device_height);
			infantium.setDeveloperHandler(InfantiumCallbackHandler.handler);
			
    		Log.i(InfantiumUnity.TAG, "Starting unity plugin with server: " + server);

			return infantium;
    	
    	} catch (Exception e){
			Log.e(InfantiumUnity.TAG, "Initial setup failed. Exception " + e.getClass().getName() + ": " + e.getMessage());
    	}
    	
    	return null;
    }

   

    public InfantiumResponse addElement(String element_id, int width, int height, String movement){
    	Element ele = new Element(element_id);
		if(width != -1 && height != -1) {
			ele.set_size(width, height);
		}
		if(!movement.equals("")) {
			ele.set_movement(movement);
		}
		
		InfantiumResponse response = infantium.addElement(ele);
		
		if(response.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddElement successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddElement failed: " + response.toString() + " -");
		
		return response;
    }
    
    public InfantiumResponse addTextElement(String element_id, int width, int height, String movement, String text, String lang){
		TextElement ele = new TextElement(element_id);
    	if(width != -1 && height != -1) {
			ele.set_size(width, height);
		}
		if(!movement.equals("")) {
			ele.set_movement(movement);
		}
		
		if(!lang.equals("")) {
			ele.set_language_text(lang, text);
		} else {
			ele.set_text(text);
		}
		
		InfantiumResponse response = infantium.addElement(ele);
		
		if(response.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddTextElement successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddTextElement failed: " + response.toString() + " -");
		
		return response;
    }
    
    
    public InfantiumResponse addShapeElement(String element_id, int width, int height, String movement, int n_sides){
    	ShapeElement ele = new ShapeElement(element_id);
		if(width != -1 && height != -1) {
			ele.set_size(width, height);
		}
		if(!movement.equals("")) {
			ele.set_movement(movement);
		}
		if(n_sides != -1) {
			ele.set_n_sides(n_sides);
		}
		
		InfantiumResponse resp = infantium.addElement(ele);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddShapeElement successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddShapeElement failed: " + resp.toString() + " -");
		
		return resp;
    }
    
    
    public InfantiumResponse addPaintedElement(String element_id, int width, int height){
    	PaintedElement ele = new PaintedElement(element_id);
		if(width != -1 && height != -1) {
			ele.set_size(width, height);
		}
		
		InfantiumResponse response = infantium.addElement(ele);
		
		if(response.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddPaintedElement successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddPaintedElement failed: " + response.toString() + " -");
		
		return response;
    }
    
    public InfantiumResponse addPictureElement(String element_id, int width, int height){
    	PictureElement ele = new PictureElement(element_id);
		if(width != -1 && height != -1) {
			ele.set_size(width, height);
		}
		
		InfantiumResponse response = infantium.addElement(ele);
		
		if(response.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddPictureElement successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddPictureElement failed: " + response.toString() + " -");
		
		return response;
    }
    
    public InfantiumResponse addGoal(String goal_id, long time_limit, boolean auto_eval, String instructions){

    	Goal goal = new Goal(goal_id);
		if(time_limit != -1L) {
			goal.set_time_limit(time_limit);
		}
		goal.set_auto_eval(auto_eval);
		if(!instructions.equals("")) {
			goal.set_instructions(instructions);
		}
		
		InfantiumResponse resp = infantium.addGoal(goal);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddGoal successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddGoal failed: " + resp.toString() + " -");
		
		return resp;
    }
    
    
    public InfantiumResponse addSelectionGoal(String goal_id, long time_limit, boolean auto_eval, String instructions, 
    											int n_correct_choices, int n_incorrect_choices, boolean unique_solution, 
    											String needed_action){
    	SelectionGoal goal = new SelectionGoal(goal_id);
		if(time_limit != -1L) {
			goal.set_time_limit(time_limit);
		}
		goal.set_auto_eval(auto_eval);
		if(!instructions.equals("")) {
			goal.set_instructions(instructions);
		}
		if(n_correct_choices != -1) {
			goal.set_n_correct_choices(n_correct_choices);
		}
		if(n_incorrect_choices != -1) {
			goal.set_n_incorrect_choices(n_incorrect_choices);
		}
		goal.set_unique_solution(unique_solution);
		if(!needed_action.equals("")) {
			goal.set_needed_action(needed_action);
		}
		
		InfantiumResponse resp = infantium.addGoal(goal);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddSelectionGoal successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddSelectionGoal failed: " + resp.toString() + " -");
		
		return resp;
    }
    
    public InfantiumResponse addMatchingGoal(String goal_id, long time_limit, boolean auto_eval, 
    		String instructions, String matching_element, String correspondence_type){
    	MatchingGoal goal = new MatchingGoal(goal_id, matching_element);
		if(time_limit != -1L) {
			goal.set_time_limit(time_limit);
		}
		goal.set_auto_eval(auto_eval);
		if(!instructions.equals("")) {
			goal.set_instructions(instructions);
		}
		if(!correspondence_type.equals("")) {
			goal.add_correspondence_by(correspondence_type);
		}
		
		InfantiumResponse resp = infantium.addGoal(goal);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddMatchingGoal successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddMatchingGoal failed: " + resp.toString() + " -");
		
		return resp;
	}
    
    public InfantiumResponse addTappingGoal(String goal_id, long time_limit, boolean auto_eval, String instructions, String element_to_tap){
		TappingGoal goal = new TappingGoal(goal_id, element_to_tap);
		if(time_limit != -1L) {
			goal.set_time_limit(time_limit);
		}
		goal.set_auto_eval(auto_eval);
		if(!instructions.equals("")) {
			goal.set_instructions(instructions);
		}
		
		InfantiumResponse resp = infantium.addGoal(goal);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddTappingGoal successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddTappingGoal failed: " + resp.toString() + " -");
		
		return resp;
    }
    

    public InfantiumResponse addNBackGoal(String goal_id, int nback, String[] elements, boolean dual, boolean requires_match, String[] properties){
		NBackGoal goal = new NBackGoal(goal_id, nback, Arrays.asList(elements));

		goal.set_dual(dual);
		goal.set_requires_match(requires_match);
		goal.set_properties(Arrays.asList(properties));
		
		InfantiumResponse resp = infantium.addGoal(goal);
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- AddNbackGoal successful -");
		else
			Log.e(InfantiumUnity.TAG, "- AddNbackGoal failed: " + resp.toString() + " -");
		
		return resp;
    }


    public InfantiumResponse newBasicInteraction(String interaction_t, String object_type, String goal_type, int lifetime, int n_concurrent_oks, int n_concurrent_kos){
		
		if (object_type.equals("")) {
			object_type = null;
		}
		
		InfantiumResponse resp;
		
		if(lifetime == -1 && n_concurrent_oks == -1 && n_concurrent_kos == -1) {
			resp = infantium.newBasicInteraction(interaction_t, object_type, goal_type);
			
		} else if (n_concurrent_oks == -1 && n_concurrent_kos == -1) {
			resp = infantium.newBasicInteraction(interaction_t, object_type, goal_type, lifetime);
			
		} else if (lifetime == -1) {
			resp = infantium.newBasicInteraction(interaction_t, object_type, goal_type, 
					n_concurrent_oks, n_concurrent_kos);
			
		} else {
			resp = infantium.newBasicInteraction(interaction_t, object_type, goal_type, lifetime,
					n_concurrent_oks, n_concurrent_kos);	
		}
		
		if(resp.equals(InfantiumResponse.Valid))
			if(Conf.D) Log.i(InfantiumUnity.TAG, "- NewBasicInteraction successful -");
		else
			Log.e(InfantiumUnity.TAG, "- NewBasicInteraction failed: " + resp.toString() + " -");
		
		return resp;
    }
    
}
