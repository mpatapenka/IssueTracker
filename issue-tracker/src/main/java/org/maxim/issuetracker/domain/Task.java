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
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    @JsonBackReference
    private Project project;

    @Column(name = "description")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String description;

    @NotNull
    @Column(name = "psd")
    private Date planStartDate;

    @NotNull
    @Column(name = "ped")
    private Date planEndDate;

    @Column(name = "asd")
    private Date actionStartDate;

    @Column(name = "aed")
    private Date actionEndDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "statusid", nullable = false)
    @JsonBackReference
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Attachment> attachments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(CascadeType.ALL)
    @JsonManagedReference
    private Set<Assigment> assigments = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Date getActionStartDate() {
        return actionStartDate;
    }

    public void setActionStartDate(Date actionStartDate) {
        this.actionStartDate = actionStartDate;
    }

    public Date getActionEndDate() {
        return actionEndDate;
    }

    public void setActionEndDate(Date actionEndDate) {
        this.actionEndDate = actionEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @XmlTransient
    public Set<Assigment> getAssigments() {
        return assigments;
    }

    public void setAssigments(Set<Assigment> assigments) {
        this.assigments = assigments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (project != null ? !project.equals(task.project) : task.project != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (planStartDate != null ? !planStartDate.equals(task.planStartDate) : task.planStartDate != null)
            return false;
        return !(planEndDate != null ? !planEndDate.equals(task.planEndDate) : task.planEndDate != null);
    }

    @Override
    public int hashCode() {
        int result = project != null ? project.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (planStartDate != null ? planStartDate.hashCode() : 0);
        result = 31 * result + (planEndDate != null ? planEndDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", project=" + project +
                ", description='" + description + '\'' +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", actionStartDate=" + actionStartDate +
                ", actionEndDate=" + actionEndDate +
                ", status=" + status +
                ", attachments=" + attachments +
                ", assigments=" + assigments +
                '}';
    }

}
