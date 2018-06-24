package scp002.mod.dropoff.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import scp002.mod.dropoff.DropOff;

public class ConfigChangeEventHandler {

    public static final ConfigChangeEventHandler INSTANCE = new ConfigChangeEventHandler();

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (!event.modID.equals(DropOff.MOD_ID)) {
            return;
        }

        DropOff.LOGGER.info("Configuration changed.");
        DropOffConfig.INSTANCE.sync(false);
    }

}
