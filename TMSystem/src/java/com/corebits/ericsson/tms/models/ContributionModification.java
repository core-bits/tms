
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
import javax.validation.constraints.Size;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "contribution_modification")
@NamedQueries({
    @NamedQuery(name = "ContributionModification.findAll", query = "SELECT c FROM ContributionModification c"),
    @NamedQuery(name = "ContributionModification.findById", query = "SELECT c FROM ContributionModification c WHERE c.id = :id"),
    @NamedQuery(name = "ContributionModification.findByIncreaseDecreaseAmount", query = "SELECT c FROM ContributionModification c WHERE c.increaseDecreaseAmount = :increaseDecreaseAmount"),
    @NamedQuery(name = "ContributionModification.findByCommencementDate", query = "SELECT c FROM ContributionModification c WHERE c.commencementDate = :commencementDate"),
    @NamedQuery(name = "ContributionModification.findByTotalSavingsMonthly", query = "SELECT c FROM ContributionModification c WHERE c.totalSavingsMonthly = :totalSavingsMonthly"),
    @NamedQuery(name = "ContributionModification.findByIncreaseDecrease", query = "SELECT c FROM ContributionModification c WHERE c.increaseDecrease = :increaseDecrease"),
    @NamedQuery(name = "ContributionModification.findByStatus", query = "SELECT c FROM ContributionModification c WHERE c.applicationStatus = :applicationStatus ORDER BY c.id DESC"),
    @NamedQuery(name = "ContributionModification.findByApplicationDate", query = "SELECT c FROM ContributionModification c WHERE c.applicationDate = :applicationDate")})
public class ContributionModification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "increase_decrease_amount")
    private BigDecimal increaseDecreaseAmount;
    @Column(name = "commencement_date")
    @Temporal(TemporalType.DATE)
    private Date commencementDate;
    @Column(name = "total_savings_monthly")
    private BigDecimal totalSavingsMonthly;
    @Size(max = 45)
    @Column(name = "increase_decrease")
    private String increaseDecrease;
    @Column(name = "application_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;    
    @Column(name = "application_status")
    private Short applicationStatus;
    @Column(name = "approval_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalDate;
    @Size(max = 75)
    @Column(name = "approve_by")
    private String approveBy;
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @ManyToOne
    private StaffMember memberId;

    public ContributionModification() {
    }

    public ContributionModification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getIncreaseDecreaseAmount() {
        return increaseDecreaseAmount;
    }

    public void setIncreaseDecreaseAmount(BigDecimal increaseDecreaseAmount) {
        this.increaseDecreaseAmount = increaseDecreaseAmount;
    }

    public Date getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        this.commencementDate = commencementDate;
    }

    public BigDecimal getTotalSavingsMonthly() {
        return totalSavingsMonthly;
    }

    public void setTotalSavingsMonthly(BigDecimal totalSavingsMonthly) {
        this.totalSavingsMonthly = totalSavingsMonthly;
    }

    public String getIncreaseDecrease() {
        return increaseDecrease;
    }

    public void setIncreaseDecrease(String increaseDecrease) {
        this.increaseDecrease = increaseDecrease;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public StaffMember getMemberId() {
        return memberId;
    }

    public void setMemberId(StaffMember memberId) {
        this.memberId = memberId;
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
        if (!(object instanceof ContributionModification)) {
            return false;
        }
        ContributionModification other = (ContributionModification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corebits.ericsson.tms.models.ContributionModification[ id=" + id + " ]";
    }
    
    public Short getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Short applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }
}
