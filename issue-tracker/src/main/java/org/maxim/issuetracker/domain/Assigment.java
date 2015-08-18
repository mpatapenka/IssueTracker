package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "assigment")
public class Assigment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "memberid", nullable = true)
    @JsonBackReference
    private Member member;

    @NotNull
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "taskid", nullable = false)
    @JsonBackReference
    private Task task;

    @Column(name = "description")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assigment")
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        Assigment assigment = (Assigment) o;

        if (member != null ? !member.equals(assigment.member) : assigment.member != null) return false;
        if (task != null ? !task.equals(assigment.task) : assigment.task != null) return false;
        return !(description != null ? !description.equals(assigment.description) : assigment.description != null);
    }

    @Override
    public int hashCode() {
        int result = member != null ? member.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Assigment{" +
                "id=" + id +
                ", member=" + member +
                ", task=" + task +
                ", description='" + description + '\'' +
                ", activities=" + activities +
                '}';
    }

}
