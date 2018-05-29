package scp002.mod.dropoff;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.util.ClientUtils;

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

        ClientUtils.sendNoSpectator(MainMessage.INSTANCE);
    }
}
