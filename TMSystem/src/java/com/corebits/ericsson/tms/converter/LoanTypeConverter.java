
package com.corebits.ericsson.tms.converter;

import com.corebits.ericsson.tms.controllers.LoanTypeController;
import com.corebits.ericsson.tms.mbeans.util.JsfUtil;
import com.corebits.ericsson.tms.models.LoanType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.omnifaces.util.Messages;

import org.primefaces.component.menu.Menu;

/**
 *
 * @author xtphere
 */
@FacesConverter("loanTypeConverter")
public class LoanTypeConverter implements Converter{
    @Inject
    private LoanTypeController ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        System.out.println("value: " + value);
        System.out.println("key: " + getKey(value) + ", facade: " + ejbFacade);
        System.out.println("found: " + this.ejbFacade.find(getKey(value)));
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof LoanType) {
            LoanType o = (LoanType) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Menu.class.getName()});
            return null;
        }
    }
}
