package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Position;

import java.util.List;

public interface PositionService {

    Position get(int id);

    List<Position> list();

    List<Position> listAllowed();

}
