/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
/**
 *
 * @author lucasbouvarel
 */
public class TestAPIs {

    
    
    
    public static String readURL(String url) throws IOException {
        try(InputStream is = new URL(url).openConnection().getInputStream()) {			
          BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));

          StringBuilder builder = new StringBuilder();
          for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line + "\n");
          }
          return builder.toString();
        }
    }
    /**
     * @param args the command line arguments
     * 
     * 
     * 
     */
    
    
    public static void main(String[] args) throws IOException {
        JSONObject json = new JSONObject();
        json.put("identifier", "eticlyon");
        json.put("password", "Insalyon69");

        // Cannot set null directly
        json.put("encryptedPassword", JSONObject.NULL);
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost("https://demo-api.ig.com/gateway/deal/session");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json; charset=UTF-8");
            request.addHeader("X-IG-API-KEY","13ff8b06150263876f0f6a39af9a3b19bc7a3605");
            request.addHeader("Version","1");
            request.setEntity(params);
            client.execute(request);
            
            // handle response here...
            HttpResponse response = client.execute(request);
            System.out.println("Response Code : " + 
                   response.getStatusLine().getStatusCode());
            Header[] headers = response.getAllHeaders();
            
            for (Header header : headers) {
                    System.out.println( header.getName() 
                          + ": " + header.getValue());
            }
            BufferedReader rd = new BufferedReader(
                   new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                    result.append(line);
            }

            System.out.println(result.toString());
            } catch (Exception ex) {
                // handle exception here
            } finally {
                client.close();
            }
            
    }
    
}
