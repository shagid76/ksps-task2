package us.pkfs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.pkfs.model.Meter;
import us.pkfs.repository.MeterRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MeterService {
    private final MeterRepository meterRepository;

    public List<Meter> findAll() {
        return meterRepository.findAll();
    }

    public Meter findByName(String name) throws Exception {
        Meter meter = meterRepository.findByName(name)
                .orElseThrow(() -> new Exception("Meter with name " + name + " not foudn"));
        return meter;
    }

    public void saveMeter(Meter meter) {
        meterRepository.save(meter);
    }

    public void delete(UUID id) throws Exception {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new Exception("Meter with id " + id + " not found"));

        meterRepository.delete(meter);
    }
}