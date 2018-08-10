package scp002.mod.dropoff;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scp002.mod.dropoff.config.DropOffConfig;
import scp002.mod.dropoff.message.MainMessage;
import scp002.mod.dropoff.message.ReportMessage;
import scp002.mod.dropoff.util.LogMessageFactory;

@SuppressWarnings("unused")
@Mod(modid = DropOff.MOD_ID, name = DropOff.MOD_NAME, version = DropOff.MOD_VERSION, guiFactory = DropOff.GUI_FACTORY)
public class DropOff {

    public static final String MOD_ID = "dropoff";
    public static final String MOD_NAME = "DropOff";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID, LogMessageFactory.INSTANCE);
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    static final String MOD_VERSION = "1.12.2-1.0.2b";
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
