package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.PositionDAO;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.security.SecurityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImplDB extends AbstractDAOHelperDB implements PositionDAO {

    @Override
    public void save(Position position) {
        currentSession().save(position);
    }

    @Override
    public void delete(int id) {
        Position obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Position findById(int id) {
        return (Position) currentSession().get(Position.class, id);
    }

    @Override
    public List<Position> list() {
        return currentSession().createQuery("from Position").list();
    }

    @Override
    public List<Position> listAllowed() {
        return currentSession().createQuery("from Position where name!=:pos")
                .setParameter("pos", SecurityConstants.ADMIN_POSITION).list();
    }

}
