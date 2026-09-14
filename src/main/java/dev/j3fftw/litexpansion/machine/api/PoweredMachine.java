package dev.j3fftw.litexpansion.machine.api;

import dev.j3fftw.litexpansion.LiteXpansion;
import dev.j3fftw.litexpansion.utils.AddonBalancePolicy;

public interface PoweredMachine {

    int getDefaultEnergyConsumption();

    default int getFinalEnergyConsumption() {
        return AddonBalancePolicy.isNerfEnabled(LiteXpansion.getInstance().getConfig())
            ? getDefaultEnergyConsumption() * 2
            : getDefaultEnergyConsumption();
    }
}
