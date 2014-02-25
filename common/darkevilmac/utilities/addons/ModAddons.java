package darkevilmac.utilities.addons;

import cpw.mods.fml.common.Loader;
import darkevilmac.utilities.addons.block.bc.ModBlocksBC;
import darkevilmac.utilities.addons.block.ic2.ModBlocksIC2;
import darkevilmac.utilities.addons.block.railcraft.ModBlocksRailcraft;
import darkevilmac.utilities.addons.crafting.bc.CraftingBC;
import darkevilmac.utilities.addons.crafting.ic2.CraftingIC2;
import darkevilmac.utilities.addons.crafting.railcraft.CraftingRailcraft;

public class ModAddons {

    public static void initBlocks() {
        ModBlocksRailcraft.init();
        ModBlocksBC.init();
        ModBlocksIC2.init();
    }

    public static void initCrafting() {
        if (Loader.isModLoaded("Railcraft")) {
            CraftingRailcraft.init();
        }
        if (Loader.isModLoaded("BuildCraft|Energy")) {
            CraftingBC.init();
        }
        if (Loader.isModLoaded("IC2")) {
            CraftingIC2.init();
        }
    }

}
