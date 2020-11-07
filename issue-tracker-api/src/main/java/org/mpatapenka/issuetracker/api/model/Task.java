package org.mpatapenka.issuetracker.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Task extends IdentifiedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;

    @NotEmpty
    @Size(max = 100)
    private String summary;

    @Lob
    private String description;

    @NotNull
    private LocalDateTime scheduledStartAt;

    @NotNull
    private LocalDateTime scheduledCompleteAt;

    private ZonedDateTime startedAt;

    private ZonedDateTime completedAt;

    @OneToMany(mappedBy = "task")
    @Builder.Default
    private Set<TaskAttachment> attachments = new HashSet<>();
}