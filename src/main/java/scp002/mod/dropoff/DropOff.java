package scp002.mod.dropoff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.message.ReportMessage;

@SuppressWarnings("unused")
@Mod(modid = DropOff.MOD_ID, name = DropOff.MOD_NAME, version = DropOff.MOD_VERSION, guiFactory = DropOff.GUI_FACTORY)
public class DropOff {

    public static final String MOD_ID = "dropoff";
    public static final String MOD_NAME = "DropOff";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    static final String MOD_VERSION = "1.7.10-2.8.14";
    static final String GUI_FACTORY = "scp002.mod.dropoff.gui.GuiFactory";

    private static final String SERVER_SIDE = "scp002.mod.dropoff.CommonProxy";
    private static final String CLIENT_SIDE = "scp002.mod.dropoff.ClientProxy";

    @SuppressWarnings("NullableProblems")
    @SidedProxy(serverSide = SERVER_SIDE, clientSide = CLIENT_SIDE)
    private static CommonProxy commonProxy;

    @SuppressWarnings("NullableProblems")
    @Mod.Instance
    DropOff dropOff;

    @Mod.EventHandler
    void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Beginning pre-initialization...");
        commonProxy.preInit(event);

        // Register messages.
        NETWORK.registerMessage(MainMessage.Handler.class, MainMessage.class, 0, Side.SERVER);
        NETWORK.registerMessage(ReportMessage.Handler.class, ReportMessage.class, 1, Side.CLIENT);

        // Initialize config.
        DropOffConfig.INSTANCE.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    void init(FMLInitializationEvent event) {
        LOGGER.info("Beginning initialization...");
        commonProxy.init(event);
    }

}
