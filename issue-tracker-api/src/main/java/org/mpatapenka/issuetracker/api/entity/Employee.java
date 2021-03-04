package org.mpatapenka.issuetracker.api.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SuperBuilder(toBuilder = true)
public class Employee extends IdentifiedEntity {

    @NotNull
    private UUID uuid;

    @NotEmpty
    @Size(max = 50)
    private String firstName;

    @NotEmpty
    @Size(max = 50)
    private String lastName;

    @NotEmpty
    @Size(max = 10)
    private String username;

    @NotEmpty
    @Size(max = 60)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private EmployeePosition position;
}