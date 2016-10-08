
package com.corebits.ericsson.tms.utils;

/**
 *
 * @author xtphere
 */
public enum ApprovalStatusType {
    PENDING(-1, "Pending"), APPROVED(1, "Approved");

    Integer key;
    String value;

    private ApprovalStatusType(Integer key, String value){
        this.value = value;
        this.key = key;
    }
    
    public Integer getKey(){
        return key;
    }
    
    public String getValue(){
        return value;
    }
    
}
