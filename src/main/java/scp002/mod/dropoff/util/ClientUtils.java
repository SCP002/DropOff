package scp002.mod.dropoff.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import scp002.mod.dropoff.DropOff;

public class ClientUtils {
    public static void printToChat(String message) {
        message = "[" + TextFormatting.BLUE + DropOff.MOD_NAME + TextFormatting.RESET + "]: " + message;

        EntityPlayerSP player = Minecraft.getMinecraft().player;
        TextComponentString textComponentString = new TextComponentString(message);

        player.sendMessage(textComponentString);
    }

    public static void playSound(SoundEvent soundEvent) {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        PositionedSoundRecord record = PositionedSoundRecord.getMasterRecord(soundEvent, 1.0f);

        soundHandler.playSound(record);
    }

    public static void sendNoSpectator(IMessage message) {
        if (Minecraft.getMinecraft().player.isSpectator()) {
            printToChat("Action do not allowed in spectator mode.");
        } else {
            DropOff.NETWORK.sendToServer(message);
        }
    }
}
