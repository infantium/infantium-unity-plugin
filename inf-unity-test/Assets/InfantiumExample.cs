using UnityEngine;
using System.Collections;

public class InfantiumExample : MonoBehaviour {
	InfantiumBypass infantium;
	void Start () 
	{
		AndroidJNIHelper.debug = true; 
		this.infantium = new InfantiumBypass("androidSDK","585f946642aac847403bf6743f918892eac695f6",100,100);
		this.infantium.initInfantium();
	}
	
	void Update()
	{
	}

}