package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Message {
    @Id
    private String id;
    private LocalDateTime date;
    private String message;
    private boolean userType;
}
