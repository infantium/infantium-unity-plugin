package com.infantium.unityplugin;


import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.infantium.android.sdk.InfantiumAsyncResponseHandler;
import com.infantium.android.sdk.constants.Conf;

import com.unity3d.player.UnityPlayer;

public class InfantiumCallbackHandler{
	private static final String LOG_TAG = "Infantium ANE Android";
	private static final String UNITY_RECIVE_OBJECT= "Infantium ANE Android";

	
	private static final String INIT = "initFunction";
	private static final String SET_CONTENTAPP_UUID = "setContentAppUUID";
	private static final String ADD_ELEMENT = "addElement";
	private static final String ADD_NUMBER_ELEMENT = "addNumberElement";
	private static final String ADD_TEXT_ELEMENT = "addTextElement";
	private static final String ADD_SHAPE_ELEMENT = "addShapeElement";
	private static final String ADD_PAINTED_ELEMENT = "addPaintedElement";
	private static final String ADD_PICTURE_ELEMENT = "addPictureElement";
	private static final String ADD_GOAL = "addGoal";
	private static final String ADD_SELECTION_GOAL = "addSelectionGoal";
	private static final String ADD_MATCHING_GOAL = "addMatchingGoal";
	private static final String ADD_TAPPING_GOAL = "addTappingGoal";
	private static final String NEW_BASIC_INTERACTION = "newBasicInteraction";
	private static final String CREATE_GAMEPLAY = "createGameplay";
	private static final String CLOSE_GAMEPLAY = "closeGameplay";
	private static final String START_PLAYING = "startPlaying";
	private static final String SEND_GAME_RAWDATA = "sendGameRawdata";
	private static final String SEND_EBOOK_RAWDATA = "sendEbookRawdata";
	private static final String RETURN_TO_INFANTIUM_APP = "returnToInfantiumApp";
	private static final String SET_SUBCONTENT = "setSubContentUUID";
	private static final String ADD_DYNAMIC_FIELD = "addDynamicField";
	private static final String ON_PAUSE_INFANTIUM = "onPauseInfantium";
	private static final String ON_RESUME_INFANTIUM = "onResumeInfantium";
	
	public static final InfantiumAsyncResponseHandler handler = new InfantiumAsyncResponseHandler() {
		@Override
		public void onSuccessContentApp() {
			if(Conf.D) Log.i(LOG_TAG, "Content App success callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, SET_CONTENTAPP_UUID, "SUCCESS");
		};
		
		@Override
		public void onFailureContentApp(String description) {
			if(Conf.D) Log.i(LOG_TAG, "Content App failure callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, SET_CONTENTAPP_UUID, "FAILURE");
		};
		
		@Override
		public void onSuccessCreateGameplay() {
			if(Conf.D) Log.i(LOG_TAG, "CreateGameplay success callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, CREATE_GAMEPLAY, "SUCCESS");
		};
		
		@Override
		public void onFailureCreateGameplay(String description) {
			if(Conf.D) Log.i(LOG_TAG, "CreateGameplay failure callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, CREATE_GAMEPLAY, "FAILURE");
		};

		@Override
		public void onSuccessCloseGameplay() {
			if(Conf.D) Log.i(LOG_TAG, "CloseGameplay success callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, CLOSE_GAMEPLAY, "SUCCESS");
		};
		
		@Override
		public void onFailureCloseGameplay(String description) {
			if(Conf.D) Log.i(LOG_TAG, "CloseGameplay failure callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, CLOSE_GAMEPLAY, "FAILURE");
		};

		@Override
		public void onSuccessGameRawData() {
			if(Conf.D) Log.i(LOG_TAG, "SendGameRawdata success callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, SEND_GAME_RAWDATA, "SUCCESS");
		};
		
		@Override
		public void onFailureGameRawdata(String description) {
			if(Conf.D) Log.i(LOG_TAG, "SendGameRawdata failure callback");
			UnityPlayer.UnitySendMessage(UNITY_RECIVE_OBJECT, SEND_GAME_RAWDATA, "FAILURE");
		};
	};
}