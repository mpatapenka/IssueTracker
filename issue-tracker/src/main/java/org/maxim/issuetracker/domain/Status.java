package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(max = Constants.TEXT_MAX_SIZE, message = "Status name" + Constants.SIZE_ERROR_MSG_SUFFIX)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Task> tasks = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        if (id != status.id) return false;
        if (name != null ? !name.equals(status.name) : status.name != null) return false;
        return !(tasks != null ? !tasks.equals(status.tasks) : status.tasks != null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }

}
