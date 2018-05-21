package scp002.mod.dropoff.config;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import scp002.mod.dropoff.DropOff;

import java.util.ArrayList;
import java.util.List;

public class DropOffGuiConfig extends GuiConfig {
    public DropOffGuiConfig(GuiScreen parent) {
        super(parent, getConfigElements(), DropOff.MOD_ID, false, false,
                getAbridgedConfigPath(DropOffConfig.INSTANCE.getConfig().getConfigFile().toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();
        configElements.add(getCategoryElement(DropOffConfig.INSTANCE.categoryGeneral, "General"));
        configElements.add(getCategoryElement(DropOffConfig.INSTANCE.categoryContainers, "Containers"));

        return configElements;
    }

    private static IConfigElement getCategoryElement(String category, String name) {
        ConfigCategory configCategory = DropOffConfig.INSTANCE.getConfig().getCategory(category);

        @SuppressWarnings("unchecked")
        List<IConfigElement> configElements = new ConfigElement(configCategory).getChildElements();

        String langKey = DropOff.MOD_ID + ".config.category." + category;

        return new DummyConfigElement.DummyCategoryElement(name, langKey, configElements);
    }
}
