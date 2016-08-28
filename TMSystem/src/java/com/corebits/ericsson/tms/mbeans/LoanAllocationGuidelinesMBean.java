
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanAllocationGuidelinesController;
import com.corebits.ericsson.tms.models.LoanAllocationGuidelines;
import com.corebits.ericsson.tms.models.LoanType;
import java.math.BigDecimal;
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
public class LoanAllocationGuidelinesMBean extends AbstractMBean<LoanAllocationGuidelines>{
    @EJB
    LoanAllocationGuidelinesController ejbFacade;
    
    public LoanAllocationGuidelinesMBean(){
        super(LoanAllocationGuidelines.class);
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(ejbFacade);
    }
    
    public BigDecimal getLoanTypeInterestRate(LoanType loanType, BigDecimal amount, int tenure){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("loanType", loanType);
        List<LoanAllocationGuidelines> list = ejbFacade.findWithNamedQuery(LoanAllocationGuidelinesController.NAMED_QUERY_FIND_BY_LOAN_TYPE, parameter);
        System.out.println("list: " + list.size() + "loanType: " + loanType + ", amount: " + amount + ", tenure: " + tenure);
        for (LoanAllocationGuidelines row : list) {
            System.out.println("row: " + row);
            if((amount != null && amount.doubleValue() >= row.getMinimumAmount().doubleValue() && 
                    amount.doubleValue() <= row.getMaximumAmount().doubleValue()) &&
                    tenure >= row.getMinimumTenure() && tenure <= row.getMaximumTenure()){
                return row.getInterestRate();                
            }
        }
        return BigDecimal.ZERO;
    }
}