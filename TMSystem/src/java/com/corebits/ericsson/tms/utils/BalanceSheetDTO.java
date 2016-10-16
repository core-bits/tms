
package com.corebits.ericsson.tms.utils;

import com.corebits.ericsson.tms.models.AccountType;
import java.util.List;

/**
 *
 * @author xtphere
 */
public class BalanceSheetDTO {
    private AccountType category;
    private List<AccountDTO> account;
    private double total;
    
    public BalanceSheetDTO(){
        
    }

    public BalanceSheetDTO(AccountType category, List<AccountDTO> account, double total) {
        this.category = category;
        this.account = account;
        this.total = total;
    }

    public AccountType getCategory() {
        return category;
    }

    public void setCategory(AccountType category) {
        this.category = category;
    }

    public List<AccountDTO> getAccount() {
        return account;
    }

    public void setAccount(List<AccountDTO> account) {
        this.account = account;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BalanceSheetDTO{" + "category=" + category + ", account=" + account + ", total=" + total + '}';
    }
    
}
