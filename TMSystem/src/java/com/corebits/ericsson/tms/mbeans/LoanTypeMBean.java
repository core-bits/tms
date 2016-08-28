
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanTypeController;
import com.corebits.ericsson.tms.models.LoanType;
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
    
    public LoanTypeMBean(){
        super(LoanType.class);
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(ejbFacade);
    }
    
    
}
