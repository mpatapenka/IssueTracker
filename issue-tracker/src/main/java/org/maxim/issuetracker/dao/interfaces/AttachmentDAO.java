package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Attachment;

import java.util.List;

public interface AttachmentDAO {

    void save(Attachment attachment);

    void delete(int id);

    Attachment findById(int id);

    List<Attachment> list();

}
