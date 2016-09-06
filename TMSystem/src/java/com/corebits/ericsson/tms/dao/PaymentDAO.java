
package com.corebits.ericsson.tms.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author xtphere
 */
public class PaymentDAO  implements Serializable{
    private List<RepaymentEntryDAO> repaymentEntry;
    private double totalInterest;
    private double totalCostOfLoan;
    private double loanAmount;
    private double annualInterestRate;
    private Date loanStartDate;
    private double monthlyPayment;
    private int numberOfPayment;
    
    public PaymentDAO(){
        repaymentEntry = new ArrayList<>();
        loanStartDate = new Date();
    }

    public PaymentDAO(List<RepaymentEntryDAO> repaymentEntry, double totalInterest, double totalCostOfLoan) {
        this.repaymentEntry = repaymentEntry;
        this.totalInterest = totalInterest;
        this.totalCostOfLoan = totalCostOfLoan;
    }

    public PaymentDAO(List<RepaymentEntryDAO> repaymentEntry, double totalInterest, double totalCostOfLoan, 
            double loanAmount, double annualInterestRate, Date loanStartDate, double monthlyPayment, 
            int numberOfPayment) {
        this.repaymentEntry = repaymentEntry;
        this.totalInterest = totalInterest;
        this.totalCostOfLoan = totalCostOfLoan;
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanStartDate = loanStartDate;
        this.monthlyPayment = monthlyPayment;
        this.numberOfPayment = numberOfPayment;
    }

    public List<RepaymentEntryDAO> getRepaymentEntry() {
        return repaymentEntry;
    }

    public void setRepaymentEntry(List<RepaymentEntryDAO> repaymentEntry) {
        this.repaymentEntry = repaymentEntry;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public double getTotalCostOfLoan() {
        return totalCostOfLoan;
    }

    public void setTotalCostOfLoan(double totalCostOfLoan) {
        this.totalCostOfLoan = totalCostOfLoan;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getNumberOfPayment() {
        return numberOfPayment;
    }

    public void setNumberOfPayment(int numberOfPayment) {
        this.numberOfPayment = numberOfPayment;
    }

    @Override
    public String toString() {
        return "PaymentDAO{" + "repaymentEntry=" + repaymentEntry + ", totalInterest=" + totalInterest + ", totalCostOfLoan=" + totalCostOfLoan + ", loanAmount=" + loanAmount + ", annualInterestRate=" + annualInterestRate + ", loanStartDate=" + loanStartDate + ", monthlyPayment=" + monthlyPayment + ", numberOfPayment=" + numberOfPayment + '}';
    }
    
    
    
}
