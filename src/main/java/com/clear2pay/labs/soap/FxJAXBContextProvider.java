package com.clear2pay.labs.soap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Singleton class to create Jaxb Context for ESB Payment Hub services
 */
public final class FxJAXBContextProvider {

	private static JAXBContext instance;
	private static JAXBContext esbInstance;

	private FxJAXBContextProvider() {

	}

	/**
	 * initContext
	 * 
	 * @return
	 */
	public static synchronized JAXBContext initContext() {
		try {
			if (instance == null) {
				instance = JAXBContext.newInstance(com.adib.customerrelation.ObjectFactory.class);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * initContext
	 * 
	 * @return
	 */
	public static synchronized JAXBContext initEsbContext() {
		try {
			if (esbInstance == null) {
				esbInstance = JAXBContext.newInstance(com.esbbank.gbo.xml.schemas.v1_0.ObjectFactory.class);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return esbInstance;
	}
}
