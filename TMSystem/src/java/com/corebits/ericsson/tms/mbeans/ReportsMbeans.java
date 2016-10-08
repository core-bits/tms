/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.ReportsController;
import com.corebits.ericsson.tms.models.Journal;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "reports")
@RequestScoped
public class ReportsMbeans {
    
    @Inject
    ReportsController rc;
    
    private List<Journal> journals;

    public ReportsMbeans() {
    }
    
    public List<Journal> getAllTransactions(){
        journals = rc.getAllTransactions();
        return journals;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
    
    
}
