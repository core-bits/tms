/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Tommy
 */
@Named(value = "pretty")
@RequestScoped
public class PrettyNavigation implements Serializable{

    public PrettyNavigation() {
    }
    
    public String homePage(){
        System.out.println("homePage button clicked");
        return "pretty:home";
    }
    
    public String registrationPage(){
        return "pretty:newmember";
    }
    
    public String newuserPage(){
        return "pretty:createuser";
    }    
    
    public String viewmembers(){
        return "pretty:members";
    }
    
    public String contributionModification(){
        return "pretty:modifycontribution";
    }
    
    public String loanApplication(){
        return "pretty:loanapplication";
    }
}
