package org.mpatapenka.issuetracker.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public final class JwtAuthRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}