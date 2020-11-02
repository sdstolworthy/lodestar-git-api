package com.redhat.labs.lodestar.models.gitlab;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTransfer {

    private Integer id;
    private Integer namespace;

}