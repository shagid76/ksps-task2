package us.pkfs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "values")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeterValue {
    @Id
    private UUID valueId = UUID.randomUUID();
    private Double dayValue;
    private Double nightValue;
    private LocalDate date = LocalDate.now();
}