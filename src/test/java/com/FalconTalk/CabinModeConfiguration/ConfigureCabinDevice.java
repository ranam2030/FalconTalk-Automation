package com.FalconTalk.CabinModeConfiguration;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.FalconTalk.Config.ActiavityMain;
import com.FalconTalk.Config.AppiumServer;
import com.FalconTalk.Data.CabinModeData;
import com.FalconTalk.PageActions.CabinModeConfigurationPage;

class ConfigureCabinDevice {
	
	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {
		AppiumServer.Start();
		ActiavityMain.AndroidLaunchApp();
		Thread.sleep(5000);
	}
	
	@Test(priority=0)
	public void ConfigureCabinDevicePageTest() throws InterruptedException {  //TC112
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		cabinmode.getNavDrawer();
		Thread.sleep(5000);
		cabinmode.getCabinMode();
		Assert.assertEquals(cabinmode.getCabinPage(),CabinModeData.CabinPage , "Can't go to cabin page");
	}
	
	@Test(priority=1)
	public void TostMessageTest() throws InterruptedException {  //TC113
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		cabinmode.pressValidateButton();
		Assert.assertEquals(cabinmode.getTostMessage(),CabinModeData.TostMessage , "Tost Message Showing Wrong");
	}
	
	@Test(priority=2)
	public void  CabinAccountAndExtensionSelectionPageTest() { //TC114
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		cabinmode.pushPassword(CabinModeData.CabinPagePassword);
		cabinmode.pressValidateButton();
		Assert.assertEquals(cabinmode.getRingGroupPageforCabinMode(),CabinModeData.CabinModePage , "Can't Go to Cabin mode page");
	}
	
	
	@Test(priority=3)
	public void AllCabinAccountIsFreeTest() { //TC116
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		String st;
		int i=0;
		for(i=1;i<7;i++) {
			st = cabinmode.getCabinNameWithExtension(i);
			//System.out.println(st.contains("empty"));
			Assert.assertEquals(st.contains("empty"),true , "Account list is not empty");
		}
		
	}
	
	@Test(priority = 4)
	public void CabinAccountandExtensionRangeTest() { //TC115
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		
			boolean resAxxess = cabinmode.getExtensionList(1).equals(CabinModeData.ExtensionListAxxess);
			Assert.assertEquals(resAxxess, true);
			
			boolean resCG_710_Legacy = cabinmode.getExtensionList(2).equals(CabinModeData.ExtensionListCG_710_Legacy);
			Assert.assertEquals(resCG_710_Legacy, true);
			
			boolean resCG_710_FC = cabinmode.getExtensionList(3).equals(CabinModeData.ExtensionListCG_710_FC);
			Assert.assertEquals(resCG_710_FC, true);
			
			boolean resCNX_900 = cabinmode.getExtensionList(4).equals(CabinModeData.ExtensionListCNX_900);
			Assert.assertEquals(resCNX_900, true);
			
			boolean resGDR = cabinmode.getExtensionList(5).equals(CabinModeData.ExtensionListGDR);
			Assert.assertEquals(resGDR, true);
			
			boolean resSDR = cabinmode.getExtensionList(6).equals(CabinModeData.ExtensionListSDR);
			Assert.assertEquals(resSDR, true);
			
	}
	
	@Test(priority = 5)
	public void ApplyButtonActiveTest() { //TC117
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		cabinmode.CabinAccountandExtensionSelection(1, 83);
		Assert.assertEquals(cabinmode.getApplyButton(), false, "Apply BUtton can't inactive");
		cabinmode.CabinAccountandExtensionSelection(2, 606);
		Assert.assertEquals(cabinmode.getApplyButton(), false, "Apply BUtton can't inactive");
		cabinmode.CabinAccountandExtensionSelection(3, 13);
		Assert.assertEquals(cabinmode.getApplyButton(), false, "Apply BUtton can't inactive");
		cabinmode.CabinAccountandExtensionSelection(4, 10);
		Assert.assertEquals(cabinmode.getApplyButton(), false, "Apply BUtton can't inactive");
		cabinmode.CabinAccountandExtensionSelection(5, 15);
		Assert.assertEquals(cabinmode.getApplyButton(), false, "Apply BUtton can't inactive");
		cabinmode.CabinAccountandExtensionSelection(6, 608);
		Assert.assertEquals(cabinmode.getApplyButton(), true, "Apply BUtton can active");
	}
	
	@Test(priority = 6)
	public void ApplyButtonPressAndAutoDiscoveryStartTest() { //TC118
		CabinModeConfigurationPage cabinmode = new CabinModeConfigurationPage();
		cabinmode.pressApplybutton();
		Assert.assertEquals(cabinmode.getAutoDiscoveryPage(), CabinModeData.AutoDiscoveryPageMessage, "Can't go AutoDiscovery Page");
	}
	
	@AfterTest
	public void tearDown() { 
		ActiavityMain.CloseApp();
		AppiumServer.Stop();
	}
}
