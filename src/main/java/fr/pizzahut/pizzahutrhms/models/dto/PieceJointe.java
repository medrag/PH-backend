package fr.pizzahut.pizzahutrhms.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PieceJointe {
    private String id;
    private String name;
    private String length;
    private byte[] content;
    private String type;
}
