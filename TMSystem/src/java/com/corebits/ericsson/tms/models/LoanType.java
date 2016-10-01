
package com.corebits.ericsson.tms.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "loan_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanType.findAll", query = "SELECT l FROM LoanType l"),
    @NamedQuery(name = "LoanType.findById", query = "SELECT l FROM LoanType l WHERE l.id = :id"),
    @NamedQuery(name = "LoanType.findByLoanName", query = "SELECT l FROM LoanType l WHERE l.loanName = :loanName"),
    @NamedQuery(name = "LoanType.findByLoanDescription", query = "SELECT l FROM LoanType l WHERE l.loanDescription = :loanDescription")})
public class LoanType implements Serializable {    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "loan_name")
    private String loanName;
    @Size(max = 200)
    @Column(name = "loan_description")
    private String loanDescription;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "maximum_amount")
    private double maximumAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maximum_tenure")
    private int maximumTenure;
    @OneToMany(mappedBy = "loanType")
    private List<LoanAllocationGuidelines> loanAllocationGuidelinesList;

    public LoanType() {
    }

    public LoanType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    @XmlTransient
    public List<LoanAllocationGuidelines> getLoanAllocationGuidelinesList() {
        return loanAllocationGuidelinesList;
    }

    public void setLoanAllocationGuidelinesList(List<LoanAllocationGuidelines> loanAllocationGuidelinesList) {
        this.loanAllocationGuidelinesList = loanAllocationGuidelinesList;
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
        if (!(object instanceof LoanType)) {
            return false;
        }
        LoanType other = (LoanType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corebits.ericsson.models.LoanType[ id=" + id + " ]";
    }

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public int getMaximumTenure() {
        return maximumTenure;
    }

    public void setMaximumTenure(int maximumTenure) {
        this.maximumTenure = maximumTenure;
    }
    
}
