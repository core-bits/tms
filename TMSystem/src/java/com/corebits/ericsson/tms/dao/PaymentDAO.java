
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
    private BigDecimal totalInterest;
    private BigDecimal totalCostOfLoan;
    private BigDecimal loanAmount;
    private BigDecimal annualInterestRate;
    private Date loanStartDate;
    private BigDecimal monthlyPayment;
    private int numberOfPayment;
    
    public PaymentDAO(){
        repaymentEntry = new ArrayList<>();
        totalInterest = BigDecimal.ZERO;
        totalCostOfLoan = BigDecimal.ZERO;
        loanAmount = BigDecimal.ZERO;
        annualInterestRate = BigDecimal.ZERO;
        loanStartDate = new Date();
        monthlyPayment = BigDecimal.ZERO;
    }

    public PaymentDAO(List<RepaymentEntryDAO> repaymentEntry, BigDecimal totalInterest, BigDecimal totalCostOfLoan) {
        this.repaymentEntry = repaymentEntry;
        this.totalInterest = totalInterest;
        this.totalCostOfLoan = totalCostOfLoan;
    }

    public PaymentDAO(List<RepaymentEntryDAO> repaymentEntry, BigDecimal totalInterest, BigDecimal totalCostOfLoan, 
            BigDecimal loanAmount, BigDecimal annualInterestRate, Date loanStartDate, BigDecimal monthlyPayment, 
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

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalCostOfLoan() {
        return totalCostOfLoan;
    }

    public void setTotalCostOfLoan(BigDecimal totalCostOfLoan) {
        this.totalCostOfLoan = totalCostOfLoan;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
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
