/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.File;
import java.util.ArrayList;
import org.json.JSONArray;


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
    public static double bidOffer=0.0;
    public static double change=0.0;
    public static void main(String[] args) throws IOException {
        /*JSONObject json = new JSONObject();
        json.put("password", "Insalyon69");
        json.put("identifier", "eticlyon");
        */
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        ConnectionRequest connection= new ConnectionRequest();
        connection.setIdentifier("eticlyon");
        connection.setPassword("Insalyon69");
        
        String maconnection = objectMapper.writeValueAsString(connection);
        System.out.println(objectMapper.writeValueAsString(connection));
       
        // Cannot set null directly
        //json.put("encryptedPassword", JSONObject.NULL);
        
        CloseableHttpClient client = HttpClients.createDefault();
        
        
        try {
            HttpPost request = new HttpPost("https://demo-api.ig.com/gateway/deal/session");
            StringEntity params = new StringEntity(objectMapper.writeValueAsString(connection));
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json; charset=UTF-8");
            request.addHeader("X-IG-API-KEY","13ff8b06150263876f0f6a39af9a3b19bc7a3605");
            request.addHeader("Version","2");
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
            
        
        
        CreatePositionRequest myPosition= new CreatePositionRequest();
        myPosition.setEpic("CS.D.LTCUSD.CFD.IP");
        myPosition.setExpiry("-");
        myPosition.setDirection("BUY");
        myPosition.setSize("1");
        myPosition.setOrderType("MARKET");
        myPosition.setGuaranteedStop("false");
        myPosition.setForceOpen("true");
        myPosition.setCurrencyCode("USD");
        
        String maposition = objectMapper.writeValueAsString(myPosition);
        
        System.out.println("My position : "+ maposition);
            
                      
//open position

            request = new HttpPost("https://demo-api.ig.com/gateway/deal/positions/otc");
            client= HttpClients.createDefault();
            params = new StringEntity(maposition);
            System.out.println("");
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json; charset=UTF-8");
            request.addHeader("X-IG-API-KEY","13ff8b06150263876f0f6a39af9a3b19bc7a3605");
            request.addHeader("Version","2");
            request.addHeader("X-SECURITY-TOKEN",xst);
            request.addHeader("CST",cst);
            request.setEntity(params);
            System.out.println("");
            System.out.println("");
            
            System.out.println("envoie du post");
            response = client.execute(request);
            System.out.println("post envoyé");
            
            
            
            System.out.println("Response Code : " + 
                   response.getStatusLine().getStatusCode());
            rd = new BufferedReader(
                   new InputStreamReader(response.getEntity().getContent()));

            result = new StringBuffer();
            line = "";
            while ((line = rd.readLine()) != null) {
                    result.append(line);
            }

            System.out.println(result.toString());
            String retour = result.toString();
            CreatePositionResponse reponse=objectMapper.readValue(retour,CreatePositionResponse.class);
            System.out.println("Objet mapper : "+reponse.getDealReference());
            System.out.println("");
            System.out.println("FERMETURE DE LA POSITION");
            System.out.println("");
            CreatePositionRequest fermeture=new CreatePositionRequest();
            
            fermeture.setEpic("CS.D.LTCUSD.CFD.IP");
            fermeture.setExpiry("-");
            fermeture.setDirection("SELL");
            fermeture.setSize("1");
            fermeture.setOrderType("MARKET");
            fermeture.setGuaranteedStop("false");
            fermeture.setForceOpen("false");
            fermeture.setCurrencyCode("USD");
            
            String maFermeture = objectMapper.writeValueAsString(fermeture);
            
            System.out.println("fermeture : " + maFermeture);
            
            
            request = new HttpPost("https://demo-api.ig.com/gateway/deal/positions/otc");
            client= HttpClients.createDefault();
            params = new StringEntity(maFermeture);
            System.out.println("");
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json; charset=UTF-8");
            request.addHeader("X-IG-API-KEY","13ff8b06150263876f0f6a39af9a3b19bc7a3605");
            request.addHeader("Version","2");
            request.addHeader("X-SECURITY-TOKEN",xst);
            request.addHeader("CST",cst);
            
            request.setEntity(params);
            System.out.println("");
            System.out.println("");
            
            Thread.sleep(1000);
            System.out.println("envoie du post");
            response = client.execute(request);
            System.out.println("post envoyé");
            
            
            
            System.out.println("Response Code de la fermeture : " + 
                   response.getStatusLine().getStatusCode());
            
            rd = new BufferedReader(
                   new InputStreamReader(response.getEntity().getContent()));

            result = new StringBuffer();
            line = "";
            while ((line = rd.readLine()) != null) {
                    result.append(line);
            }
            System.out.println(result.toString());
            
            System.out.println("");
            System.out.println("");
            System.out.println("");
        //Nouveau client
            LSClient lsClient =new LSClient();
            ConnectionInfo connectionInfo=new ConnectionInfo();
            connectionInfo.user="eticLyon";
            connectionInfo.password="CST-" + cst + "|XST-" +xst;
            connectionInfo.pushServerUrl="https://demo-apd.marketdatasystems.com";
            
        //connection
            ConnectionListenerAdapter adapter=new ConnectionListenerAdapter();
            lsClient.openConnection(connectionInfo, adapter);
            
            String tradeableEpic = "CS.D.LTCUSD.CFD.IP";
            
        //Array de listener
            ArrayList<HandyTableListenerAdapter> listeners = new ArrayList<>();
            StreamingAPI streamingApi=new StreamingAPI();
            listeners.add(streamingApi.subscribeForMarket(tradeableEpic, new HandyTableListenerAdapter() {
                public void onUpdate(int i, String s, UpdateInfo updateInfo) {
                   marketOffer = Double.valueOf(updateInfo.getNewValue("OFFER"));
                   bidOffer=Double.valueOf(updateInfo.getNewValue("BID"));
                   change=Double.valueOf(updateInfo.getNewValue("CHANGE"));
                   System.out.println("EPIC "+tradeableEpic +"   Offer price: "+bidOffer);
                   System.out.println("EPIC "+tradeableEpic +"   Offer price: "+marketOffer);
                   System.out.println("CHANGE :" + change);
                }
            }, lsClient
            ));
            System.out.println("");
            System.out.println("");
            System.out.println("");
  
            
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
*/
 }
    

