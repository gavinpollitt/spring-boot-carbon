package com.snowfort.carbon.sci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class PowerUtilizationEnergyConsumptionProvider implements EnergyConsumptionProvider {

    private static final Logger log = LoggerFactory.getLogger(PowerUtilizationEnergyConsumptionProvider.class);


    /**
     * This calculates the power usage in kW.
     * While charging the power usage rate is positive otherwise it negative so its absolute value is considered.
     * It gives the reading in mW, hence divided it by 10e-6
     *
     * @return the energy consumption
     */
    @Override
    public double getEnergyConsumption() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        log.info(SystemInfo.getCurrentPlatform().getName());
        log.info(hal.getComputerSystem().toString());

        return hal.getPowerSources()
                .stream()
                .mapToDouble(ps -> Math.abs(ps.getPowerUsageRate()) / 1000000)
                .average()
                .orElse(0.0);
    }
}
