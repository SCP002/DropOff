package scp002.mod.dropoff.util;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import scp002.mod.dropoff.render.RendererCubeTarget;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ByteBufUtilsExt extends ByteBufUtils {

    private final ByteBuf buf;

    public ByteBufUtilsExt(ByteBuf buf) {
        this.buf = buf;
    }

    public void writeRendererCubeTargets(List<RendererCubeTarget> rendererCubeTargets) {
        buf.writeInt(rendererCubeTargets.size());

        for (RendererCubeTarget rendererCubeTarget : rendererCubeTargets) {
            writeRendererCubeTarget(rendererCubeTarget);
        }
    }

    public List<RendererCubeTarget> readRendererCubeTargets() {
        List<RendererCubeTarget> rendererCubeTargets = new ArrayList<>();
        int targetsLen = buf.readInt();

        for (int i = 0; i < targetsLen; ++i) {
            rendererCubeTargets.add(readRendererCubeTarget());
        }

        return rendererCubeTargets;
    }

    private void writeRendererCubeTarget(RendererCubeTarget rendererCubeTarget) {
        writeBlockPos(rendererCubeTarget.getBlockPos());
        writeColor(rendererCubeTarget.getColor());
    }

    private RendererCubeTarget readRendererCubeTarget() {
        BlockPos blockPos = readBlockPos();
        Color color = readColor();

        return new RendererCubeTarget(blockPos, color);
    }

    private void writeBlockPos(BlockPos blockPos) {
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
    }

    private BlockPos readBlockPos() {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        return new BlockPos(x, y, z);
    }

    private void writeColor(Color color) {
        buf.writeInt(color.getRed());
        buf.writeInt(color.getGreen());
        buf.writeInt(color.getBlue());
    }

    private Color readColor() {
        int red = buf.readInt();
        int green = buf.readInt();
        int blue = buf.readInt();

        return new Color(red, green, blue);
    }

}
