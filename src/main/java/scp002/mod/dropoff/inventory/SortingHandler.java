package scp002.mod.dropoff.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;
import scp002.mod.dropoff.config.DropOffConfig;

import java.util.ArrayList;
import java.util.List;

public class SortingHandler {

    private final InventoryManager inventoryManager;
    private final ItemStackComparator itemStackComparator;
    private int startSlot;
    private int endSlot;

    public SortingHandler(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        itemStackComparator = new ItemStackComparator();
    }

    public void setStartSlot(int value) {
        startSlot = value;
    }

    public void setEndSlot(int value) {
        endSlot = value;
    }

    public void sort(IInventory inventory) {
        if (endSlot < InventoryManager.Slots.FIRST) {
            endSlot = inventory.getSizeInventory();
        }

        mergeStacks(inventory);

        List<ItemStack> stacks = new ArrayList<>();

        for (int i = startSlot; i < endSlot; ++i) {
            ItemStack currentStack = inventory.getStackInSlot(i);

            if (currentStack != null) {
                stacks.add(currentStack);
            }

            inventory.setInventorySlotContents(i, null);
        }

        stacks.sort(itemStackComparator);

        populateInventory(inventory, stacks);
    }

    /**
     * This method checks the config text field to determine whether to sort the inventory with the specified name
     * or not.
     */
    public boolean isSortRequired(IInventory inventory) {
        String name = inventoryManager.getItemStackName(inventory);

        String[] containerNames =
                StringUtils.split(DropOffConfig.INSTANCE.sortContainersWithNames, DropOffConfig.INSTANCE.delimiter);

        for (String containerName : containerNames) {
            String regex = containerName.replace("*", ".*").trim();

            if (name.matches(regex)) {
                return true;
            }
        }

        return false;
    }

    private void populateInventory(IInventory inventory, List<ItemStack> stacks) {
        for (int i = startSlot, j = 0; i < endSlot; ++i, ++j) {
            if (j < stacks.size()) {
                ItemStack currentStack = stacks.get(j);

                inventory.setInventorySlotContents(i, currentStack);
            }
        }
    }

    private void mergeStacks(IInventory inventory) {
        for (int i = startSlot; i < endSlot; ++i) {
            ItemStack leftStack = inventory.getStackInSlot(i);

            if (leftStack == null) {
                continue;
            }

            for (int j = i + 1; j < endSlot; ++j) {
                ItemStack rightStack = inventory.getStackInSlot(j);

                if (rightStack == null) {
                    continue;
                }

                if (inventoryManager.isStacksEqual(leftStack, rightStack)) {
                    int maxSize = inventoryManager.getMaxAllowedStackSize(inventory, leftStack);

                    if (leftStack.stackSize + rightStack.stackSize <= maxSize) {
                        leftStack.stackSize += rightStack.stackSize;

                        inventory.setInventorySlotContents(j, null);
                    } else {
                        int leftToMax = maxSize - leftStack.stackSize;

                        leftStack.stackSize = maxSize;
                        rightStack.stackSize -= leftToMax;
                    }
                }
            }
        }
    }

}
