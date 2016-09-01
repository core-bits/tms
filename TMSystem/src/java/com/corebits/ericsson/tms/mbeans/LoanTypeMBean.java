
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanTypeController;
import com.corebits.ericsson.tms.models.LoanType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author xtphere
 */
//
@ManagedBean(name="loanTypeMBean")
@ViewScoped
public class LoanTypeMBean extends AbstractMBean<LoanType> {
    @EJB
    LoanTypeController ejbFacade;
    private LoanType loanType;
    
    public LoanTypeMBean(){
        super(LoanType.class);
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(ejbFacade);
    }
    
    public List<LoanType> getLoanTypeList(){        
        return ejbFacade.findAll();
    }
    
    public void createNewLoanType(){
        ejbFacade.create(loanType);
    }
    
    public void updateLoanType(){
        ejbFacade.edit(loanType);
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
    
    
}
