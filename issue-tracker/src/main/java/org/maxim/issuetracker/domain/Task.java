package org.maxim.issuetracker.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Task project" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;

    @Column(name = "description")
    @Size(max = Constants.TEXT_MAX_SIZE, message = "Task description" + Constants.SIZE_ERROR_MSG_SUFFIX)
    private String description;

    @Column(name = "psd")
    private Date planStartDate;

    @Column(name = "ped")
    private Date planEndDate;

    @Column(name = "asd")
    private Date actionStartDate;

    @Column(name = "aed")
    private Date actionEndDate;

    @NotNull(message = "Task status" + Constants.NULL_ERROR_MSG_SUFFIX)
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "statusid", nullable = false)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(CascadeType.ALL)
    private Set<Attachment> attachments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(CascadeType.ALL)
    private Set<Assigment> assigments = new HashSet<>();

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

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

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
        if (id != task.id) return false;
        if (project != null ? !project.equals(task.project) : task.project != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (planStartDate != null ? !planStartDate.equals(task.planStartDate) : task.planStartDate != null)
            return false;
        if (planEndDate != null ? !planEndDate.equals(task.planEndDate) : task.planEndDate != null) return false;
        if (actionStartDate != null ? !actionStartDate.equals(task.actionStartDate) : task.actionStartDate != null)
            return false;
        if (actionEndDate != null ? !actionEndDate.equals(task.actionEndDate) : task.actionEndDate != null)
            return false;
        if (status != null ? !status.equals(task.status) : task.status != null) return false;
        if (attachments != null ? !attachments.equals(task.attachments) : task.attachments != null) return false;
        return !(assigments != null ? !assigments.equals(task.assigments) : task.assigments != null);
    }

    @Override
    public int hashCode() {
        return id;
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
