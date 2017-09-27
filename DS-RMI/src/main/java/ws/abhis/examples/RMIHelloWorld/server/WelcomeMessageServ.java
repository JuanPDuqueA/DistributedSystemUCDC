package ws.abhis.examples.RMIHelloWorld.server;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

//define server operation
public class WelcomeMessageServ implements WelcomeMessageDef {
    private static Logger logger = Logger.getLogger(WelcomeMessageServ.class);
    private String text;

    public boolean sendMessage(Long message) {
        long startTime = System.nanoTime();
        int counter = 0;
        for (Long i = 0L;i<=message; i++) {
            if (checkIfPrime(i)){

            logger.info("\n Este es el primo  "+i);
                counter++;}
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        text = counter + ","+ duration;
        return true;
    }

    public String getMessages() {
        return this.text;
    }

    private boolean checkIfPrime(Long n) {
        if (n%2==0) return false;
        for(Long i=3L;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public void killMySelf() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("shutdown -h now");
    }
}
