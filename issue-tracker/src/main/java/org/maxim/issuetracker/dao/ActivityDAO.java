package org.maxim.issuetracker.dao;

import org.maxim.issuetracker.domain.Activity;

import java.util.List;

public interface ActivityDAO {

    void save(Activity activity);

    void delete(int id);

    Activity findById(int id);

    List<Activity> list();

    List<Activity> listLast(int offset);

}
