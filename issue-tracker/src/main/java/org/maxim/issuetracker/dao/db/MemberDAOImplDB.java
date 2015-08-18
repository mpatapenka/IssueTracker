package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.MemberDAO;
import org.maxim.issuetracker.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImplDB extends AbstractDAOHelperDB implements MemberDAO {

    @Override
    public boolean isExist(Member member) {
        List members = currentSession()
                .createQuery("from Member where project.id=:projectid and employee.id=:employeeid")
                .setParameter("projectid", member.getProject().getId())
                .setParameter("employeeid", member.getEmployee().getId())
                .list();
        return members.size() > 0;
    }

    @Override
    public void save(Member member) {
        currentSession().save(member);
    }

    @Override
    public void delete(int id) {
        Member obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Member findById(int id) {
        return (Member) currentSession().get(Member.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Member> list() {
        return currentSession().createQuery("from Member").list();
    }

}
