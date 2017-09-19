package ws.abhis.examples.RMIHelloWorld.client;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ws.abhis.examples.RMIHelloWorld.server.WelcomeMessageDef;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WelcomeMessageCli {
    private static Logger logger = Logger.getLogger(WelcomeMessageCli.class);

    //start client
    public static void main(String[] args) {
        logger.info("Starting client....");
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("rmiClientAppContext.xml");
            WelcomeMessageDef rmiClient = (WelcomeMessageDef) context.getBean("WelcomeM");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            logger.info("\n \n Ingrese el texto a pasar en minusculas: \n \n");
            String message = bufferRead.readLine();
            rmiClient.sendMessage(message);
            String msg = rmiClient.getMessages();
            logger.info("\n"+msg+"\n");
            logger.info("\n Presione enter para salir");
            System.in.read();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
