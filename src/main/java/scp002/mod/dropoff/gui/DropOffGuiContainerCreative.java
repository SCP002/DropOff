package scp002.mod.dropoff.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;
import scp002.mod.dropoff.DropOff;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.util.ClientUtils;

import java.io.IOException;

class DropOffGuiContainerCreative extends GuiContainerCreative {
    private final DropOffGuiButton dropOffGuiButton;

    DropOffGuiContainerCreative(EntityPlayerSP player) {
        super(player);

        dropOffGuiButton = new DropOffGuiButton();
    }

    @Override
    public void initGui() {
        super.initGui();

        int xPos = super.width / 2 + DropOffConfig.INSTANCE.creativeInventoryButtonXOffset;
        int yPos = super.height / 2 + DropOffConfig.INSTANCE.creativeInventoryButtonYOffset;

        dropOffGuiButton.x = xPos;
        dropOffGuiButton.y = yPos;

        super.buttonList.add(dropOffGuiButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == dropOffGuiButton) {
            ClientUtils.sendNoSpectator(MainMessage.INSTANCE);
        } else {
            try {
                super.actionPerformed(button);
            } catch (IOException e) {
                DropOff.LOGGER.error("Can not perform button action: " + e.getMessage());
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        dropOffGuiButton.visible = super.getSelectedTabIndex() == CreativeTabs.INVENTORY.getTabIndex();

        if (dropOffGuiButton.isMouseOver()) {
            super.drawHoveringText(dropOffGuiButton.hoverText, mouseX, mouseY, super.fontRenderer);
        }
    }
}
