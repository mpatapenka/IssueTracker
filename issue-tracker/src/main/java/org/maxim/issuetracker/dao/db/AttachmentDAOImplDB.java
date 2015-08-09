package org.maxim.issuetracker.dao.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.AttachmentDAO;
import org.maxim.issuetracker.domain.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachmentDAOImplDB extends AbstractDAOHelperDB implements AttachmentDAO {

    public AttachmentDAOImplDB() { }

    public AttachmentDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Attachment attachment) {
        currentSession().save(attachment);
    }

    @Override
    public void delete(int id) {
        Attachment obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Attachment findById(int id) {
        return (Attachment) currentSession().get(Attachment.class, id);
    }

    @Override
    public List<Attachment> list() {
        return currentSession().createQuery("from Attachment").list();
    }

}
