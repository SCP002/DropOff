package scp002.mod.dropoff.inventory;

import invtweaks.api.InvTweaksAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import scp002.mod.dropoff.DropOff;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

class ItemStackComparator implements Comparator<ItemStack> {

    @Nullable
    private InvTweaksAPI invTweaksApi;
    @Nullable
    private Comparator inventorySorterComparator;
    @Nullable
    private Constructor<?> itemStackHolderConstructor;

    ItemStackComparator() {
        if (Loader.isModLoaded("inventorytweaks")) {
            try {
                Class<? extends InvTweaksAPI> invTweaksApiClass = Class.forName("invtweaks.forge.InvTweaksMod")
                        .asSubclass(InvTweaksAPI.class);
                invTweaksApi = invTweaksApiClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                DropOff.LOGGER.error("Failed to instantiate Inventory Tweaks API: " + e.getMessage());
            }
        }

        if (invTweaksApi == null && Loader.isModLoaded("inventorysorter")) {
            try {
                Class<? extends Comparator> inventorySorterComparatorClass =
                        Class.forName("cpw.mods.inventorysorter.InventoryHandler$ItemStackComparator")
                                .asSubclass(Comparator.class);
                inventorySorterComparator = inventorySorterComparatorClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                DropOff.LOGGER.error("Failed to instantiate ItemStackComparator from Inventory Sorter mod: " +
                        e.getMessage());
            }

            try {
                Class<?> itemStackHolderClass = Class.forName("cpw.mods.inventorysorter.ItemStackHolder");
                itemStackHolderConstructor = itemStackHolderClass.getConstructor(ItemStack.class);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                DropOff.LOGGER.error("Failed to instantiate ItemStackHolder from Inventory Sorter mod: " +
                        e.getMessage());
            }
        }
    }

    @Override
    public int compare(@Nonnull ItemStack left, @Nonnull ItemStack right) {
        if (invTweaksApi != null) {
            return invTweaksApi.compareItems(left, right);
        }

        if (inventorySorterComparator != null && itemStackHolderConstructor != null) {
            try {
                Object leftHolder = itemStackHolderConstructor.newInstance(left);
                Object rightHolder = itemStackHolderConstructor.newInstance(right);

                //noinspection unchecked
                return inventorySorterComparator.compare(leftHolder, rightHolder);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                DropOff.LOGGER.error("Failed to get ItemStackComparator result from Inventory Sorter mod: " +
                        e.getMessage());
            }
        }

        return left.getDisplayName().compareToIgnoreCase(right.getDisplayName());
    }

}
