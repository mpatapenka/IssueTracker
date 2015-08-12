package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.MemberDAO;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    @Transactional
    public void add(Member member) {
        if (memberDAO.isExist(member)) {
            throw new IllegalArgumentException("Employee already working with project.");
        }
        memberDAO.save(member);
    }

    @Override
    @Transactional
    public List<Member> list() {
        return memberDAO.list();
    }

}