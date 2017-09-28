package ws.abhis.examples.RMIHelloWorld.client;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ws.abhis.examples.RMIHelloWorld.server.WelcomeMessageDef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class WelcomeMessageCli {
    private static Logger logger = Logger.getLogger(WelcomeMessageCli.class);

    //start client
    public static void main(String[] args) throws Exception {
        logger.info("Starting client....");
        List<WelcomeMessageDef> clients = new ArrayList<WelcomeMessageDef>();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        logger.info("\n \n Ingrese el numero maximo: \n \n");
        String message = bufferRead.readLine();
        while (true) {
            ApplicationContext context = new ClassPathXmlApplicationContext("rmiClientAppContext.xml");
            clients.clear();
            Map<Integer, Long> times = new HashMap<Integer, Long>();
            for (int i = 0; i < 2; i++)
                try {
                    clients.add((WelcomeMessageDef) context.getBean("WelcomeM" + i));
                } catch (Exception e) {
                    logger.info(clients.size() + " servidores en linea");
                }
            if (clients.size() == 1) break;
            if (message.length() < 1 || message.length() > 15) {
                logger.error("Los valores digitados estan fuera del rango");
            } else {
                for (WelcomeMessageDef rmiClient : clients) {
                    rmiClient.sendMessage(Long.valueOf(message));
                    String msg = rmiClient.getMessages();
                    logger.info("\n" + msg + "\n");
                    times.put(clients.indexOf(rmiClient), Long.valueOf(msg.substring(msg.indexOf(",") + 1)));
                }
                Map.Entry<Integer, Long> maxEntry = Collections.max(times.entrySet(),
                        new Comparator<Map.Entry<Integer, Long>>() {
                            public int compare(Map.Entry<Integer, Long> e1, Map.Entry<Integer, Long> e2) {
                                return e1.getValue().compareTo(e2.getValue());
                            }
                        });
                clients.get(maxEntry.getKey()).killMySelf();
                Thread.sleep(3000);
            }
        }
        logger.info("\n Presione enter para salir");
        System.in.read();
    }

}