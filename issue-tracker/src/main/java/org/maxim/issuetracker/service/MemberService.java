package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Member;

import java.util.List;

public interface MemberService {

    void add(Member member);

    Member get(int id);

    List<Member> list();

}
