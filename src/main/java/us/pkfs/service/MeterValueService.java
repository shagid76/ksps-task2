package us.pkfs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.pkfs.model.Meter;
import us.pkfs.model.MeterValue;
import us.pkfs.repository.MeterRepository;
import us.pkfs.repository.MeterValueRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MeterValueService {
    private final MeterValueRepository meterValueRepository;
    private final MeterRepository meterRepository;

    @Value("${tariff.day}")
    private Double dayTariff;

    @Value("${tariff.night}")
    private Double nightTariff;

    @Value("${tariff.additional.day}")
    private Double dayAdditional;

    @Value("${tariff.additional.night}")
    private Double nightAdditional;

    public Meter save(MeterValue value, String meterName)throws Exception {
        Meter meter = meterRepository.findByName(meterName)
                .orElseThrow(() -> new Exception("Meter " + meterName + " not found"));

        double previousDay = meter.getCurrentDay();
        double previousNight = meter.getCurrentNight();

        double newDay = value.getDayValue();
        double newNight = value.getNightValue();

        boolean adjusted = false;
        if (newDay < previousDay) {
            newDay = previousDay + dayAdditional;
            adjusted = true;
        }
        if (newNight < previousNight) {
            newNight = previousNight + nightAdditional;
            adjusted = true;
        }

        if (adjusted) {
            System.out.println("Warning: Adjusted meter values due to lower input!");
        }

        value.setDayValue(newDay);
        value.setNightValue(newNight);
        value.setDate(LocalDate.now());

        meter.getData().add(value);
        meter.setCurrentDay(newDay);
        meter.setCurrentNight(newNight);
        return meter;
    }
}