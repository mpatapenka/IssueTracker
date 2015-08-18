package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 4, max = ValidationConstants.TEXT_MAX_SIZE)
    private String name;

    @Column(name = "description")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Member> members = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Attachment> attachments = new HashSet<>();

    @XmlTransient
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    @XmlTransient
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @XmlTransient
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return !(name != null ? !name.equals(project.name) : project.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", members=" + members +
                ", tasks=" + tasks +
                ", attachments=" + attachments +
                '}';
    }
    
}
