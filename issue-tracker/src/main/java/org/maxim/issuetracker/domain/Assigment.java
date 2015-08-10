package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assigment")
public class Assigment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "memberid", nullable = false)
    @JsonBackReference
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "taskid", nullable = false)
    @JsonBackReference
    private Task task;

    @Column(name = "description")
    @Size(max = Constants.TEXT_MAX_SIZE)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assigment")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Activity> activities = new HashSet<>();

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
        if (id != assigment.id) return false;
        if (member != null ? !member.equals(assigment.member) : assigment.member != null) return false;
        if (task != null ? !task.equals(assigment.task) : assigment.task != null) return false;
        if (description != null ? !description.equals(assigment.description) : assigment.description != null)
            return false;
        return !(activities != null ? !activities.equals(assigment.activities) : assigment.activities != null);
    }

    @Override
    public int hashCode() {
        return id;
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
