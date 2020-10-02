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
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private EmployeeRole role;


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

        EmployeeMembership that = (EmployeeMembership) obj;
        return Objects.equals(this.id, that.id);
    }
}