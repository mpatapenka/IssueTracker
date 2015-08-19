package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String name;

    @Column(name = "filesystemname")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String fileSystemName;

    @Column(name = "size")
    private long size;

    @Column(name = "description")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    @JsonBackReference
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "taskid", nullable = false)
    @JsonBackReference
    private Task task;

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

    public String getFileSystemName() {
        return fileSystemName;
    }

    public void setFileSystemName(String fileSystemName) {
        this.fileSystemName = fileSystemName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
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

        if (size != that.size) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(fileSystemName != null ? !fileSystemName.equals(that.fileSystemName) : that.fileSystemName != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fileSystemName != null ? fileSystemName.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileSystemName='" + fileSystemName + '\'' +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", project=" + project +
                ", task=" + task +
                '}';
    }

}
