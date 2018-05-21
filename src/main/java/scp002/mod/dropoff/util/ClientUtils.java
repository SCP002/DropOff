package scp002.mod.dropoff.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import scp002.mod.dropoff.DropOff;

public class ClientUtils {
    public static void printToChat(String message) {
        message = "[" + EnumChatFormatting.BLUE + DropOff.MOD_NAME + EnumChatFormatting.RESET + "]: " + message;

        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        ChatComponentText chatComponentText = new ChatComponentText(message);

        player.addChatMessage(chatComponentText);
    }

    public static void playSound(String sound) {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        ResourceLocation resourceLocation = new ResourceLocation(sound);
        PositionedSoundRecord record = PositionedSoundRecord.func_147674_a(resourceLocation, 1.0f);

        soundHandler.playSound(record);
    }

    public abstract static class Sounds {
        public static final String BUTTON = "gui.button.press";
    }
}
