package scp002.mod.dropoff.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import scp002.mod.dropoff.config.DropOffConfig;

public class GuiOpenEventHandler {

    public static final GuiOpenEventHandler INSTANCE = new GuiOpenEventHandler();

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (!DropOffConfig.INSTANCE.showInventoryButton ||
                !(event.gui instanceof GuiInventory || event.gui instanceof GuiContainerCreative)) {
            return;
        }

        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        if (player.capabilities.isCreativeMode) {
            event.gui = new DropOffGuiContainerCreative(player);
        } else {
            event.gui = new DropOffGuiInventory(player);
        }
    }

}
