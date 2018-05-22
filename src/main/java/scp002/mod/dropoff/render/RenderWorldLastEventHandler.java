package scp002.mod.dropoff.render;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderWorldLastEventHandler {
    public static final RenderWorldLastEventHandler INSTANCE = new RenderWorldLastEventHandler();

    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        RendererCube.INSTANCE.tryToRender(event);
    }
}
