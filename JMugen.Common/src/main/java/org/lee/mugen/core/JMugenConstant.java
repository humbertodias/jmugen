package org.lee.mugen.core;

import java.util.ResourceBundle;

public interface JMugenConstant {
	// TODO: Review
	//String RESOURCE = ResourceBundle.getBundle("data").getString("resource");
	String RESOURCE = System.getProperty("data","resource/");
}
