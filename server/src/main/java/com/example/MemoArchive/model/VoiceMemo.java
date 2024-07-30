package com.example.MemoArchive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoiceMemo {
    //TODO: Add data validation constraints (already complete in Users)
    private int voiceMemoId;
    private int memoryId;
    private String transcriptText;
}

// Getters, setters, and constructors (no arg and all arg) are created with Lombok.