// javac -cp grid-core-1.11.27.jar;. net\company\your\HelloWorld.java

package net.company.your;

import com.lawson.grid.node.application.ApplicationEntryPointEx;
import com.lawson.grid.node.application.ApplicationEntryPointEx.GlobalState;
import com.lawson.grid.node.application.ApplicationEntryPointEx.RemainingTaskCount;
import com.lawson.grid.node.application.ModuleContext;

import net.company.your.library1.*;

public class HelloWorld implements ApplicationEntryPointEx {

	private boolean isInitialized = false;

	public boolean startModule(ModuleContext paramModuleContext) {
		System.out.println(HelloWorldLibrary1.getMessage());
		return true;
	}

	public void onlineNotification() {
		this.isInitialized = true;
	}

	public void offlineNotification() {
		this.isInitialized = false;
	}

	public void stopModule() {
		this.isInitialized = false;
	}

	public ApplicationEntryPointEx.RemainingTaskCount getRemainingTaskCount() {
		return null;
	}

	public ApplicationEntryPointEx.GlobalState getGlobalState() {
		if (this.isInitialized) {
			return new ApplicationEntryPointEx.GlobalState(true, new String[] { "OK" });
		}
		return new ApplicationEntryPointEx.GlobalState(false, new String[] { "Initalizing" });
	}
}