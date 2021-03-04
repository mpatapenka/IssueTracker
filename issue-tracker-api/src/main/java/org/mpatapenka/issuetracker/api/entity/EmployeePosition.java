package org.mpatapenka.issuetracker.api.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SuperBuilder(toBuilder = true)
public class EmployeePosition extends IdentifiedEntity {

    @NotEmpty
    @Size(max = 100)
    private String name;
}