package ws.abhis.examples.RMIHelloWorld.server;

import java.io.IOException;

//define serializable interface
public interface WelcomeMessageDef {
	public boolean sendMessage(Long message);
	public String getMessages();
	public void killMySelf() throws IOException;
}
