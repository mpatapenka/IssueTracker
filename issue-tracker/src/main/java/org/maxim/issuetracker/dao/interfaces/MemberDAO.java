package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Member;

import java.util.List;

public interface MemberDAO {

    void save(Member member);

    void delete(int id);

    Member findById(int id);

    List<Member> list();

}
