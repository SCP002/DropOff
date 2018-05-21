package scp002.mod.dropoff.util;

import java.util.Objects;

public class BlockPos {
    private final int x;
    private final int y;
    private final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "X: [" + x + "] " +
                "Y: [" + y + "] " +
                "Z: [" + z + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BlockPos)) {
            return false;
        }

        BlockPos blockPos = (BlockPos) o;

        return Objects.equals(x, blockPos.x) &&
                Objects.equals(y, blockPos.y) &&
                Objects.equals(z, blockPos.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
