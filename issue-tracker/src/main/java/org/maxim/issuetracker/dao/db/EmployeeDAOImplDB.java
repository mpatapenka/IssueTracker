package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.EmployeeDAO;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.security.SecurityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplDB extends AbstractDAOHelperDB implements EmployeeDAO {

    @Override
    public void save(Employee employee) {
        currentSession().save(employee);
    }

    @Override
    public void delete(int id) {
        Employee obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Employee findById(int id) {
        return (Employee) currentSession().get(Employee.class, id);
    }

    @Override
    public Employee findByLogin(String login) {
        List employees = currentSession()
                .createQuery("from Employee where login=:username")
                .setParameter("username", login)
                .list();
        if (employees.size() > 0) {
            return (Employee) employees.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> list() {
        return currentSession().createQuery("from Employee").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> listAllowed() {
        return currentSession().createQuery("from Employee where position.name!=:pos")
                .setParameter("pos", SecurityConstants.ADMIN_POSITION).list();
    }

}
