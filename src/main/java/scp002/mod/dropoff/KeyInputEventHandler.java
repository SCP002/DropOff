package scp002.mod.dropoff;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import scp002.mod.dropoff.message.MainMessage;

public class KeyInputEventHandler {

    static final KeyInputEventHandler INSTANCE = new KeyInputEventHandler();

    final KeyBinding mainTaskKeyBinding;

    private KeyInputEventHandler() {
        mainTaskKeyBinding = new KeyBinding(DropOff.MOD_NAME, Keyboard.KEY_X, DropOff.MOD_NAME);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!mainTaskKeyBinding.isPressed()) {
            return;
        }

        DropOff.NETWORK.sendToServer(MainMessage.INSTANCE);
    }

}
