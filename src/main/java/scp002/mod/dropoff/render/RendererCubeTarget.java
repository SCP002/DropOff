package scp002.mod.dropoff.render;

import scp002.mod.dropoff.util.BlockPos;

import java.awt.*;
import java.util.Objects;

public class RendererCubeTarget {

    private final BlockPos blockPos;
    private final Color color;

    public RendererCubeTarget(BlockPos blockPos, Color color) {
        this.blockPos = blockPos;
        this.color = color;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "BlockPos: [" + blockPos.toString() + "] " +
                "Color: [" + color.toString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof RendererCubeTarget)) {
            return false;
        }

        RendererCubeTarget rendererCubeTarget = (RendererCubeTarget) o;

        return Objects.equals(blockPos, rendererCubeTarget.blockPos) &&
                Objects.equals(color, rendererCubeTarget.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockPos, color);
    }

}
