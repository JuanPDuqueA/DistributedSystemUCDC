package ws.abhis.examples.RMIHelloWorld.starter;

import ws.abhis.examples.RMIHelloWorld.client.WelcomeMessageCli;
import ws.abhis.examples.RMIHelloWorld.server.Starter;

//start application
public class StartApp{
	public static void main(String[] args) throws Exception  {
		if (args[0].equals("server")) {
			Starter.main(null);
		} else if (args[0].equals("client")) {
			WelcomeMessageCli.main(null);
		}
	}
}
