package scp002.mod.dropoff.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
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
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

}
