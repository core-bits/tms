
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanApplicationController;
import com.corebits.ericsson.tms.controllers.RegistrationController;
import com.corebits.ericsson.tms.dao.PaymentDAO;
import com.corebits.ericsson.tms.dao.RepaymentEntryDAO;
import com.corebits.ericsson.tms.mbeans.util.JsfUtil;
import com.corebits.ericsson.tms.models.LoanApplication;
import com.corebits.ericsson.tms.models.LoanType;
import com.corebits.ericsson.tms.models.StaffMember;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author xtphere
 */
//@Named(value="loanApplicationMBean")
//@RequestScoped
@ManagedBean(name="loanApplicationMBean")
@ViewScoped
public class LoanApplicationMBean extends AbstractMBean<LoanApplication> implements Serializable{    
    @EJB
    LoanApplicationController loanApplicationFacade;
    @EJB
    RegistrationController registrationFacade;
    private int loanPeriodInYears;
    private double loanAmount;
    private double annualInterestRate;
    private Date loanStartDate;
    private int numberOfPayment;
    private PaymentDAO payment;
    private double maxLoanAmount;
    private int maxTenure;
    private List<LoanType> loanTypeList;
    private LoanType loanType;
    @ManagedProperty(value="#{loanTypeMBean}")
    private LoanTypeMBean loanTypeMBean;
    @ManagedProperty(value="#{loanAllocationGuidelinesMBean}")
    private LoanAllocationGuidelinesMBean loanAllocationGuidelinesMBean;
    private Date startDateControl;
    
    
    public LoanApplicationMBean(){        
        super(LoanApplication.class);
        System.out.println("constructor");  
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(loanApplicationFacade);
        loanTypeList = loanTypeMBean.getLoanTypeList();
        maxLoanAmount = 0;
        maxTenure = 0;
        startDateControl = new Date();
        payment = repaymentEntries();
    }
    
    public void onChangeLoanType(AjaxBehaviorEvent event){
        maxLoanAmount = loanType.getMaximumAmount();
        maxTenure = loanType.getMaximumTenure();
    }
    
    public void tenureInputControl(AjaxBehaviorEvent event){
        System.out.println("tenureInputControl: numberOfPayment=" + numberOfPayment);
        if(maxTenure < numberOfPayment){
            this.numberOfPayment = maxTenure;
            JsfUtil.addErrorMessage("Allowed maximum tenure for \"" + loanType.getLoanDescription() + "\" is " + "\"" + maxTenure + "\"");
            System.out.println("message: Allowed maximum tenure for \"" + loanType .getLoanDescription()+ "\" is " + "\"" + maxTenure + "\"");            
        }
        payment = repaymentEntries();
    }
    
    public void loanAmountInputControl(AjaxBehaviorEvent event){
        System.out.println("loanAmountInputControl: loanAmount=" + loanAmount);
        if(maxLoanAmount < loanAmount){
            loanAmount = 0;
            JsfUtil.addErrorMessage("Allowed maximum amount for \"" + loanType + "\" is " + "\"" + maxLoanAmount + "\"");
        }
        payment = repaymentEntries(); 
    }
    
    public void startDateInputControl(AjaxBehaviorEvent event){
        System.out.println("startDateInputControl: loanStartDate=" + loanStartDate);
        payment = repaymentEntries(); 
    }
    
    public String apply(){
        StaffMember member = getMemberId();
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setMemberId(member);
        loanApplication.setAnnualInterestRate(payment.getAnnualInterestRate());
        loanApplication.setDateOfApplication(new Date());
        loanApplication.setLoanAmount(payment.getLoanAmount());
        loanApplication.setLoanStartDate(payment.getLoanStartDate());
        loanApplication.setMonthlyPaymentAmount(payment.getMonthlyPayment());
        loanApplication.setNumberOfPayment(payment.getNumberOfPayment());
        loanApplication.setTotalCostOfLoan(payment.getTotalCostOfLoan());
        loanApplication.setTotalInterest(payment.getTotalInterest());
        loanApplicationFacade.create(loanApplication);
        
        return "pretty:loan-application-feedback";
    }
    
    public List<LoanApplication> getMemberLoanApplicationList(){
        Map<String, StaffMember> parameter = new HashMap<>();
        StaffMember member = getMemberId();
        parameter.put("memberId", member);
        
        return loanApplicationFacade.findWithNamedQuery(LoanApplicationController.NAMED_QUERY_FIND_MEMBER_LOAN_APPLICATION, 
                parameter);
    }
    
    private StaffMember getMemberId(){
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String memId = (String) params.get("loginId");
        System.out.println("member: " + memId);        
        return registrationFacade.getUserByLoginId(memId).getMemberId();        
    }    
    
    private PaymentDAO repaymentEntries(){
        annualInterestRate = loanAllocationGuidelinesMBean.getLoanTypeInterestRate(loanType, loanAmount, numberOfPayment);
        System.out.println("repaymentEntries: loanAmount: " + loanAmount +", annualInterestRate: " + annualInterestRate + ", loanStartDate: "
                + loanStartDate + ", numberOfPayment: " + numberOfPayment);
        if(loanAmount == 0 || annualInterestRate == 0 || loanStartDate == null || numberOfPayment < 1)
            return new PaymentDAO();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder();
        RepaymentEntryDAO entry;
        double mBeginningBal = loanAmount;
        double mPayment;
        double mInterest;
        double mEndingBal;
        double mPrincipal;
        double totInterest = 0;
        double totCostOfLoan;
        mPayment = calculateMPayment();
        List<RepaymentEntryDAO> entryList;
        entryList = new ArrayList<>();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(loanStartDate);
        for(int i = 1; i <= numberOfPayment; i++){
            mInterest = calculateMInterest(mBeginningBal);
            totInterest = totInterest + mInterest;
            mPrincipal = mPayment - mInterest; //mPayment.subtract(mInterest);
            mEndingBal = mBeginningBal - mPrincipal;
            cal.add(Calendar.MONTH, 1);
            entry = new RepaymentEntryDAO(sdf.format(cal.getTime()), mBeginningBal, mPayment, mPrincipal, mInterest, mEndingBal);
            entryList.add(entry); 
            builder.append(sdf.format(cal.getTime()))
                    .append("\t")
                    .append(mBeginningBal)
                    .append("\t")
                    .append(mPayment)
                    .append("\t")
                    .append(mPrincipal)
                    .append("\t")
                    .append(mInterest)
                    .append("\t")
                    .append(mEndingBal)
                    .append("\n");
            mBeginningBal = mEndingBal;
        }
        
        totCostOfLoan = loanAmount + totInterest;
        PaymentDAO entries = new PaymentDAO(entryList, totInterest, totCostOfLoan, loanAmount, annualInterestRate, 
        loanStartDate, mPayment, numberOfPayment); 
        
        return entries;
    }
    
    private double calculateMPayment(){
        //montly payment (Equated Monthly Payment - EMI) = P * r(1+r)^n/((1+r)^n - 1)
        //where P = principal, r = annual interest rate/100 (divided by 12 in case of monthly repayment)
        //n = number of repayments
        double rate = computeInterestRateCompounded();
        double onePlusRateRaisedToN = Math.pow((rate + 1), numberOfPayment);//BigDecimal.ONE.add(rate).pow(numberOfPayment);
        
        //numerator portion of formula
        //loanAmount.multiply(rate).multiply(onePlusRateRaisedToN);
        double numerator = loanAmount * rate * onePlusRateRaisedToN;
        
        //Denomination portion of formula
        double denominator = onePlusRateRaisedToN - 1;//onePlusRateRaisedToN.subtract(BigDecimal.ONE);
        
        // returns the monthly payment amount        
        return numerator / denominator;//numerator.divide(denominator, 5, BigDecimal.ROUND_CEILING).setScale(2, BigDecimal.ROUND_CEILING);
    }
    
    private double calculateMInterest(double mRemainingBal){   
        return mRemainingBal * computeInterestRateCompounded();
//        return mRemainingBal
//                .multiply(computeInterestRateCompounded())
//                .setScale(2, BigDecimal.ROUND_CEILING); 
    }
    
    private double computeInterestRateCompounded(){
        return (annualInterestRate / 100 / 12);
//        return annualInterestRate
//                .divide(new BigDecimal(100), 5, BigDecimal.ROUND_CEILING)
//                .divide(new BigDecimal(12), 5, BigDecimal.ROUND_CEILING)
//                .setScale(5, BigDecimal.ROUND_CEILING); 
    }

    public int getLoanPeriodInYears() {
        return loanPeriodInYears;
    }

    public void setLoanPeriodInYears(int loanPeriodInYears) {
        this.loanPeriodInYears = loanPeriodInYears;
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

    public int getNumberOfPayment() {
        return numberOfPayment;
    }

    public void setNumberOfPayment(int numberOfPayment) {
        this.numberOfPayment = numberOfPayment;
    }
    
    public static void main(String[] args){
        
        new LoanApplicationMBean().repaymentEntries();
    }

    public PaymentDAO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDAO payment) {
        this.payment = payment;
    }

    public List<LoanType> getLoanTypeList() {
        return loanTypeList;
    }

    public void setLoanTypeList(List<LoanType> loanTypeList) {
        this.loanTypeList = loanTypeList;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public LoanTypeMBean getLoanTypeMBean() {
        return loanTypeMBean;
    }

    public void setLoanTypeMBean(LoanTypeMBean loanTypeMBean) {
        this.loanTypeMBean = loanTypeMBean;
    }

    public double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public int getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(int maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Date getStartDateControl() {
        return startDateControl;
    }

    public void setStartDateControl(Date startDateControl) {
        this.startDateControl = startDateControl;
    }

    public LoanAllocationGuidelinesMBean getLoanAllocationGuidelinesMBean() {
        return loanAllocationGuidelinesMBean;
    }

    public void setLoanAllocationGuidelinesMBean(LoanAllocationGuidelinesMBean loanAllocationGuidelinesMBean) {
        this.loanAllocationGuidelinesMBean = loanAllocationGuidelinesMBean;
    }
    
    public BigDecimal getMaxLoanFormatted(){
        return new BigDecimal(maxLoanAmount);
    }
    
}
