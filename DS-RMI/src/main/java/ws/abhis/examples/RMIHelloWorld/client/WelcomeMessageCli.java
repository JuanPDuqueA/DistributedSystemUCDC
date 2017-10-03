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
    private static Map<Integer, Long> times = new HashMap<>();
    private static List<WelcomeMessageDef> servers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        logger.info("Iniciando el cliente....");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        logger.info("\n \n Ingrese el numero a calcular los primos: \n \n");
        String message = bufferRead.readLine();
        while (true) {
            ApplicationContext context = new ClassPathXmlApplicationContext("rmiClientAppContext.xml");
            servers.clear();
            times.clear();
            for (int i = 0; i < 3; i++)
                try {
                     servers.add((WelcomeMessageDef) context.getBean("welcomeM" + i));
                } catch (Exception e) {
                    logger.info(servers.size() + " servidores en linea");
                }
            if (servers.size() == 1) break;
            if (message.length() < 1 || message.length() > 15) {
                logger.error("Los valores digitados estan fuera del rango");
            } else {

                Arrays.stream(servers.toArray()).parallel().
                        forEach(rmiServer -> execute(
                                (WelcomeMessageDef) rmiServer,message,servers.indexOf(rmiServer)));
                Map.Entry<Integer, Long> maxEntry = Collections.max(times.entrySet(),
                        new Comparator<Map.Entry<Integer, Long>>() {
                            public int compare(Map.Entry<Integer, Long> e1, Map.Entry<Integer, Long> e2) {
                                return e1.getValue().compareTo(e2.getValue());
                            }
                        });
                logger.info("El servidor numero "+maxEntry.getKey()+" ha perdido y sera apagado");
                servers.get(maxEntry.getKey()).killMySelf();
                Thread.sleep(3000);
            }
        }
        logger.info("\n Presione enter para salir");
        System.in.read();
    }
    private static void execute(WelcomeMessageDef rmiServer, String message, int serverNumber){
        rmiServer.sendMessage(Long.valueOf(message));
        String msg = rmiServer.getMessages();
        logger.info("\n" + msg + "\n");
        times.put(serverNumber, Long.valueOf(msg.substring(msg.indexOf(",") + 1)));
    }
}