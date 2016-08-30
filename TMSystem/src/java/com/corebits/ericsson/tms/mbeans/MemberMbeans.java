package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.MemberController;
import com.corebits.ericsson.tms.models.StaffMember;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "member")
@SessionScoped
public class MemberMbeans implements Serializable {

    List<StaffMember> members;
    private StaffMember member;

    @Inject
    MemberController mc;

    public MemberMbeans() {
    }

    public List<StaffMember> getMembersAll() {
        List<StaffMember> members1 = mc.getMembers();
        members = members1;
        return members1;
    }

    public List<StaffMember> getMembers() {
        return members;
    }

    public void setMembers(List<StaffMember> members) {
        this.members = members;
    }

    public StaffMember getMember() {
        return member;
    }

    public void setMember(StaffMember member) {
        this.member = member;
    }
    
    
}
