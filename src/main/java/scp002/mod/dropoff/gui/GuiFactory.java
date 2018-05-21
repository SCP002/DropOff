package scp002.mod.dropoff.gui;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import scp002.mod.dropoff.config.DropOffGuiConfig;

import javax.annotation.Nullable;
import java.util.Set;

@SuppressWarnings("unused")
public class GuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraft) {
        //
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return DropOffGuiConfig.class;
    }

    @Nullable
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Nullable
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }
}
