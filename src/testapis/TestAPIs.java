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
import com.lightstreamer.ls_client.*;
import java.util.ArrayList;


/**
 *
 * @author lucasbouvarel
 */
public class TestAPIs {

    /**
     * @param args the command line arguments
     * 
     * 
     * 
     */
    public static double marketOffer=0.0;
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
            Header Hcst = response.getFirstHeader("CST");
            Header Hxst = response.getFirstHeader("X-SECURITY-TOKEN");
            String cst = Hcst.getValue();
            String xst = Hxst.getValue();
            System.out.println("CSTTTTTTT : "
                  +            cst);
            System.out.println("XCSCSCSCSCS : "
                  +            xst);
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
            
        //Nouveau client
            LSClient lsClient =new LSClient();
            ConnectionInfo connectionInfo=new ConnectionInfo();
            connectionInfo.user="eticLyon";
            connectionInfo.password="CST-" + cst + "|XST-" +xst;
            System.out.println(connectionInfo.password);
            connectionInfo.pushServerUrl="https://demo-apd.marketdatasystems.com";
            
        //connection
            ConnectionListenerAdapter adapter=new ConnectionListenerAdapter();
            System.out.println("hey");
            lsClient.openConnection(connectionInfo, adapter);
            
            String tradeableEpic = "CS.D.EURUSD.CFD.IP";
            System.out.println("passé");
            
        //Array de listener
            ArrayList<HandyTableListenerAdapter> listeners = new ArrayList<>();
            System.out.println("salut");
            StreamingAPI streamingApi=new StreamingAPI();
            listeners.add(streamingApi.subscribeForMarket(tradeableEpic, new HandyTableListenerAdapter() {
                public void onUpdate(int i, String s, UpdateInfo updateInfo) {
                   marketOffer = Double.valueOf(updateInfo.getNewValue("OFFER"));
                   System.out.println("EPIC "+tradeableEpic +"   Offer price: "+marketOffer);
                }
            }, lsClient
            ));
            while(true){
              
            }
            
        } catch (Exception ex) {
            // handle exception here
        } finally {
            client.close();
        }
        
    }
    
    /*private static void logMessage(int x, int y, String message) {
      logMessage(x, y, message, Ansi.Color.DEFAULT, null);
   }*/
    
}
