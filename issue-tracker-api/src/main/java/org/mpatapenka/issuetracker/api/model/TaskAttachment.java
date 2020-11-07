package org.mpatapenka.issuetracker.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TaskAttachment extends IdentifiedEntity {

    @NotNull
    @ManyToOne
    private Task task;

    @NotEmpty
    @Size(max = 255)
    private String fileName;

    @NotNull
    private Long fileSize;

    @NotEmpty
    @Size(max = 510)
    private String filePath;

    @Lob
    private String description;
}