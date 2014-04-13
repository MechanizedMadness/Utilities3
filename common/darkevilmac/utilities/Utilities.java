package darkevilmac.utilities;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import darkevilmac.utilities.addons.ModAddons;
import darkevilmac.utilities.block.ModBlocks;
import darkevilmac.utilities.configuration.UtilitiesConfiguration;
import darkevilmac.utilities.crafting.ModCrafting;
import darkevilmac.utilities.events.ModEvents;
import darkevilmac.utilities.fluid.ModFluids;
import darkevilmac.utilities.gui.GUIHandler;
import darkevilmac.utilities.item.ModItems;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.network.packet.PacketPipeline;
import darkevilmac.utilities.proxy.CommonProxy;
import darkevilmac.utilities.tabs.CreativeTabsUtilities3;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.version)
public class Utilities {

    public static CreativeTabs modTab = new CreativeTabsUtilities3("utilities3Tab");

    @Instance(value = Reference.MOD_ID)
    public static Utilities instance;

    @SidedProxy(clientSide = "darkevilmac.utilities.proxy.ClientProxy", serverSide = "darkevilmac.utilities.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static Configuration config;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Utilities.config = new Configuration(event.getSuggestedConfigurationFile());
        UtilitiesConfiguration.init();

        ModItems.init();
        ModBlocks.init();
        ModFluids.init();
        ModEvents.init();
        ModAddons.initBlocks();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        ModCrafting.init();
        ModAddons.initCrafting();
        proxy.registerFluidIcons();
        packetPipeline.initalise();

    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
    }

}
