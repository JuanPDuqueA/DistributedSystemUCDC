package ws.abhis.examples.RMIHelloWorld.server;

//define serializable interface
public interface WelcomeMessageDef {
	public boolean sendMessage(Long message);
	public String getMessages();
}
