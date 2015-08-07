package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(max = Constants.TEXT_MAX_SIZE, message = "Attachment name" + Constants.SIZE_ERROR_MSG_SUFFIX)
    private String name;

    @Column(name = "size")
    @Size(max = Constants.TEXT_MAX_SIZE, message = "Attachment size" + Constants.SIZE_ERROR_MSG_SUFFIX)
    private String size;

    @Column(name = "description")
    @Size(max = Constants.TEXT_MAX_SIZE, message = "Attachment description" + Constants.SIZE_ERROR_MSG_SUFFIX)
    private String description;

    @NotNull(message = "Attachment project" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "projectid", nullable = false)
    @JsonBackReference
    private Project project;

    @NotNull(message = "Attachment task" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "taskid", nullable = false)
    @JsonBackReference
    private Task task;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (project != null ? !project.equals(that.project) : that.project != null) return false;
        return !(task != null ? !task.equals(that.task) : that.task != null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", project=" + project +
                ", task=" + task +
                '}';
    }

}
