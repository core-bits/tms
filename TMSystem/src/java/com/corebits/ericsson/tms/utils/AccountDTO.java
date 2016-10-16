
package com.corebits.ericsson.tms.utils;

import com.corebits.ericsson.tms.models.Accounts;

/**
 *
 * @author xtphere
 */
public class AccountDTO {
    private Accounts account;
    private double totalDebit;
    private double totalCredit;
    
    public AccountDTO(){
        
    }

    public AccountDTO(Accounts account, double totalDebit, double totalCredit) {
        this.account = account;
        this.totalDebit = totalDebit;
        this.totalCredit = totalCredit;
    }   

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "account=" + account + ", totalDebit=" + totalDebit + ", totalCredit=" + totalCredit + '}';
    }
    
    
}
