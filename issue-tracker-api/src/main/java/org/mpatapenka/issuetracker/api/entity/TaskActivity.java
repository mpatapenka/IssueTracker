package org.mpatapenka.issuetracker.api.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SuperBuilder(toBuilder = true)
public class TaskActivity extends IdentifiedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private Employee addedBy;

    @NotNull
    private ZonedDateTime startedAt;

    @NotNull
    private ZonedDateTime completedAt;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String comment;
}