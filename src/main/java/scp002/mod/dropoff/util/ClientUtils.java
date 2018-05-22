package scp002.mod.dropoff.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import scp002.mod.dropoff.DropOff;

public class ClientUtils {
    public static void printToChat(String message) {
        message = "[" + TextFormatting.BLUE + DropOff.MOD_NAME + TextFormatting.RESET + "]: " + message;

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        TextComponentString textComponentString = new TextComponentString(message);

        player.addChatMessage(textComponentString);
    }

    public static void playSound(SoundEvent soundEvent) {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        PositionedSoundRecord record = PositionedSoundRecord.getMasterRecord(soundEvent, 1.0f);

        soundHandler.playSound(record);
    }
}
