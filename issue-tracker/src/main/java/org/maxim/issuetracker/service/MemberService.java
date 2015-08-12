package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Member;

import java.util.List;

public interface MemberService {

    void add(Member member);

    List<Member> list();

}
