package us.pkfs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.pkfs.model.Meter;
import us.pkfs.model.MeterValue;
import us.pkfs.service.MeterService;
import us.pkfs.service.MeterValueService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meter")
@RequiredArgsConstructor
public class MeterController {

    private final MeterService meterService;
    private final MeterValueService meterValueService;

    @GetMapping("/all")
    public List<Meter> findAll() {
        return meterService.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody Meter meter) {
        meterService.saveMeter(meter);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        try {
            meterService.delete(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Meter not found " + e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Meter> findByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(meterService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/{name}/value")
    public ResponseEntity<?> uploadValue(@PathVariable("name") String name, @RequestBody MeterValue value) {
        try {
            Meter updatedMeter = meterValueService.save(value, name);
            return ResponseEntity.ok(updatedMeter);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}