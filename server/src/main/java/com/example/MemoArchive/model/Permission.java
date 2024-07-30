package com.example.MemoArchive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    //TODO: Add data validation constraints (already complete in Users)
    private int permissionId;
    private int ownerUserId;
    private int contributorUserId;
    private String accessType;

    // Getters, setters, and constructors (no arg and all arg) are created with Lombok.

}
