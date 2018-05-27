package scp002.mod.dropoff.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiScreenEventHandler {
    public static final GuiScreenEventHandler INSTANCE = new GuiScreenEventHandler();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onActionPreformed(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (!event.getButton().getClass().getName().equals("vazkii.quark.management.client.gui.GuiButtonChest") ||
                !GuiScreen.isShiftKeyDown()) {
            return;
        }

        // event.setCanceled(true);

        // ...
    }
}
