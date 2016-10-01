
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanApplicationController;
import com.corebits.ericsson.tms.controllers.LoanRepaymentController;
import com.corebits.ericsson.tms.controllers.RegistrationController;
import com.corebits.ericsson.tms.dao.PaymentDAO;
import com.corebits.ericsson.tms.dao.RepaymentEntryDAO;
import com.corebits.ericsson.tms.mbeans.util.JsfUtil;
import com.corebits.ericsson.tms.models.LoanAllocationGuidelines;
import com.corebits.ericsson.tms.models.LoanApplication;
import com.corebits.ericsson.tms.models.LoanRepayment;
import com.corebits.ericsson.tms.models.LoanType;
import com.corebits.ericsson.tms.models.StaffMember;
import com.corebits.ericsson.tms.utils.ApprovalStatusType;
import com.corebits.ericsson.tms.utils.LoanRepaymentStatusType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
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
    @EJB
    LoanRepaymentController repaymentFacade;
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
    @ManagedProperty(value="#{loanRepaymentMBean}")
    private LoanRepaymentMBean loanRepaymentMBean;
    private Date startDateControl;
    private LoanApplication selectedLoanApplication;
    private List<LoanRepayment> outstandingLoanPaymentList;
    private String loggedOnMemberId;
    
    
    public LoanApplicationMBean(){        
        super(LoanApplication.class);  
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(loanApplicationFacade);
        loanTypeList = loanTypeMBean.getLoanTypeList();
        maxLoanAmount = 0;
        maxTenure = 0;
        startDateControl = new Date();
        payment = new PaymentDAO();
        selectedLoanApplication = new LoanApplication();
        outstandingLoanPaymentList = new ArrayList<>();
        loggedOnMemberId = this.getMemberId().getMemberId();
    }
    
    public void onChangeLoanType(AjaxBehaviorEvent event){
        maxLoanAmount = loanType.getMaximumAmount();
        maxTenure = loanType.getMaximumTenure();
    }
    
    public boolean canApprove(String status, String memberId){
        System.out.println("canApprove ->>>>> " + (!ApprovalStatusType.APPROVED.getKey().toString().equals(status) && !loggedOnMemberId.equals(memberId)));
        if(Objects.isNull(memberId) || "".equals(memberId))
            return false;       
       
        return (!ApprovalStatusType.APPROVED.getKey().toString().equals(status) && !loggedOnMemberId.equals(memberId));
        //System.out.println("row: " + row + ", size: " + getMemberLoanApplicationList().size() + ", can --->>>>>>> " + can + ", status: " + status + ", memberId: " + memberId);
       
    }
    
    public void tenureInputControl(AjaxBehaviorEvent event){
        //System.out.println("tenureInputControl: numberOfPayment=" + numberOfPayment);
        if(maxTenure < numberOfPayment){
            this.numberOfPayment = maxTenure;
            JsfUtil.addErrorMessage("Allowed maximum tenure for \"" + loanType.getLoanDescription() + "\" is " + "\"" + maxTenure + "\"");
            System.out.println("message: Allowed maximum tenure for \"" + loanType .getLoanDescription()+ "\" is " + "\"" + maxTenure + "\"");            
        }
        payment = repaymentEntries();
    }
    
    public void loanAmountInputControl(AjaxBehaviorEvent event){
//        System.out.println("loanAmountInputControl: loanAmount=" + loanAmount);
        if(maxLoanAmount < loanAmount){
            loanAmount = 0;
            JsfUtil.addErrorMessage("Allowed maximum amount for \"" + loanType + "\" is " + "\"" + maxLoanAmount + "\"");
        }
        payment = repaymentEntries(); 
    }
    
    public void startDateInputControl(AjaxBehaviorEvent event){
        //System.out.println("startDateInputControl: loanStartDate=" + loanStartDate);
        payment = repaymentEntries(); 
        
        if(Objects.nonNull(payment)){
            if(payment.getRepaymentEntry().isEmpty()){
                String message = "We are sorry there is no provision for the supplied values. Please contact \"Accounts Department\"";
                JsfUtil.addErrorMessage(message);
            }
        }
    }
    
    private String generateTransactionId(){
        return UUID.randomUUID().toString().substring(0, 20);
    }
    
    public String apply(){
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();        
        StaffMember member = getMemberId();
        map.put("applicant", member);
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setLoanId(payment.getLoanId());
        loanApplication.setLoanType(payment.getLoanType());
        loanApplication.setLoanSubType(payment.getLoanSubType());
        loanApplication.setMemberId(member);
        loanApplication.setAnnualInterestRate(payment.getAnnualInterestRate());
        loanApplication.setDateOfApplication(new Date());
        loanApplication.setLoanAmount(payment.getLoanAmount());
        loanApplication.setLoanStartDate(payment.getLoanStartDate());
        loanApplication.setMonthlyPaymentAmount(payment.getMonthlyPayment());
        loanApplication.setNumberOfPayment(payment.getNumberOfPayment());
        loanApplication.setTotalCostOfLoan(payment.getTotalCostOfLoan());
        loanApplication.setTotalInterest(payment.getTotalInterest());
        loanApplication.setApprovalStatus(ApprovalStatusType.PENDING.getKey());
        loanApplication.setLoanTypeDesc(payment.getLoanTypeDesc());
        loanApplication.setLoanSubTypeDesc(payment.getLoanSubTypeDesc());
        
        loanApplicationFacade.create(loanApplication);        
        
        return "pretty:loan-application-feedback";
    }
    
    public void approveLoan(LoanApplication loan){
//        System.out.println("loan: " + loan);
        StaffMember member = getMemberId();
        
        loan.setApprovedBy(member.getMemberName());
        loan.setDateOfApproval(new Date());
        loan.setApprovalStatus(1);
        loanApplicationFacade.edit(loan);
        
        //populate the repayment table with the entries        
        PaymentDAO entries = repaymentEntries(loan.getLoanType(), loan.getLoanAmount(), 
                loan.getAnnualInterestRate(), loan.getLoanStartDate(), loan.getNumberOfPayment(), 
                loan.getLoanSubType());
        
        entries.getRepaymentEntry().forEach(entry ->{
            LoanRepayment repayment = new LoanRepayment();
            repayment.setLoanId(loan);
            repayment.setRepaymentAmount(entry.getMonthlyPayment());
            repayment.setMemberId(loan.getMemberId().getMemberId());
            repayment.setRepaymentPeriod(entry.getPaymentDate());
            repayment.setRepaymentStatus(LoanRepaymentStatusType.OUTSTANDING.toString());
            
            repaymentFacade.create(repayment);
        });
        
        //reload 
        getMemberLoanApplicationList();
        
        String message = "Loan Application with Loan ID: \""+ loan.getId() + "\" for Member: \"" + loan.getMemberId().getMemberName() + " (" + loan.getMemberId().getMemberId() + ")\" has been successfully approved";
        
        JsfUtil.addSuccessMessage(message);
    }
    
    public List<LoanApplication> getMemberLoanApplicationList(){
        Map<String, StaffMember> parameter = new HashMap<>();
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        StaffMember applicant = (StaffMember)map.get("applicant");
        StaffMember member = applicant;//getMemberId();
        
        if(Objects.nonNull(member)){
            parameter.put("memberId", member);
            return loanApplicationFacade.findWithNamedQuery(LoanApplicationController.FIND_MEMBER_LOAN_APPLICATION, 
                    parameter);
        }else{
            return loanApplicationFacade.findWithNamedQuery(LoanApplicationController.NAMED_QUERY_FIND_ALL, 
                parameter);
        }
    }
    
    private StaffMember getMemberId(){
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String memId = (String) params.get("loginId");
        //System.out.println("member: " + memId);
        return registrationFacade.getUserByLoginId(memId).getMemberId();
    }    
    
    private PaymentDAO repaymentEntries(){
        LoanAllocationGuidelines loanGuideline = loanAllocationGuidelinesMBean.getLoanSubType(loanType, loanAmount, numberOfPayment);
        if(Objects.nonNull(loanGuideline)){
            annualInterestRate = loanGuideline.getInterestRate();
            return repaymentEntries(loanType, loanAmount, annualInterestRate, 
                    loanStartDate, numberOfPayment, loanGuideline);
        }
        
        return new PaymentDAO();
    }
    
    private PaymentDAO repaymentEntries(LoanType loanType, double loanAmount, 
            double annualInterestRate, Date loanStartDate, int numberOfPayment, 
            LoanAllocationGuidelines loanGuideline){   
//        System.out.println("loanType: " + loanType + ", loanAmount: " + loanAmount + ", annualInterestRate: " + annualInterestRate
//        + ", loanStartDate: " + loanStartDate + ", numberOfPayment " + numberOfPayment + ", loanGuideline: " + loanGuideline);
        
        if(Objects.isNull(loanGuideline)){
            throw new RuntimeException("No setup value available for \"" + loanType.getLoanName() + "\" \"" + loanAmount + "\", \"" + loanType.getMaximumTenure() + "\"");
        }
        
//        System.out.println("repaymentEntries: loanAmount: " + loanAmount +", annualInterestRate: " + annualInterestRate + ", loanStartDate: "
//                + loanStartDate + ", numberOfPayment: " + numberOfPayment);
        if(loanAmount == 0 || annualInterestRate == 0 || loanStartDate == null || numberOfPayment < 1)
            return new PaymentDAO();
        
        RepaymentEntryDAO entry;
        double mBeginningBal = loanAmount;
        double mPayment;
        double mInterest;
        double mEndingBal;
        double mPrincipal;
        double totInterest = 0;
        double totCostOfLoan;
        mPayment = calculateMPayment(loanAmount, numberOfPayment, annualInterestRate);
        List<RepaymentEntryDAO> entryList;
        entryList = new ArrayList<>();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(loanStartDate);
        for(int i = 1; i <= numberOfPayment; i++){
            mInterest = calculateMInterest(mBeginningBal, annualInterestRate);
            totInterest = totInterest + mInterest;
            mPrincipal = mPayment - mInterest; 
            mEndingBal = mBeginningBal - mPrincipal;
            cal.add(Calendar.MONTH, 1);
            entry = new RepaymentEntryDAO(cal.getTime(), mBeginningBal, mPayment, mPrincipal, mInterest, mEndingBal);
            entryList.add(entry); 
            mBeginningBal = mEndingBal;
        }
        
        totCostOfLoan = loanAmount + totInterest;
        PaymentDAO entries = new PaymentDAO(entryList, totInterest, totCostOfLoan, loanAmount, annualInterestRate, 
        loanStartDate, mPayment, numberOfPayment, generateTransactionId(), loanType, loanGuideline); 
        
        return entries;
    }
    
    private double calculateMPayment(double loanAmount, int numberOfPayment, double annualInterestRate){
        //montly payment (Equated Monthly Payment - EMI) = P * r(1+r)^n/((1+r)^n - 1)
        //where P = principal, r = annual interest rate/100 (divided by 12 in case of monthly repayment)
        //n = number of repayments
        double rate = computeInterestRateCompounded(annualInterestRate);
        double onePlusRateRaisedToN = Math.pow((rate + 1), numberOfPayment);
        
        //numerator portion of formula
        double numerator = loanAmount * rate * onePlusRateRaisedToN;
        
        //Denomination portion of formula
        double denominator = onePlusRateRaisedToN - 1;
        
        // returns the monthly payment amount        
        return numerator / denominator;
    }
    
    private double calculateMInterest(double mRemainingBal, double annualInterestRate){   
        return mRemainingBal * computeInterestRateCompounded(annualInterestRate);
    }
    
    private double computeInterestRateCompounded(double annualInterestRate){
        return (annualInterestRate / 100 / 12);
    }

    public int getLoanPeriodInYears() {
        return loanPeriodInYears;
    }

    public void setLoanPeriodInYears(int loanPeriodInYears) {
        this.loanPeriodInYears = loanPeriodInYears;
    }
    
    public void loadRepaymentEntries(LoanApplication loan){
        selectedLoanApplication = loan;
//        System.out.println("routeToRepaymentEntries: " + selectedLoanApplication);
        String loanId = selectedLoanApplication.getLoanId();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("loanId", loanId);
        
        outstandingLoanPaymentList = loanRepaymentMBean.getLoanRepaymentEntries(selectedLoanApplication, LoanRepaymentStatusType.OUTSTANDING);        
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

    public LoanApplication getSelectedLoanApplication() {
        return selectedLoanApplication;
    }

    public void setSelectedLoanApplication(LoanApplication selectedLoanApplication) {
        this.selectedLoanApplication = selectedLoanApplication;
    }

    public List<LoanRepayment> getOutstandingLoanPaymentList() {
        return outstandingLoanPaymentList;
    }

    public void setOutstandingLoanPaymentList(List<LoanRepayment> outstandingLoanPaymentList) {
        this.outstandingLoanPaymentList = outstandingLoanPaymentList;
    }
    
    public LoanRepaymentMBean getLoanRepaymentMBean() {
        return loanRepaymentMBean;
    }

    public void setLoanRepaymentMBean(LoanRepaymentMBean loanRepaymentMBean) {
        this.loanRepaymentMBean = loanRepaymentMBean;
    }
    
    public String getLoggedOnMemberId(){
        return loggedOnMemberId;
    }
}