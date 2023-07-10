package com.capstone.validations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleValidation {

    @NotBlank(message = "Role name is required")
    @Size(max = 50, message = "Role name must be at most 50 characters")
    private String roleName;

    // Getters and setters

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
