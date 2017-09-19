package ws.abhis.examples.RMIHelloWorld.server;

//define serializable interface
public interface WelcomeMessageDef {
	public boolean sendMessage(String message);
	public String getMessages();
}
