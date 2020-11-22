package org.mpatapenka.issuetracker.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data(staticConstructor = "from")
public final class JwtAuthResponse {

    @NotEmpty
    private final String token;
}