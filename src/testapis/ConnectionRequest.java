/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author lucasbouvarel
 */

public class ConnectionRequest {
    private String identifier;
    private String password;
    private String encryptedPassword;
    
  
   public void setIdentifier(String identifier){
       this.identifier=identifier;
   }
   public void setPassword(String password){
       this.password=password;
   }
   public void setEncryptedPassword(String encryptedPassword){
       this.encryptedPassword=encryptedPassword;
   }

}
