package scp002.mod.dropoff.gui;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import scp002.mod.dropoff.DropOff;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;

class DropOffGuiContainerCreative extends GuiContainerCreative {
    private final DropOffGuiButton dropOffGuiButton;

    DropOffGuiContainerCreative(EntityClientPlayerMP player) {
        super(player);

        dropOffGuiButton = new DropOffGuiButton();
    }

    @Override
    public void initGui() {
        super.initGui();

        int xPos = super.width / 2 + DropOffConfig.INSTANCE.creativeInventoryButtonXOffset;
        int yPos = super.height / 2 + DropOffConfig.INSTANCE.creativeInventoryButtonYOffset;

        dropOffGuiButton.xPosition = xPos;
        dropOffGuiButton.yPosition = yPos;

        //noinspection unchecked
        super.buttonList.add(dropOffGuiButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == dropOffGuiButton) {
            DropOff.NETWORK.sendToServer(MainMessage.INSTANCE);
        } else {
            super.actionPerformed(button);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (dropOffGuiButton.func_146115_a()) { // If the button is hovered by mouse.
            super.drawHoveringText(dropOffGuiButton.hoverText, mouseX, mouseY, super.fontRendererObj);
        }
    }
}
