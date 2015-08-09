package org.maxim.issuetracker.dao.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.MemberDAO;
import org.maxim.issuetracker.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImplDB extends AbstractDAOHelperDB implements MemberDAO {

    public MemberDAOImplDB() { }

    public MemberDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
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
    public List<Member> list() {
        return currentSession().createQuery("from Member").list();
    }

}
