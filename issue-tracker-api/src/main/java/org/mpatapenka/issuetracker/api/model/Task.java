package org.mpatapenka.issuetracker.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, updatable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "assigned_to", updatable = false)
    private Employee assignedTo;

    @NotNull
    private String description;

    @NotNull
    private LocalDateTime scheduledStartAt;

    @NotNull
    private LocalDateTime scheduledCompleteAt;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "task")
    @Builder.Default
    private Set<Attachment> attachments = new HashSet<>();


    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task that = (Task) obj;
        return Objects.equals(this.id, that.id);
    }
}