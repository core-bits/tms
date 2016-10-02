/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.utils;

/**
 *
 * @author Tommy
 */
public class EmailMessageBuilder {
    
    public String approveMember(String memberName, String serviceURL){
        StringBuilder builder = new StringBuilder();
        builder.append("Dear ").append(memberName).append("\n\n").
                append("Your registration have been approved. Kindly proceed to the login page to login.").append("\n\n").
                append("URL:").append(serviceURL).append("\n\n").append("Regards.");
                return builder.toString();
    }
}
