/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.TransactionController;
import com.corebits.ericsson.tms.models.Operations;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "operations")
@ViewScoped
public class OperationsMbeans implements Serializable{

    @Inject
    TransactionController tc;
    
    @ManagedProperty(value = "#{transaction}")
    TransactionMbean tbean;

    private String name;
    private String descriptions;
    private List<Operations> operations;

    public OperationsMbeans() {
    }
    
    @PostConstruct
    private void init(){
        System.out.println("Operation mbean initialized...");
        operations = tc.getOperations();
    }

    public void createOperation() {
        FacesMessage message;
        Operations o = new Operations();
        o.setOperationDescription(descriptions);
        o.setOperationName(name);
        try {
            tc.persist(o);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Completed successfully");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Failed to complete your request");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        operations = tc.getOperations();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

}
