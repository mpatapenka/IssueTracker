package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "projectid", nullable = false)
    @JsonBackReference
    private Project project;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "employeeid", nullable = false)
    @JsonBackReference
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "roleid", nullable = false)
    @JsonBackReference
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Assigment> assigments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Activity> activities = new HashSet<>();

    @XmlTransient
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

    @XmlTransient
    public Set<Assigment> getAssigments() {
        return assigments;
    }

    public void setAssigments(Set<Assigment> assigments) {
        this.assigments = assigments;
    }

    @XmlTransient
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
