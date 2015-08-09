package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> list();

    List<Activity> listLast(int offset);

    String convertToJson(List<Activity> activities);

}
