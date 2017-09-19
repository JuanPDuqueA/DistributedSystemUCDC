package ws.abhis.examples.RMIHelloWorld.server;


import org.apache.log4j.Logger;

//define server operation
public class WelcomeMessageServ implements WelcomeMessageDef {
	private static Logger logger = Logger
			.getLogger(WelcomeMessageServ.class);
	private static String fileName = "welbook.dat";
	private String text;

	public boolean sendMessage(String message) {
		this.text = message.toLowerCase();
		return true;
	}

	public String getMessages() {
		return this.text;
	}
}
