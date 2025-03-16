package us.pkfs.task2.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import us.pkfs.model.Meter;
import us.pkfs.model.MeterValue;
import us.pkfs.repository.MeterRepository;
import us.pkfs.repository.MeterValueRepository;
import us.pkfs.service.MeterValueService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeterValueServiceTest {
    @Mock
    private MeterValueRepository meterValueRepository;

    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private MeterValueService meterValueService;

    private double dayTariff = 100;
    private double nightTariff = 100;
    private double dayAdditional = 100;
    private double nightAdditional = 100;

    private Meter meter;
    private MeterValue meterValue;

    @BeforeEach
    void setUp() {
        meter = new Meter();
        meter.setId(UUID.randomUUID());
        meter.setName("TestMeter");
        meter.setCurrentDay(100.0);
        meter.setCurrentNight(50.0);
        meter.setData(new ArrayList<>());

        meterValue = new MeterValue();
        meterValue.setDayValue(110.0);
        meterValue.setNightValue(55.0);
    }

    @Test
    void testSaveMeterValue() throws Exception {
        when(meterRepository.findByName("TestMeter")).thenReturn(Optional.of(meter));

        Meter updatedMeter = meterValueService.save(meterValue, "TestMeter");

        assertEquals(110, updatedMeter.getCurrentDay());
        assertEquals(55, updatedMeter.getCurrentNight());
    }

    @Test
    void testSaveMeterValueMeterNotFound() {
        when(meterRepository.findByName("Unknown")).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> meterValueService.save(meterValue, "Unknown"));
        assertEquals("Meter Unknown not found", exception.getMessage());
    }
}