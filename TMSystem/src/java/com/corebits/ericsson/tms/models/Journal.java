
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
import javax.validation.constraints.Size;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "journal")
@NamedQueries({
    @NamedQuery(name = "Journal.findAll", query = "SELECT j FROM Journal j ORDER BY j.id DESC"),
    @NamedQuery(name = "Journal.findById", query = "SELECT j FROM Journal j WHERE j.id = :id"),
    @NamedQuery(name = "Journal.findByDate", query = "SELECT j FROM Journal j WHERE j.date = :date"),
    @NamedQuery(name = "Journal.findByMonth", query = "SELECT j FROM Journal j WHERE j.month = :month"),
    @NamedQuery(name = "Journal.findByYear", query = "SELECT j FROM Journal j WHERE j.year = :year"),
    @NamedQuery(name = "Journal.findByCredit", query = "SELECT j FROM Journal j WHERE j.credit = :credit"),
    @NamedQuery(name = "Journal.findByDebit", query = "SELECT j FROM Journal j WHERE j.debit = :debit"),
    @NamedQuery(name = "Journal.findByAmount", query = "SELECT j FROM Journal j WHERE j.amount = :amount"),
    @NamedQuery(name = "Journal.findByNote", query = "SELECT j FROM Journal j WHERE j.note = :note"),
    @NamedQuery(name = "Journal.findByAccount", query = "SELECT j FROM Journal j WHERE j.account = :account")})
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "date")
    private String date;
    @Size(max = 15)
    @Column(name = "month")
    private String month;
    @Size(max = 15)
    @Column(name = "year")
    private String year;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "credit")
    private Double credit;
    @Column(name = "debit")
    private Double debit;
    @Column(name = "amount")
    private Double amount;
    @Size(max = 45)
    @Column(name = "note")
    private String note;
    @Column(name = "actual_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualDate;
    @JoinColumn(name = "account", referencedColumnName = "id")
    @ManyToOne
    private Accounts account;
    
    public Journal() {
    }

    public Journal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        if (!(object instanceof Journal)) {
            return false;
        }
        Journal other = (Journal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corebits.ericsson.tms.models.Journal[ id=" + id + " ]";
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
    
}
