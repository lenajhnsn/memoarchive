package com.example.MemoArchive.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate memoryDate;
    private LocalDate creationDate;

    // Getters, setters, and constructors (no arg and all arg) are created with Lombok.
}
