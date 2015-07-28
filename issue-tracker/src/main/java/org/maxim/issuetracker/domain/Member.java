package org.maxim.issuetracker.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Member project" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;

    @NotNull(message = "Member employee" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "employeeid", nullable = false)
    private Employee employee;

    @NotNull(message = "Member role" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Cascade(CascadeType.ALL)
    private Set<Assigment> assigments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Cascade(CascadeType.ALL)
    private Set<Activity> activities = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Assigment> getAssigments() {
        return assigments;
    }

    public void setAssigments(Set<Assigment> assigments) {
        this.assigments = assigments;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (id != member.id) return false;
        if (project != null ? !project.equals(member.project) : member.project != null) return false;
        if (employee != null ? !employee.equals(member.employee) : member.employee != null) return false;
        if (role != null ? !role.equals(member.role) : member.role != null) return false;
        if (assigments != null ? !assigments.equals(member.assigments) : member.assigments != null) return false;
        return !(activities != null ? !activities.equals(member.activities) : member.activities != null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", project=" + project +
                ", employee=" + employee +
                ", role=" + role +
                ", assigments=" + assigments +
                ", activities=" + activities +
                '}';
    }

}
