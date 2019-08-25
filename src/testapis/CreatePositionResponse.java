/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;

/**
 *
 * @author lucasbouvarel
 */
public class CreatePositionResponse {
    private String dealReference;
    
    public void setDealReference(String dealReference){
        this.dealReference=dealReference;
    }
    public String getDealReference(){
        return this.dealReference;
    }
}
