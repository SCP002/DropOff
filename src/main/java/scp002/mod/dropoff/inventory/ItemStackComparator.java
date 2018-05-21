package scp002.mod.dropoff.inventory;

import cpw.mods.fml.common.Loader;
import invtweaks.api.InvTweaksAPI;
import net.minecraft.item.ItemStack;
import scp002.mod.dropoff.DropOff;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;

class ItemStackComparator implements Comparator<ItemStack> {
    @Nullable
    private InvTweaksAPI invTweaksApi;

    ItemStackComparator() {
        if (Loader.isModLoaded("inventorytweaks")) {
            try {
                Class<? extends InvTweaksAPI> invTweaksApiClass = Class.forName("invtweaks.forge.InvTweaksMod")
                        .asSubclass(InvTweaksAPI.class);
                this.invTweaksApi = invTweaksApiClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                DropOff.LOGGER.error("Failed to instantiate Inventory Tweaks API: " + e.getMessage());
            }
        }
    }

    @Override
    public int compare(@Nonnull ItemStack left, @Nonnull ItemStack right) {
        if (invTweaksApi != null) {
            return invTweaksApi.compareItems(left, right);
        }

        return left.getDisplayName().compareToIgnoreCase(right.getDisplayName());
    }
}
