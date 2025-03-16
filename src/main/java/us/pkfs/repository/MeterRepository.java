package us.pkfs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import us.pkfs.model.Meter;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeterRepository extends MongoRepository<Meter, UUID> {
    Optional<Meter> findByName(String name);
}