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
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(max = 100)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false, updatable = false)
    private EmployeePosition position;


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

        Employee that = (Employee) obj;
        return Objects.equals(this.id, that.id);
    }
}