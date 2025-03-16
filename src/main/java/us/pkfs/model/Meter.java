package us.pkfs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "metrics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private Double currentDay;
    private Double currentNight;
    private List<MeterValue> data;
}