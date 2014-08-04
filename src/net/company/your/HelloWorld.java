// javac -cp grid-core-1.11.27.jar;. net\company\your\HelloWorld.java

package net.company.your;

import com.lawson.grid.node.properties.GridProperties;
import com.lawson.grid.node.application.ApplicationEntryPointEx;
import com.lawson.grid.node.application.ApplicationEntryPointEx.GlobalState;
import com.lawson.grid.node.application.ApplicationEntryPointEx.RemainingTaskCount;
import com.lawson.grid.node.application.ModuleContext;
import com.lawson.grid.util.logging.GridLogger;

import net.company.your.library1.*;

public class HelloWorld implements ApplicationEntryPointEx {

	private static final GridLogger log = GridLogger.getLogger(HelloWorld.class);

	private boolean isInitialized = false;

	public boolean startModule(ModuleContext paramModuleContext) {
		// use the Grid application library
		log.info(HelloWorldLibrary1.getMessage());
		// write to the Grid application log file
		log.info("Yay, I'm writing to the log file :)");
		log.warn("Hey, it feels hot in here!");
		log.error("Ouch, that hurt :(");
		log.debug("Useful data 48656C6C6F576F726C64");
		log.trace("Wooohooo 01001000 01100101 01101100 01101100 01101111 00100000 01010111 01101111 01110010 01101100 01100100 00100001");
		// get a Grid application property
		GridProperties p = ModuleContext.getCurrent().getProperties();
		String message = p.getProperty("message");
		log.info("The message is: " + message);
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