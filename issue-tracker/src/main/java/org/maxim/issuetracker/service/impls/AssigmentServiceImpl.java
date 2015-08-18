package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.AssigmentDAO;
import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.service.AssigmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssigmentServiceImpl implements AssigmentService {

    @Autowired
    private AssigmentDAO assigmentDAO;

    @Override
    @Transactional
    public void add(Assigment assigment) {
        assigmentDAO.save(assigment);
    }

    @Override
    @Transactional
    public Assigment get(int id) {
        return assigmentDAO.findById(id);
    }

}
