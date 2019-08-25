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
public class ClosePositionRequest {
    private String dealId;
    private String epic;
    private String expiry;
    private String direction;
    private String size;
    private String level;
    private String orderType;
    private String timeInForce;
    private String quoteId;
    
    public void setDealId(String dealId){
        this.dealId=dealId;
    }
    public void setEpic(String epic){
        this.epic=epic;
    }
    public void setExpiry(String expiry){
        this.expiry=expiry;
    }
    public void setDirection(String direction){
        this.direction=direction;
    }
    public void setSize(String size){
        this.size=size;
    }
    public void setLevel(String level){
        this.level=level;
    }
    public void setOrderType(String orderType){
        this.orderType=orderType;
    }
    public void setTimeInForce(String timeInForce){
        this.timeInForce=timeInForce;
    }
    public void setQuoteId(String quoteId){
        this.quoteId=quoteId;
    }
}

