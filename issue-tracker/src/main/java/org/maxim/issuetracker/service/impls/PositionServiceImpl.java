package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.PositionDAO;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDAO positionDAO;

    @Override
    @Transactional
    public Position get(int id) {
        return positionDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Position> list() {
        return positionDAO.list();
    }

    @Override
    @Transactional
    public List<Position> listAllowed() {
        return positionDAO.listAllowed();
    }

}
