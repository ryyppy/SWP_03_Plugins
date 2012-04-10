package myfirst;

import plugin.AbstractPlugin;


public class Plugin extends AbstractPlugin {

	public Plugin(){
        super("Kunden-Plugin");
		properties.put("Vorname", "");
        properties.put("Nachname", "");
        properties.put("Adresse", "");
	}
	


}
