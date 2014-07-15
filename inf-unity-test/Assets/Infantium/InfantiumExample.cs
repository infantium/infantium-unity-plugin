/* 
 * INFANTIUM UNITY PLUGIN
 * Author:  Marc Pomar (marc@infanitum.com)
 *
 */

using UnityEngine;
using System;
using System.Collections;
using System.Collections.Generic;

public class InfantiumExample : MonoBehaviour {
	InfantiumPlugin infantium;
	
	/* 
	 * - INFANTIUM UNITY SAMPLE: -
	 *   This code only shows calls to functions, please place function calls
	 *   on apropiate place depending on your game behaviour.
	 */
	
	void Start () 
	{
		// INFO: Just to debug some android-unity comunication
		AndroidJNIHelper.debug = true; 
		
		string server = "api"; // Change to "api" for production
		Boolean debug = true;
		Debug.Log(string.Format("Setup infantium server={0}",server));
		
		// Create InfantiumPlugin instance and initialize it
		this.infantium = new InfantiumPlugin("androidSDK","585f946642aac847403bf6743f918892eac695f6",100,100, server, debug);
		
		// InfantiumResponse enum encapsulates infantium native sdk response
		InfantiumResponse response;
		
		// Set contentapp_uuid
		response = this.infantium.setContentAppUUID("b771a22dca6c4c58aa007e9c28687219");
		
		// Important!! initialize infantium with onResume method
		this.infantium.onResumeInfantium();
		
		// Create a new gameplay using subcontent_uuid
		response = this.infantium.createGameplay("74a75f13b2604bce889400c677c04f6b");
		
		// Add element to current gameplay
		response = this.infantium.addElement("element1", 10, 10, "circular");
		
		// Add goal to current gameplay
		response = this.infantium.addGoal("element1_goal", 10, true, "");

		response = this.infantium.addSelectionGoal("goalId", -1, false, "", 1, 3, true, "");

		response = this.infantium.addNBackGoal("nbackGoal3", 3, new string[]{"element1"}, true, true, new string[]{"color","shape"});
		
		// Initialize timers, game has been started!
		this.infantium.startPlaying();
		
		// DEMO: Generate a sample interacion
		this.infantium.newBasicInteraction("success", "element1", "element1_goal", 10, 5, 5);
		
		// Send rawdata to infantium servers (don't worry about connection, infantiumSDK handles that for you)
		this.infantium.sendGameRawdata();
		
		// Close Gameplay: Important!! Remember to close gameplay
		this.infantium.closeGameplay();
		
		// Return to infantium
		this.infantium.returnToInfantiumApp();
	}
	
	void Update()
	{
	}
	
}