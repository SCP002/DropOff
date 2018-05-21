package scp002.mod.dropoff.gui;

import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.audio.SoundHandler;

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
    public void func_146113_a(SoundHandler soundHandler) {
        //
    }
}
