package scp002.mod.dropoff.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import scp002.mod.dropoff.DropOff;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.util.ClientUtils;

import java.io.IOException;

class DropOffGuiInventory extends GuiInventory {
    private final DropOffGuiButton dropOffGuiButton;

    DropOffGuiInventory(EntityPlayerSP player) {
        super(player);

        dropOffGuiButton = new DropOffGuiButton();
    }

    @Override
    public void initGui() {
        super.initGui();

        int xPos = super.width / 2 + DropOffConfig.INSTANCE.survivalInventoryButtonXOffset;
        int yPos = super.height / 2 + DropOffConfig.INSTANCE.survivalInventoryButtonYOffset;

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

        if (dropOffGuiButton.isMouseOver()) {
            super.drawHoveringText(dropOffGuiButton.hoverText, mouseX, mouseY, super.fontRenderer);
        }
    }
}
