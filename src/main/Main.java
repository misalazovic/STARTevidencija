package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import javax.swing.JOptionPane;
import logic.Logic;
import server.ServerConnection;

/**
 *
 * @author Misa
 */
public class Main {

    public static void main(String[] args) {

        String s;
        String path;
        String response;
        Date date;
        try {

            path = Logic.selectPath();

            Process p = Runtime.getRuntime().exec("\"" + path + "\"");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                date = new Date();
                response = new ServerConnection().sendData(new URL("http://localhost:8080/misa/index.php"), s.substring(s.indexOf(":") + 1), date, InetAddress.getLocalHost().getHostName());
                JOptionPane.showMessageDialog(null, response, "Odgovor", JOptionPane.PLAIN_MESSAGE); // QR-Code:'code'
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
