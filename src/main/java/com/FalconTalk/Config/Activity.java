package com.FalconTalk.Config;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Activity {
	public static AppiumDriver<MobileElement> driverActivity;
	public static DesiredCapabilities cap;
	
	
	public static void AndroidLaunchApp() throws MalformedURLException {
		String device = System.getProperty("deviceName");
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability("autoGrantPermissions","true");
		//cap.setCapability(MobileCapabilityType.NO_RESET,"true");
		//cap.setCapability(MobileCapabilityType.FULL_RESET,"false");
		cap.setCapability("appPackage", "com.dassault.HONfalcontalk");
		cap.setCapability("appActivity", "com.sb.app.application.SplashScreenActivity");
		driverActivity = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		AppDriver.setDriver(driverActivity);
		
	}
	
	public static void CloseApp() {
		driverActivity.quit();
	}
	public static String deviceName() {
		String devicName = driverActivity.getCapabilities().getCapability("deviceModel").toString();
		return devicName;
	}

}
