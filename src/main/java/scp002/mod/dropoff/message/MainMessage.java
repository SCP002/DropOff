package scp002.mod.dropoff.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import scp002.mod.dropoff.inventory.InteractionResult;
import scp002.mod.dropoff.inventory.InventoryData;
import scp002.mod.dropoff.render.RendererCubeTarget;
import scp002.mod.dropoff.task.MainTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMessage implements IMessage {

    public static final MainMessage INSTANCE = new MainMessage();

    /**
     * Leave public default constructor for Netty.
     */
    @SuppressWarnings("WeakerAccess")
    public MainMessage() {
        //
    }

    @Override
    public void toBytes(ByteBuf buf) {
        //
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        //
    }

    public static class Handler implements IMessageHandler<MainMessage, IMessage> {

        @Nullable
        private MainTask mainTask;

        @Override
        public IMessage onMessage(MainMessage message, @Nonnull MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;

            if (mainTask == null || !player.isEntityEqual(mainTask.getPlayer())) {
                mainTask = new MainTask(player);
            }

            mainTask.run();

            List<InventoryData> inventoryDataList = mainTask.getInventoryDataList();
            List<RendererCubeTarget> rendererCubeTargets = new ArrayList<>();
            int affectedContainers = 0;

            for (InventoryData inventoryData : inventoryDataList) {
                Color color;

                if (inventoryData.getInteractionResult() == InteractionResult.DROPOFF_SUCCESS) {
                    ++affectedContainers;
                    color = new Color(0, 255, 0);
                } else {
                    color = new Color(255, 0, 0);
                }

                for (TileEntity entity : inventoryData.getEntities()) {
                    RendererCubeTarget rendererCubeTarget = new RendererCubeTarget(entity.getPos(), color);

                    rendererCubeTargets.add(rendererCubeTarget);
                }
            }

            return new ReportMessage(mainTask.getItemsCounter(), affectedContainers, inventoryDataList.size(),
                    rendererCubeTargets);
        }

    }

}
