package org.maxim.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "_date")
    private Date date;

    @Column(name = "duration")
    @Min(value = ValidationConstants.MIN_DURATION)
    @Max(value = ValidationConstants.MAX_DURATION_PER_DAY)
    private int duration;

    @Column(name = "comment")
    @Size(max = ValidationConstants.TEXT_MAX_SIZE)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "memberid", nullable = false)
    @JsonBackReference
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "assigmentid", nullable = false)
    @JsonBackReference
    private Assigment assigment;

    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Assigment getAssigment() {
        return assigment;
    }

    public void setAssigment(Assigment assigment) {
        this.assigment = assigment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        if (id != activity.id) return false;
        if (duration != activity.duration) return false;
        if (date != null ? !date.equals(activity.date) : activity.date != null) return false;
        if (comment != null ? !comment.equals(activity.comment) : activity.comment != null) return false;
        if (member != null ? !member.equals(activity.member) : activity.member != null) return false;
        return !(assigment != null ? !assigment.equals(activity.assigment) : activity.assigment != null);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                ", duration=" + duration +
                ", comment='" + comment + '\'' +
                ", member=" + member +
                ", assigment=" + assigment +
                '}';
    }

}
