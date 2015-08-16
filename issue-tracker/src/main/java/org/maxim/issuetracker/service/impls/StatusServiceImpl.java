package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.StatusDAO;
import org.maxim.issuetracker.domain.Status;
import org.maxim.issuetracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDAO statusDAO;

    @Override
    @Transactional
    public Status getInitial() {
        final int initialPos = 0;
        return statusDAO.list().get(initialPos);
    }
}
