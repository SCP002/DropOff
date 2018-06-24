package scp002.mod.dropoff.task;

import net.minecraft.util.EnumChatFormatting;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.render.RendererCube;
import scp002.mod.dropoff.render.RendererCubeTarget;
import scp002.mod.dropoff.util.ClientUtils;

import java.util.List;

public class ReportTask implements Runnable {

    private final int itemsCounter;
    private final int affectedContainers;
    private final int totalContainers;
    private final List<RendererCubeTarget> rendererCubeTargets;

    public ReportTask(int itemsCounter, int affectedContainers, int totalContainers,
                      List<RendererCubeTarget> rendererCubeTargets) {
        this.itemsCounter = itemsCounter;
        this.affectedContainers = affectedContainers;
        this.totalContainers = totalContainers;
        this.rendererCubeTargets = rendererCubeTargets;
    }

    @Override
    public void run() {
        if (DropOffConfig.INSTANCE.highlightContainers) {
            RendererCube.INSTANCE.draw(rendererCubeTargets);
        }

        if (DropOffConfig.INSTANCE.displayMessage) {
            String message = String.valueOf(EnumChatFormatting.RED) +
                    itemsCounter +
                    EnumChatFormatting.RESET +
                    " items moved to " +
                    EnumChatFormatting.RED +
                    affectedContainers +
                    EnumChatFormatting.RESET +
                    " containers of " +
                    EnumChatFormatting.RED +
                    totalContainers +
                    EnumChatFormatting.RESET +
                    " checked in total.";

            ClientUtils.printToChat(message);
        }

        ClientUtils.playSound(ClientUtils.Sounds.BUTTON);
    }

}
