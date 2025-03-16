package us.pkfs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import us.pkfs.model.MeterValue;

import java.util.UUID;

@Repository
public interface MeterValueRepository extends MongoRepository<MeterValue, UUID> {
}
