
package com.corebits.ericsson.tms.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author xtphere
 */
@Entity
@Table(name = "loan_application")
@NamedQueries({
    @NamedQuery(name = "LoanApplication.findAll", query = "SELECT l FROM LoanApplication l"),
    @NamedQuery(name = "LoanApplication.findById", query = "SELECT l FROM LoanApplication l WHERE l.id = :id"),
    @NamedQuery(name = "LoanApplication.findByMemberId", query = "SELECT l FROM LoanApplication l WHERE l.memberId = :memberId"),    
    @NamedQuery(name = "LoanApplication.findByLoanAmount", query = "SELECT l FROM LoanApplication l WHERE l.loanAmount = :loanAmount"),
    @NamedQuery(name = "LoanApplication.findByAnnualInterestRate", query = "SELECT l FROM LoanApplication l WHERE l.annualInterestRate = :annualInterestRate"),
    @NamedQuery(name = "LoanApplication.findByLoanStartDate", query = "SELECT l FROM LoanApplication l WHERE l.loanStartDate = :loanStartDate"),
    @NamedQuery(name = "LoanApplication.findByMonthlyPaymentAmount", query = "SELECT l FROM LoanApplication l WHERE l.monthlyPaymentAmount = :monthlyPaymentAmount"),
    @NamedQuery(name = "LoanApplication.findByNumberOfPayment", query = "SELECT l FROM LoanApplication l WHERE l.numberOfPayment = :numberOfPayment"),
    @NamedQuery(name = "LoanApplication.findByTotalInterest", query = "SELECT l FROM LoanApplication l WHERE l.totalInterest = :totalInterest"),
    @NamedQuery(name = "LoanApplication.findByTotalCostOfLoan", query = "SELECT l FROM LoanApplication l WHERE l.totalCostOfLoan = :totalCostOfLoan"),    
    @NamedQuery(name = "LoanApplication.findByDateOfApplication", query = "SELECT l FROM LoanApplication l WHERE l.dateOfApplication = :dateOfApplication"),
    @NamedQuery(name = "LoanApplication.findByApprovedBy", query = "SELECT l FROM LoanApplication l WHERE l.approvedBy = :approvedBy"),
    @NamedQuery(name = "LoanApplication.findByDateOfApproval", query = "SELECT l FROM LoanApplication l WHERE l.dateOfApproval = :dateOfApproval")})
public class LoanApplication implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "loan_amount")
    private double loanAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "annual_interest_rate")
    private double annualInterestRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "loan_start_date")
    @Temporal(TemporalType.DATE)
    private Date loanStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monthly_payment_amount")
    private double monthlyPaymentAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_payment")
    private int numberOfPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_interest")
    private double totalInterest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_cost_of_loan")
    private double totalCostOfLoan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_application")
    @Temporal(TemporalType.DATE)
    private Date dateOfApplication;
    @Size(max = 45)
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "date_of_approval")
    @Temporal(TemporalType.TIME)
    private Date dateOfApproval;  
    @Column(name = "approval_status")
    private Integer approvalStatus;
    @Column(name = "loan_status")
    private Integer loanStatus;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private StaffMember memberId;

    public LoanApplication() {
    }

    public LoanApplication(Integer id) {
        this.id = id;
    }

    public LoanApplication(Integer id, StaffMember memberId, double loanAmount, double annualInterestRate, Date loanStartDate, double monthlyPaymentAmount, int numberOfPayment, double totalInterest, double totalCostOfLoan, double monthlyPrincipal, Date dateOfApplication) {
        this.id = id;
        this.memberId = memberId;
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.loanStartDate = loanStartDate;
        this.monthlyPaymentAmount = monthlyPaymentAmount;
        this.numberOfPayment = numberOfPayment;
        this.totalInterest = totalInterest;
        this.totalCostOfLoan = totalCostOfLoan;
        this.dateOfApplication = dateOfApplication;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(double monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public int getNumberOfPayment() {
        return numberOfPayment;
    }

    public void setNumberOfPayment(int numberOfPayment) {
        this.numberOfPayment = numberOfPayment;
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

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getDateOfApproval() {
        return dateOfApproval;
    }

    public void setDateOfApproval(Date dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanApplication)) {
            return false;
        }
        LoanApplication other = (LoanApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
   
    

    public StaffMember getMemberId() {
        return memberId;
    }

    public void setMemberId(StaffMember memberId) {
        this.memberId = memberId;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "LoanApplication{" + "id=" + id + ", loanAmount=" + loanAmount + ", annualInterestRate=" + annualInterestRate + ", loanStartDate=" + loanStartDate + ", monthlyPaymentAmount=" + monthlyPaymentAmount + ", numberOfPayment=" + numberOfPayment + ", totalInterest=" + totalInterest + ", totalCostOfLoan=" + totalCostOfLoan + ", dateOfApplication=" + dateOfApplication + ", approvedBy=" + approvedBy + ", dateOfApproval=" + dateOfApproval + ", approvalStatus=" + approvalStatus + ", loanStatus=" + loanStatus + ", memberId=" + memberId + '}';
    }
    
    
    
}
