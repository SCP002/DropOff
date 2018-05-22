package scp002.mod.dropoff.gui;

import net.minecraft.client.audio.SoundHandler;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.util.ArrayList;
import java.util.List;

class DropOffGuiButton extends GuiButtonExt {
    final List<String> hoverText = new ArrayList<>();

    DropOffGuiButton() {
        super(0, 0, 0, 10, 10, "^");

        hoverText.add("DropOff items from the player");
        hoverText.add("inventory to the nearby containers.");
    }

    @Override
    public void playPressSound(SoundHandler soundHandler) {
        //
    }
}
