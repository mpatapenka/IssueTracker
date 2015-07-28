package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Position;

import java.util.List;

public interface PositionDAO {

    void save(Position position);

    void delete(int id);

    Position findById(int id);

    List<Position> list();

}
