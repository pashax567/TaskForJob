package ru.psu.taskforjob.dto;

import java.util.UUID;

public class EmployeeDTO {
    private UUID id;
    private String name;
    private UUID orgId;
    private UUID chiefId;

    public EmployeeDTO(UUID id, String name, UUID orgId, UUID chiefId) {
        this.id = id;
        this.name = name;
        this.orgId = orgId;
        this.chiefId = chiefId;
    }

    public EmployeeDTO() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgId(UUID orgId) {
        this.orgId = orgId;
    }

    public void setChiefId(UUID chiefId) {
        this.chiefId = chiefId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getOrgId() {
        return orgId;
    }

    public UUID getChiefId() {
        return chiefId;
    }
}
