package com.example.MemoArchive.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    //TODO: Add data validation constraints (already complete in Users)
    private int tagId;
    private int memoryId;
    private int userId;

    // Getters, setters, and constructors (no arg and all arg) are created with Lombok.
}
