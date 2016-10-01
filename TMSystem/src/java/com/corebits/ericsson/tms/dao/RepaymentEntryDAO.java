
package com.corebits.ericsson.tms.dao;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xtphere
 */
public class RepaymentEntryDAO  implements Serializable{
    private Date paymentDate;
    private double beginingBalance;
    private double monthlyPayment;
    private double principal;
    private double interest;
    private double endinBalance;   
    
    public RepaymentEntryDAO(){        
    }

    public RepaymentEntryDAO(Date paymentDate, double beginingBalance, double monthlyPayment, double principal, 
            double interest, double endinBalance) {
        this.paymentDate = paymentDate;
        this.beginingBalance = beginingBalance;
        this.monthlyPayment = monthlyPayment;
        this.principal = principal;
        this.interest = interest;
        this.endinBalance = endinBalance;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getBeginingBalance() {
        return beginingBalance;
    }

    public void setBeginingBalance(double beginingBalance) {
        this.beginingBalance = beginingBalance;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getEndinBalance() {
        return endinBalance;
    }

    public void setEndinBalance(double endinBalance) {
        this.endinBalance = endinBalance;
    }

    @Override
    public String toString() {
        return "RepaymentEntryDAO{" + "paymentDate=" + paymentDate + ", beginingBalance=" + beginingBalance + ", monthlyPayment=" + monthlyPayment + ", principal=" + principal + ", interest=" + interest + ", endinBalance=" + endinBalance + '}';
    }
    
}
