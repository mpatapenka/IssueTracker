package org.maxim.issuetracker.dao;

import org.maxim.issuetracker.domain.Member;

import java.util.List;

public interface MemberDAO {

    boolean isExist(Member member);

    void save(Member member);

    void delete(int id);

    Member findById(int id);

    List<Member> list();

}
