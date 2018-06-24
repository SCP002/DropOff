package scp002.mod.dropoff.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.apache.commons.lang3.StringUtils;
import scp002.mod.dropoff.config.DropOffConfig;

public class DropOffHandler {

    private final InventoryManager inventoryManager;
    private final InventoryPlayer playerInventory;
    private final NonNullList<ItemStack> playerStacks;
    private int itemsCounter;
    private int startSlot;
    private int endSlot;

    public DropOffHandler(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.playerInventory = inventoryManager.getPlayer().inventory;
        this.playerStacks = playerInventory.mainInventory;
    }

    public void setStartSlot(int value) {
        startSlot = value;
    }

    public void setEndSlot(int value) {
        endSlot = value;
    }

    public int getItemsCounter() {
        return itemsCounter;
    }

    public void setItemsCounter(int value) {
        itemsCounter = (value < 0) ? 0 : value;
    }

    public void dropOff(InventoryData toInventoryData) {
        IInventory toInventory = toInventoryData.getInventory();

        if (endSlot == InventoryManager.Slots.LAST) {
            endSlot = toInventory.getSizeInventory();
        }

        for (int i = InventoryManager.Slots.PLAYER_INVENTORY_FIRST; i < playerStacks.size(); ++i) {
            if (!playerStacks.get(i).isEmpty() && isItemValid(playerStacks.get(i).getDisplayName())) {
                if (DropOffConfig.INSTANCE.dropOffOnlyFullStacks &&
                        playerStacks.get(i).getCount() <
                                inventoryManager.getMaxAllowedStackSize(playerInventory, playerStacks.get(i))) {
                    continue;
                }

                int oldPlayerStackSize = playerStacks.get(i).getCount();

                movePlayerStack(i, toInventory);

                int itemsMoved = oldPlayerStackSize - playerStacks.get(i).getCount();
                itemsCounter += itemsMoved;

                if (itemsMoved > 0) {
                    toInventoryData.setInteractionResult(InteractionResult.DROPOFF_SUCCESS);
                }
            }
        }
    }

    /**
     * This method checks the config text field to determine whether to DropOff the item with the specified name or not.
     */
    private boolean isItemValid(String name) {
        String[] itemNames = StringUtils.split(DropOffConfig.INSTANCE.excludeItemsWithNames,
                DropOffConfig.INSTANCE.delimiter);

        for (String itemName : itemNames) {
            String regex = itemName.replace("*", ".*").trim();

            if (name.matches(regex)) {
                return false;
            }
        }

        return true;
    }

    private void movePlayerStack(int playerStackIndex, IInventory toInventory) {
        Integer emptySlotIndex = null;
        boolean hasSameStack = false;

        for (int i = startSlot; i < endSlot; ++i) {
            ItemStack toCurrentStack = toInventory.getStackInSlot(i);

            if (toCurrentStack.isEmpty()) {
                if (emptySlotIndex == null) {
                    emptySlotIndex = i;
                }

                continue;
            }

            if (inventoryManager.isStacksEqual(toCurrentStack, playerStacks.get(playerStackIndex))) {
                hasSameStack = true;
                int toCurrentStackMaxSize = inventoryManager.getMaxAllowedStackSize(toInventory, toCurrentStack);

                if (toCurrentStack.getCount() + playerStacks.get(playerStackIndex).getCount() <=
                        toCurrentStackMaxSize) {
                    int toCurrentStackNewSize = toCurrentStack.getCount() +
                            playerStacks.get(playerStackIndex).getCount();
                    toCurrentStack.setCount(toCurrentStackNewSize);

                    playerStacks.set(playerStackIndex, ItemStack.EMPTY);

                    return;
                } else {
                    int leftToMax = toCurrentStackMaxSize - toCurrentStack.getCount();
                    int playerStacksNewSize = playerStacks.get(playerStackIndex).getCount() - leftToMax;

                    toCurrentStack.setCount(toCurrentStackMaxSize);
                    playerStacks.get(playerStackIndex).setCount(playerStacksNewSize);
                }
            }
        }

        if (hasSameStack && emptySlotIndex != null &&
                toInventory.isItemValidForSlot(emptySlotIndex, playerStacks.get(playerStackIndex))) {
            toInventory.setInventorySlotContents(emptySlotIndex, playerStacks.get(playerStackIndex));
            playerStacks.set(playerStackIndex, ItemStack.EMPTY);
        }
    }

}
