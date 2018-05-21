package scp002.mod.dropoff.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class RenderWorldLastEventHandler {
    public static final RenderWorldLastEventHandler INSTANCE = new RenderWorldLastEventHandler();

    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        RendererCube.INSTANCE.tryToRender(event);
    }
}
