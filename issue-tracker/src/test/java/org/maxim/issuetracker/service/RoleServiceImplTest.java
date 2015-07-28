package org.maxim.issuetracker.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.service.interfaces.RoleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class RoleServiceImplTest {

    private static ApplicationContext context;
    private static RoleService service;
    private static Role role;

    @BeforeClass
    public static void setUpTest() {
        context = new ClassPathXmlApplicationContext("/WEB-INF/spring/root-context.xml");
        service = (RoleService) context.getBean("roleServiceImpl");

        role = new Role();
        role.setName("Test role");
    }

    @Test
    public void runTest() {
        List<Role> roles = service.list();
        int beforeSize = roles.size();

        service.add(role);
        Assert.assertNotEquals(0, role.getId());

        roles = service.list();
        Assert.assertNotEquals(beforeSize, roles.size());
        beforeSize = roles.size();

        Role roleFromDB = service.get(role.getId());
        Assert.assertEquals(role, roleFromDB);

        service.remove(role.getId());
        roles = service.list();
        Assert.assertNotEquals(beforeSize, roles.size());
    }

}
