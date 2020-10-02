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
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false, updatable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false, updatable = false)
    private Employee addedBy;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    private String comment;


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

        TaskActivity that = (TaskActivity) obj;
        return Objects.equals(this.id, that.id);
    }
}