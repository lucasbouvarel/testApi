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
public class CreatePositionRequest {
    private String epic;
    private String expiry;
    private String direction;
    private String size;
    private String orderType;
    private String timeInForce;
    private String level;
    private String guaranteedStop;
    private String stopLevel;
    private String stopDistance;
    private String trailingStop;
    private String trailingStopIncrement;
    private String forceOpen;
    private String limitLevel;
    private String limitDistance;
    private String quoteId;
    private String currencyCode;
    
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
    public void setOrderType(String orderType){
        this.orderType=orderType;
    }
    public void setGuaranteedStop(String guaranteedStop){
        this.guaranteedStop=guaranteedStop;
    }
    public void setForceOpen(String forceOpen){
        this.forceOpen=forceOpen;
    }
    public void setCurrencyCode(String currencyCode){
        this.currencyCode=currencyCode;
    }
}
