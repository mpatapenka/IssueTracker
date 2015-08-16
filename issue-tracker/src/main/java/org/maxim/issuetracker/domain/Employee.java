package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    @Size(min = 3, max = ValidationConstants.TEXT_MAX_SIZE)
    private String firstName;

    @Column(name = "lastname")
    @Size(min = 4, max = ValidationConstants.TEXT_MAX_SIZE)
    private String lastName;

    @Column(name = "login")
    @Size(min = 3, max = ValidationConstants.TEXT_MAX_SIZE)
    private String login;

    @Column(name = "password")
    @Size(min = 3, max = ValidationConstants.TEXT_MAX_SIZE)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "positionid", nullable = false)
    @JsonBackReference
    private Position position;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Member> members = new HashSet<>();

    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlTransient
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @XmlTransient
    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (id != employee.id) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (login != null ? !login.equals(employee.login) : employee.login != null) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        return !(position != null ? !position.equals(employee.position) : employee.position != null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                ", members=" + members +
                '}';
    }

}
