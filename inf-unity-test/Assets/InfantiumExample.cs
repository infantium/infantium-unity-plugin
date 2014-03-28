using UnityEngine;
using System.Collections;

public class InfantiumExample : MonoBehaviour {
	InfantiumPlugin infantium;
	void Start () 
	{
		AndroidJNIHelper.debug = true; 
		this.infantium = new InfantiumPlugin("androidSDK","585f946642aac847403bf6743f918892eac695f6",100,100);
		this.infantium.initInfantium();
		AndroidJavaObject response = this.infantium.addElement("element1", 10, 10, "circular");

	}
	
	void Update()
	{
	}

}