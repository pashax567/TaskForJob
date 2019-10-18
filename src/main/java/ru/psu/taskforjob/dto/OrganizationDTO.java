package ru.psu.taskforjob.dto;

import java.util.UUID;

public class OrganizationDTO {
    private UUID id;
    private String name;
    private UUID headId;

    public OrganizationDTO(UUID id, String name, UUID headId) {
        this.id = id;
        this.name = name;
        this.headId = headId;
    }

    public OrganizationDTO() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadId(UUID headId) {
        this.headId = headId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getHeadId() {
        return headId;
    }
}
