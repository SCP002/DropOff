package scp002.mod.dropoff;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scp002.mod.dropoff.config.ConfigChangeEventHandler;
import scp002.mod.dropoff.gui.GuiOpenEventHandler;
import scp002.mod.dropoff.gui.GuiScreenEventHandler;
import scp002.mod.dropoff.render.RenderWorldLastEventHandler;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // Register key bindings.
        ClientRegistry.registerKeyBinding(KeyInputEventHandler.INSTANCE.mainTaskKeyBinding);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Register event handlers.
        MinecraftForge.EVENT_BUS.register(RenderWorldLastEventHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(GuiOpenEventHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(GuiScreenEventHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(KeyInputEventHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ConfigChangeEventHandler.INSTANCE);
    }
}
