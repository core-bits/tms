
package com.corebits.ericsson.tms.models;

import java.io.Serializable;
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
@Table(name = "loan_repayment")
@NamedQueries({
    @NamedQuery(name = "LoanRepayment.findAll", query = "SELECT l FROM LoanRepayment l"),
    @NamedQuery(name = "LoanRepayment.findById", query = "SELECT l FROM LoanRepayment l WHERE l.id = :id"),
    @NamedQuery(name = "LoanRepayment.findByMemberId", query = "SELECT l FROM LoanRepayment l WHERE l.memberId = :memberId"),
    @NamedQuery(name = "LoanRepayment.findByRepaymentAmount", query = "SELECT l FROM LoanRepayment l WHERE l.repaymentAmount = :repaymentAmount"),
    @NamedQuery(name = "LoanRepayment.findByDateOfPayment", query = "SELECT l FROM LoanRepayment l WHERE l.dateOfPayment = :dateOfPayment"),
    @NamedQuery(name = "LoanRepayment.findByDescription", query = "SELECT l FROM LoanRepayment l WHERE l.description = :description"),
    @NamedQuery(name = "LoanRepayment.findByLoanId", query = "SELECT l FROM LoanRepayment l WHERE l.loanId = :loanId"),
    @NamedQuery(name = "LoanRepayment.findByLoanIdNStatus", query = "SELECT l FROM LoanRepayment l WHERE l.loanId = :loanId AND l.repaymentStatus = :repaymentStatus"),
    @NamedQuery(name = "LoanRepayment.findByRepaymentPeriod", query = "SELECT l FROM LoanRepayment l WHERE l.repaymentPeriod = :repaymentPeriod")})
public class LoanRepayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "member_id")
    private String memberId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "repayment_amount")
    private double repaymentAmount;
    @Column(name = "amount_received")
    private double amountReceived;
    @Column(name = "date_of_payment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPayment;
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "repayment_period")
    @Temporal(TemporalType.DATE)
    private Date repaymentPeriod;
    @Size(min = 1, max = 45)
    @Column(name = "transaction_id")
    private String transactionId;
    @Size(min = 1, max = 45)
    @Column(name = "repayment_status")
    private String repaymentStatus;
    @JoinColumn(name = "loan_id", referencedColumnName = "loan_id")
    @ManyToOne//(optional = false)
    private LoanApplication loanId;

    public LoanRepayment() {
    }

    public LoanRepayment(String memberId, double repaymentAmount, Date dateOfPayment, String description, Date repaymentPeriod) {
        this.memberId = memberId;
        this.repaymentAmount = repaymentAmount;
        this.dateOfPayment = dateOfPayment;
        this.description = description;
        this.repaymentPeriod = repaymentPeriod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(Date repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public LoanApplication getLoanId() {
        return loanId;
    }

    public void setLoanId(LoanApplication loanId) {
        this.loanId = loanId;
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
        if (!(object instanceof LoanRepayment)) {
            return false;
        }
        LoanRepayment other = (LoanRepayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    @Override
    public String toString() {
        return "LoanRepayment{" + "id=" + id + ", memberId=" + memberId + ", repaymentAmount=" + repaymentAmount + ", amountReceived=" + amountReceived + ", dateOfPayment=" + dateOfPayment + ", description=" + description + ", repaymentPeriod=" + repaymentPeriod + ", transactionId=" + transactionId + ", repaymentStatus=" + repaymentStatus + ", loanId=" + loanId + '}';
    }
    
    
    
}
