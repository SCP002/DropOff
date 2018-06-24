package scp002.mod.dropoff.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.util.ClientUtils;

public class GuiScreenEventHandler {

    public static final GuiScreenEventHandler INSTANCE = new GuiScreenEventHandler();

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onActionPreformed(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (!DropOffConfig.INSTANCE.overrideQuarkButton ||
                !(event.getGui() instanceof GuiInventory || event.getGui() instanceof GuiContainerCreative) ||
                !event.getButton().getClass().getName().equals("vazkii.quark.management.client.gui.GuiButtonChest") ||
                !GuiScreen.isShiftKeyDown()) {
            return;
        }

        event.setCanceled(true);

        ClientUtils.sendNoSpectator(MainMessage.INSTANCE);
    }

}
