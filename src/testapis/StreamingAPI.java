/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;
import com.lightstreamer.ls_client.*;

/**
 *
 * @author lucasbouvarel
 */
public class StreamingAPI {
    private static final String MARKET_L1_PATTERN = "MARKET:{epic}";
    
    public StreamingAPI(){
        
    }
    
    public HandyTableListenerAdapter subscribeForMarket(String epic,
			HandyTableListenerAdapter adapter, LSClient lsClient) throws Exception {
        String subscriptionKey = MARKET_L1_PATTERN.replace("{epic}", epic);

        ExtendedTableInfo extendedTableInfo = new ExtendedTableInfo(
                        new String[]{subscriptionKey}, "MERGE", new String[]{"BID",
                                        "OFFER", "MARKET_STATE", "CHANGE"}, true);

        final SubscribedTableKey subscribedTableKey = lsClient.subscribeTable(
                        extendedTableInfo, adapter, false);
        adapter.setSubscribedTableKey(subscribedTableKey);
        return adapter;
	}
}
