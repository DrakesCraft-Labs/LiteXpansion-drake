package dev.j3fftw.litexpansion.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddonBalancePolicyTest {

    @Test
    void omittedOptionDoesNotEnableEnergyOverrides() {
        assertFalse(AddonBalancePolicy.isNerfEnabled(new YamlConfiguration()));
    }

    @Test
    void explicitOptInRemainsSupported() {
        final YamlConfiguration config = new YamlConfiguration();
        config.set("options.nerf-other-addons", true);
        assertTrue(AddonBalancePolicy.isNerfEnabled(config));
    }

    @Test
    void explicitOptOutWinsOverLegacyDefaults() {
        final YamlConfiguration config = new YamlConfiguration();
        config.addDefault("options.nerf-other-addons", true);
        config.set("options.nerf-other-addons", false);
        assertFalse(AddonBalancePolicy.isNerfEnabled(config));
    }

    @Test
    void invalidOptionDoesNotEnableEnergyOverrides() {
        final YamlConfiguration config = new YamlConfiguration();
        config.set("options.nerf-other-addons", "invalid");
        assertFalse(AddonBalancePolicy.isNerfEnabled(config));
    }

    @Test
    void bundledConfigurationAgreesWithFallback() throws Exception {
        try (var input = getClass().getResourceAsStream("/config.yml")) {
            assertNotNull(input);
            final YamlConfiguration config = YamlConfiguration.loadConfiguration(
                new InputStreamReader(input, StandardCharsets.UTF_8));
            assertFalse(AddonBalancePolicy.isNerfEnabled(config));
        }
    }
}
