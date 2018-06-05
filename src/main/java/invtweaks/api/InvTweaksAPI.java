package invtweaks.api;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface InvTweaksAPI {
    int compareItems(@Nonnull ItemStack i, @Nonnull ItemStack j);
}
