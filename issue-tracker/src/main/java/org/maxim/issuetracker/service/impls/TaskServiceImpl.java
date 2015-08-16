package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.TaskDAO;
import org.maxim.issuetracker.domain.Task;
import org.maxim.issuetracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public void add(Task task) {
        taskDAO.save(task);
    }

}
