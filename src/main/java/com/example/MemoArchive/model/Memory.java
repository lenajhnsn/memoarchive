package com.example.MemoArchive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memory {
    //TODO: Add data validation constraints (already complete in Users)
    private int memoryId;
    private int userId;
    private String type;
    private String content;
    private String description;
    private LocalDate memoryDate;
    private LocalDate creationDate;

    // Getters, setters, and constructors (no arg and all arg) are created with Lombok.
}
