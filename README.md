## Infantium Unity Android Plugin

#### How to use it
```c#
		// FILL WITH YOUR INFANTIUM CREDENTIALS
		string api_user = "<your_api_user_here>";
		string api_key = "<your_api_key_here>";
		string contentAppUUID = "<your_contentAppUUID_here>";
		string subContentUUID = "<your_subContentUUID_here>";

		// Create InfantiumPlugin instance and initialize it
		this.infantium = new InfantiumPlugin(api_user, api_key, Screen.width, Screen.height);

		// InfantiumResponse enum encapsulates infantium native sdk response
		InfantiumResponse response;

		// Set contentapp_uuid
		response = this.infantium.setContentAppUUID(contentAppUUID);

		// Important!! initialize infantium with onResume method
		this.infantium.onResumeInfantium();

		// Create a new gameplay using subcontent_uuid
		response = this.infantium.createGameplay(subContentUUID);

		// Add element to current gameplay
		response = this.infantium.addElement("element1", 10, 10, "circular");

		// Add goal to current gameplay
		response = this.infantium.addGoal("element1_goal", 10, true, "");

		// Initialize timers, game has been started!
		this.infantium.startPlaying();

		// DEMO: Generate a sample interacion
		this.infantium.newBasicInteraction("success", "element1", "element1_goal", 10, 5, 5);

		// Send rawdata to infantium servers (don't worry about connection, infantiumSDK handles that for you)
		this.infantium.sendGameRawdata();

		// Close Gameplay: Important!! Remember to close gameplay
		this.infantium.closeGameplay();
```

**Some References:**
http://docs.unity3d.com/Documentation/Manual/PluginsForAndroid.html

