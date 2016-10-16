
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.ReportsController;
import com.corebits.ericsson.tms.models.AccountType;
import com.corebits.ericsson.tms.models.Accounts;
import com.corebits.ericsson.tms.models.Journal;
import com.corebits.ericsson.tms.utils.AccountDTO;
import com.corebits.ericsson.tms.utils.BalanceSheetDTO;
import java.util.ArrayList;
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
    
    public List<BalanceSheetDTO> getBalanceSheet(){
        List<AccountType> acctTypeList = rc.getAccountTypeList();
        List<BalanceSheetDTO> balanceSheetList = new ArrayList<>();               
        acctTypeList.stream().map((AccountType type) -> {
            List<Accounts> accountList = rc.getAccountListByAccountType(type);
            List<AccountDTO> acctDTOList = new ArrayList<>();            
            double acctTypeTotal = 0.0;
            for(Accounts account : accountList){
                List<Journal> entries = rc.getAccountEntriesByAccount(account);
                double credit = 0.0;
                double debit = 0.0; 
                for(Journal entry : entries){
                    if(entry.getCredit() > 0){
                        credit += entry.getCredit();
                    }else{
                        debit += entry.getDebit();
                    }
                }
                acctTypeTotal += credit + debit;
                AccountDTO acctDTO = new AccountDTO(account, credit, debit);
                acctDTOList.add(acctDTO);  
            }
            BalanceSheetDTO balanceSheet = new BalanceSheetDTO(type, acctDTOList, acctTypeTotal);              
            return balanceSheet;
        }).forEach((balanceSheet) -> {
            balanceSheetList.add(balanceSheet);
        });
        return balanceSheetList;
    }
    
    
}
