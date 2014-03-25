using UnityEngine;
using System.Collections;

public class InfantiumExample : MonoBehaviour {
	public GUIText number_output_text;
	AndroidJavaClass pluginActivityJavaClass;
	
	void Start () 
	{
		AndroidJNI.AttachCurrentThread();
		pluginActivityJavaClass = new AndroidJavaClass("com.infantium.unityplugin");
	}
	
	void Update()
	{
		int number = pluginActivityJavaClass.CallStatic<int>("getNumber");
		number_output_text.text = "nr: " + number;
	}
}