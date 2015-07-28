package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Position;

import java.util.List;

/**
 * Created by Maxim on 23.07.2015.
 */
public interface PositionDAO {

    void add(Position role);

    void remove(long id);

    Position get(long id);

    List<Position> list();

}
