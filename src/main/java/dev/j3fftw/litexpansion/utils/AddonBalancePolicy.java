package dev.j3fftw.litexpansion.utils;

import org.bukkit.configuration.ConfigurationSection;

public final class AddonBalancePolicy {

    private AddonBalancePolicy() {
    }

    public static boolean isNerfEnabled(ConfigurationSection config) {
        // Match config.yml: missing options must not silently override other addons' energy.
        return config.getBoolean("options.nerf-other-addons", false);
    }
}
