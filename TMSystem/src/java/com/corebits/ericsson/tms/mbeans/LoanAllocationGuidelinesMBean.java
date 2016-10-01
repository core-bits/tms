
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanAllocationGuidelinesController;
import com.corebits.ericsson.tms.models.LoanAllocationGuidelines;
import com.corebits.ericsson.tms.models.LoanType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author xtphere
 */
//@Named(value="loanAllocationGuidelinesMBean")
//@RequestScoped
@ManagedBean(name="loanAllocationGuidelinesMBean")
@ViewScoped
public class LoanAllocationGuidelinesMBean extends AbstractMBean<LoanAllocationGuidelines> implements Serializable{
    @EJB
    LoanAllocationGuidelinesController ejbFacade;
    private LoanAllocationGuidelines loanAllocationGuideline;
    
    public LoanAllocationGuidelinesMBean(){
        super(LoanAllocationGuidelines.class);
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(ejbFacade);
    }
    
    public double getLoanTypeInterestRate(LoanType loanType, double amount, int tenure){
        LoanAllocationGuidelines loanSubType = getLoanSubType(loanType, amount, tenure);        
        if(loanSubType != null)
            return loanSubType.getInterestRate();
        return 0;
    }
    
    public LoanAllocationGuidelines getLoanSubType(LoanType loanType, double amount, int tenure){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("loanType", loanType);
        List<LoanAllocationGuidelines> list = ejbFacade.findWithNamedQuery(LoanAllocationGuidelinesController.NAMED_QUERY_FIND_BY_LOAN_TYPE, parameter);
//        System.out.println("list: " + list.size() + ", loanType: " + loanType + ", amount: " + amount + ", tenure: " + tenure);
        for (LoanAllocationGuidelines row : list) {
//            System.out.println("row: " + row);
            if((amount >= row.getMinimumAmount() && amount <= row.getMaximumAmount()) &&
                    tenure >= row.getMinimumTenure() && tenure <= row.getMaximumTenure()){
                return row;                
            }
        }
        return null;
    }
    
    public List<LoanAllocationGuidelines> getLoanAllocationGuidlelineList(){
        return ejbFacade.findAll();
    }
    
    public void createNewLoanAllocationGuideline(){
        ejbFacade.create(loanAllocationGuideline);
    }
    
    public void updateLoanAllocationGuideline(){
        ejbFacade.edit(loanAllocationGuideline);
    }

    public LoanAllocationGuidelines getLoanAllocationGuideline() {
        return loanAllocationGuideline;
    }

    public void setLoanAllocationGuideline(LoanAllocationGuidelines loanAllocationGuideline) {
        this.loanAllocationGuideline = loanAllocationGuideline;
    }
    
    
}
