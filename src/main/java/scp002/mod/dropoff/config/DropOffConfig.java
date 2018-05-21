package scp002.mod.dropoff.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import scp002.mod.dropoff.DropOff;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;

public class DropOffConfig {
    public static final DropOffConfig INSTANCE = new DropOffConfig();

    public final String delimiter = DefaultValues.delimiter;

    final String categoryContainers = "containers";
    final String categoryGeneral = "general";

    private final List<String> propertyOrder = new ArrayList<>();
    private final Map<String, Set<String>> categoryToPropertyKeySet = new HashMap<>();

    public boolean checkBeacons = DefaultValues.checkBeacons;
    public boolean checkBrewingStands = DefaultValues.checkBrewingStands;
    public boolean checkChests = DefaultValues.checkChests;
    public boolean checkDispensers = DefaultValues.checkDispensers;
    public boolean checkDroppers = DefaultValues.checkDroppers;
    public boolean checkEnderChests = DefaultValues.checkEnderChests;
    public boolean checkFurnaces = DefaultValues.checkFurnaces;
    public boolean checkHoppers = DefaultValues.checkHoppers;
    public boolean displayMessage = DefaultValues.displayMessage;
    public boolean dropOff = DefaultValues.dropOff;
    public boolean dropOffEveryPlace = DefaultValues.dropOffEveryPlace;
    public boolean dropOffOnlyFullStacks = DefaultValues.dropOffOnlyFullStacks;
    public boolean highlightContainers = DefaultValues.highlightContainers;
    public boolean showInventoryButton = DefaultValues.showInventoryButton;
    public boolean sortContainers = DefaultValues.sortContainers;
    public boolean sortPlayerInventory = DefaultValues.sortPlayerInventory;

    public int creativeInventoryButtonXOffset = DefaultValues.creativeInventoryButtonXOffset;
    public int creativeInventoryButtonYOffset = DefaultValues.creativeInventoryButtonYOffset;
    public int highlightDelay = DefaultValues.highlightDelay;
    public int scanRadius = DefaultValues.scanRadius;
    public int survivalInventoryButtonXOffset = DefaultValues.survivalInventoryButtonXOffset;
    public int survivalInventoryButtonYOffset = DefaultValues.survivalInventoryButtonYOffset;

    public String excludeItemsWithNames = DefaultValues.excludeItemsWithNames;
    public String processContainersWithNames = DefaultValues.processContainersWithNames;
    public String sortContainersWithNames = DefaultValues.sortContainersWithNames;

    @Nullable
    private Configuration config;

    private DropOffConfig() {
        //
    }

    public void init(File file) throws IllegalStateException {
        if (config == null) {
            config = new Configuration(file);
            sync(true);
        } else {
            throw new IllegalStateException("Config already initialized.");
        }
    }

    public Configuration getConfig() throws IllegalStateException {
        if (config == null) {
            throw new IllegalStateException("Config not initialized.");
        }

        return config;
    }

    void sync(boolean load) throws IllegalStateException {
        if (config == null) {
            throw new IllegalStateException("Config not initialized.");
        }

        propertyOrder.clear();
        categoryToPropertyKeySet.clear();

        if (load) {
            config.load();
            DropOff.LOGGER.info("Loading config.");
        } else {
            DropOff.LOGGER.info("Reloading config.");
        }

        // ---------------------------------------------------General---------------------------------------------------
        Property property;

        // Booleans
        property = config.get(categoryGeneral, "Display message", DefaultValues.displayMessage);
        property.comment = "[Client-side] Print information to the chat when task is complete.";
        process(categoryGeneral, property);
        displayMessage = property.getBoolean();

        property = config.get(categoryGeneral, "DropOff", DefaultValues.dropOff);
        property.comment = "Move items from the player inventory to the nearby containers.";
        process(categoryGeneral, property);
        dropOff = property.getBoolean();

        property = config.get(categoryGeneral, "DropOff everyplace", DefaultValues.dropOffEveryPlace);
        property.comment = "Move items to all containers, even those that are not defined in the configuration.";
        process(categoryGeneral, property);
        dropOffEveryPlace = property.getBoolean();

        property = config.get(categoryGeneral, "DropOff only full stacks", DefaultValues.dropOffOnlyFullStacks);
        property.comment = "Move only full item stacks from the player inventory.";
        process(categoryGeneral, property);
        dropOffOnlyFullStacks = property.getBoolean();

        property = config.get(categoryGeneral, "Highlight containers", DefaultValues.highlightContainers);
        property.comment = "[Client-side] Highlight nearby containers.";
        process(categoryGeneral, property);
        highlightContainers = property.getBoolean();

        property = config.get(categoryGeneral, "Show inventory button", DefaultValues.showInventoryButton);
        property.comment = "[Client-side] Show button in the player inventory.";
        process(categoryGeneral, property);
        showInventoryButton = property.getBoolean();

        property = config.get(categoryGeneral, "Sort containers", DefaultValues.sortContainers);
        property.comment = "Sort nearby containers.";
        process(categoryGeneral, property);
        sortContainers = property.getBoolean();

        property = config.get(categoryGeneral, "Sort player inventory", DefaultValues.sortPlayerInventory);
        property.comment = "Sort player inventory.";
        process(categoryGeneral, property);
        sortPlayerInventory = property.getBoolean();

        // Integers
        property = config.get(categoryGeneral, "Creative inventory button X offset",
                DefaultValues.creativeInventoryButtonXOffset);
        property.comment = "[Client-side] Creative inventory button position width offset.";
        process(categoryGeneral, property);
        creativeInventoryButtonXOffset = property.getInt();

        property = config.get(categoryGeneral, "Creative inventory button Y offset",
                DefaultValues.creativeInventoryButtonYOffset);
        property.comment = "[Client-side] Creative inventory button position height offset.";
        process(categoryGeneral, property);
        creativeInventoryButtonYOffset = property.getInt();

        property = config.get(categoryGeneral, "Highlight delay", DefaultValues.highlightDelay);
        property.comment = "[Client-side] Blocks highlighting delay in milliseconds. Delay < 0 means forever.";
        process(categoryGeneral, property);
        highlightDelay = property.getInt();

        property = config.get(categoryGeneral, "Scan radius", DefaultValues.scanRadius);
        property.comment = "Radius in blocks to check containers around the player.";
        process(categoryGeneral, property);
        scanRadius = property.getInt();

        property = config.get(categoryGeneral, "Survival inventory button X offset",
                DefaultValues.survivalInventoryButtonXOffset);
        property.comment = "[Client-side] Survival inventory button position width offset.";
        process(categoryGeneral, property);
        survivalInventoryButtonXOffset = property.getInt();

        property = config.get(categoryGeneral, "Survival inventory button Y offset",
                DefaultValues.survivalInventoryButtonYOffset);
        property.comment = "[Client-side] Survival inventory button position height offset.";
        process(categoryGeneral, property);
        survivalInventoryButtonYOffset = property.getInt();

        // Strings
        property = config.get(categoryGeneral, "Exclude items with names", DefaultValues.excludeItemsWithNames);
        property.comment = "Do not move items with the following names from the player inventory. Delimiter is '" +
                delimiter + "'. Wildcards allowed.";
        process(categoryGeneral, property);
        excludeItemsWithNames = property.getString();

        config.setCategoryPropertyOrder(categoryGeneral, propertyOrder);

        // -------------------------------------------------Containers--------------------------------------------------
        // Booleans
        property = config.get(categoryContainers, "Check beacons", DefaultValues.checkBeacons);
        property.comment = "Check nearby beacons.";
        process(categoryContainers, property);
        checkBeacons = property.getBoolean();

        property = config.get(categoryContainers, "Check brewing stands", DefaultValues.checkBrewingStands);
        property.comment = "Check nearby brewing stands.";
        process(categoryContainers, property);
        checkBrewingStands = property.getBoolean();

        property = config.get(categoryContainers, "Check chests", DefaultValues.checkChests);
        property.comment = "Check nearby chests.";
        process(categoryContainers, property);
        checkChests = property.getBoolean();

        property = config.get(categoryContainers, "Check dispensers", DefaultValues.checkDispensers);
        property.comment = "Check nearby dispensers.";
        process(categoryContainers, property);
        checkDispensers = property.getBoolean();

        property = config.get(categoryContainers, "Check droppers", DefaultValues.checkDroppers);
        property.comment = "Check nearby droppers.";
        process(categoryContainers, property);
        checkDroppers = property.getBoolean();

        property = config.get(categoryContainers, "Check ender chests", DefaultValues.checkEnderChests);
        property.comment = "Check nearby ender chests.";
        process(categoryContainers, property);
        checkEnderChests = property.getBoolean();

        property = config.get(categoryContainers, "Check furnaces", DefaultValues.checkFurnaces);
        property.comment = "Check nearby furnaces.";
        process(categoryContainers, property);
        checkFurnaces = property.getBoolean();

        property = config.get(categoryContainers, "Check hoppers", DefaultValues.checkHoppers);
        property.comment = "Check nearby hoppers.";
        process(categoryContainers, property);
        checkHoppers = property.getBoolean();

        // Strings
        property = config.get(categoryContainers, "Process containers with names",
                DefaultValues.processContainersWithNames);
        property.comment = "Try to manipulate containers with the following names. Delimiter is '" + delimiter +
                "'. Wildcards allowed.";
        process(categoryContainers, property);
        processContainersWithNames = property.getString();

        property = config.get(categoryContainers, "Sort containers with names",
                DefaultValues.sortContainersWithNames);
        property.comment = "Sort containers with the following names. Delimiter is '" + delimiter +
                "'. Wildcards allowed.";
        process(categoryContainers, property);
        sortContainersWithNames = property.getString();

        config.setCategoryPropertyOrder(categoryContainers, propertyOrder);

        // -------------------------------------------Removing unknown entries------------------------------------------
        for (String categoryName : config.getCategoryNames()) {
            ConfigCategory category = config.getCategory(categoryName);
            Set<String> knownKeys = categoryToPropertyKeySet.get(categoryName);

            if (knownKeys == null) {
                config.removeCategory(category);
                DropOff.LOGGER.info("Removed unknown config category: " + categoryName);
            } else {
                for (Iterator<String> iterator = category.keySet().iterator(); iterator.hasNext(); /**/) {
                    String propertyKey = iterator.next();

                    if (!knownKeys.contains(propertyKey)) {
                        iterator.remove();
                        DropOff.LOGGER.info("Removed unknown config property: " + propertyKey);
                    }
                }
            }
        }

        if (config.hasChanged()) {
            config.save();
        }
    }

    private void process(String category, Property property) {
        setLangKey(category, property);

        propertyOrder.add(property.getName());

        Set<String> propKeys = categoryToPropertyKeySet.get(category);

        if (propKeys == null) {
            propKeys = new HashSet<>();
        }

        propKeys.add(property.getName());
        categoryToPropertyKeySet.put(category, propKeys);
    }

    private void setLangKey(String category, Property property) {
        StringBuilder builder = new StringBuilder();
        builder.append(DropOff.MOD_ID);
        builder.append(".config.");

        category = category.substring(category.indexOf("_") + 1).replace("_", ".");
        builder.append(category);

        String propertyName = property.getName();
        builder.append(".");
        builder.append(propertyName);

        property.setLanguageKey(builder.toString());
    }

    private abstract class DefaultValues {
        private static final boolean checkBeacons = true;
        private static final boolean checkBrewingStands = true;
        private static final boolean checkChests = true;
        private static final boolean checkDispensers = true;
        private static final boolean checkDroppers = true;
        private static final boolean checkEnderChests = true;
        private static final boolean checkFurnaces = true;
        private static final boolean checkHoppers = true;
        private static final boolean displayMessage = true;
        private static final boolean dropOff = true;
        private static final boolean dropOffEveryPlace = false;
        private static final boolean dropOffOnlyFullStacks = false;
        private static final boolean highlightContainers = true;
        private static final boolean showInventoryButton = true;
        private static final boolean sortContainers = true;
        private static final boolean sortPlayerInventory = true;

        private static final int creativeInventoryButtonXOffset = 79;
        private static final int creativeInventoryButtonYOffset = -63;
        private static final int highlightDelay = 3000;
        private static final int scanRadius = 4;
        private static final int survivalInventoryButtonXOffset = 71;
        private static final int survivalInventoryButtonYOffset = -14;

        private static final String delimiter = ",";
        private static final String excludeItemsWithNames = "Item Name" + delimiter + "Item*Names";
        private static final String processContainersWithNames = "*Barrel*" + delimiter + "*Chest*" + delimiter +
                "*Drawer*";
        private static final String sortContainersWithNames = "*Chest*";
    }
}
