package ws.abhis.examples.RMIHelloWorld.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {	
	
	//start server
	public static void main(String[] args) {
		System.out.println("opening server for incoming connections...");
		new ClassPathXmlApplicationContext("rmiServerAppContext.xml");
	}
}
