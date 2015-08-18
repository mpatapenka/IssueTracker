package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.AttachmentDAO;
import org.maxim.issuetracker.domain.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachmentDAOImplDB extends AbstractDAOHelperDB implements AttachmentDAO {

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
    @SuppressWarnings("unchecked")
    public List<Attachment> list() {
        return currentSession().createQuery("from Attachment").list();
    }

}
