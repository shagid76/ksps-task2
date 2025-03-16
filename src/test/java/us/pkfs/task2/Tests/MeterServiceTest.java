package us.pkfs.task2.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import us.pkfs.model.Meter;
import us.pkfs.repository.MeterRepository;
import us.pkfs.service.MeterService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeterServiceTest {
    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private MeterService meterService;

    private Meter meter;

    @BeforeEach
    void setUp() {
        meter = new Meter();
        meter.setId(UUID.randomUUID());
        meter.setName("TestMeter");
    }

    @Test
    void testFindAll() {
        when(meterRepository.findAll()).thenReturn(List.of(meter));
        List<Meter> meters = meterService.findAll();
        assertFalse(meters.isEmpty());
        assertEquals(1, meters.size());
    }

    @Test
    void testFindByName() throws Exception {
        when(meterRepository.findByName("TestMeter")).thenReturn(Optional.of(meter));
        Meter foundMeter = meterService.findByName("TestMeter");
        assertNotNull(foundMeter);
        assertEquals("TestMeter", foundMeter.getName());
    }

    @Test
    void testFindByNameNotFound() {
        when(meterRepository.findByName("Unknown")).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> meterService.findByName("Unknown"));
        assertEquals("Meter with name Unknown not foudn", exception.getMessage());
    }

    @Test
    void testSaveMeter() {
        meterService.saveMeter(meter);
        verify(meterRepository, times(1)).save(meter);
    }

    @Test
    void testDelete() throws Exception {
        when(meterRepository.findById(meter.getId())).thenReturn(Optional.of(meter));
        meterService.delete(meter.getId());
        verify(meterRepository, times(1)).delete(meter);
    }

    @Test
    void testDeleteNotFound() {
        UUID randomId = UUID.randomUUID(); // Generate UUID once
        when(meterRepository.findById(randomId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> meterService.delete(randomId));

        assertTrue(exception.getMessage().contains("not found"));
    }

}
