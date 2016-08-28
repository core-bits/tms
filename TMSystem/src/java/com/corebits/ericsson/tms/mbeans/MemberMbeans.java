/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.MemberController;
import com.corebits.ericsson.tms.models.Member1;
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

    List<Member1> members;
    private Member1 member;

    @Inject
    MemberController mc;

    public MemberMbeans() {
    }

    public List<Member1> getMembersAll() {
        List<Member1> members1 = mc.getMembers();
        members = members1;
        return members1;
    }

    public List<Member1> getMembers() {
        return members;
    }

    public void setMembers(List<Member1> members) {
        this.members = members;
    }

    public Member1 getMember() {
        return member;
    }

    public void setMember(Member1 member) {
        this.member = member;
    }
    
    
}
