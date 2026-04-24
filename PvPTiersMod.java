package com.pvptiers.mod;

import com.pvptiers.mod.data.TierCache;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PvP Tiers Mod – pagrindinis įėjimo taškas (client-side only).
 *
 * Palaikomos versijos: Minecraft 1.21.5 – 1.21.1
 * Tieriai: HT1 LT1 HT2 LT2 HT3 LT3 HT4 LT4 HT5 LT5 HT6 LT6 O NE B A
 */
public class PvPTiersMod implements ClientModInitializer {

    public static final String MOD_ID = "pvptiers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("PvP Tiers Mod įkeltas! Tieriai: HT1→LT6 O NE B A");

        // Išvalome cache kai paliekame serverį
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            TierCache.clear();
            LOGGER.info("Tier cache išvalytas (atsijungta nuo serverio)");
        });
    }
}
