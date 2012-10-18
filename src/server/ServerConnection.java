package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

/**
 *
 * @author Misa
 */
public class ServerConnection {

    private String data;

    public void setupConnection(String id, Date date, String verif) {
        try {
            data = URLEncoder.encode("IDKorisnika", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("Datum", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(date), "UTF-8");
            data += "&" + URLEncoder.encode("VerifikacioniUredjaj", "UTF-8") + "=" + URLEncoder.encode(verif, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }

    public String sendData(URL url, String id, Date date, String verif) {
        try {
            setupConnection(id, date, verif);

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
