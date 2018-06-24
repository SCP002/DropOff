package scp002.mod.dropoff;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import scp002.mod.dropoff.config.ConfigChangeEventHandler;
import scp002.mod.dropoff.gui.GuiOpenEventHandler;
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
        FMLCommonHandler.instance().bus().register(KeyInputEventHandler.INSTANCE);
        FMLCommonHandler.instance().bus().register(ConfigChangeEventHandler.INSTANCE);
    }

}
