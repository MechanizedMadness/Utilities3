package darkevilmac.utilities.block;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.BlockIds;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyPipe;
import darkevilmac.utilities.tile.TileEntityEnergyPipeBrain;

public class ModBlocks {

    public static BlockEnergyPipeBrain energyPipeBrain;
    public static BlockEnergyPipe energyPipe;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyPipe.class, "tile" + Strings.ENERGYPIPE_UNLOCALIZEDNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyPipeBrain.class, "tile" + Strings.ENERGYPIPE_BRAIN_UNLOCALIZEDNAME);

        energyPipeBrain = new BlockEnergyPipeBrain(BlockIds.ENERGYPIPE_BRAIN_ID);
        energyPipe = new BlockEnergyPipe(BlockIds.ENERGYPIPE_ID);

        GameRegistry.registerBlock(energyPipeBrain, Strings.ENERGYPIPE_BRAIN_UNLOCALIZEDNAME);
        GameRegistry.registerBlock(energyPipe, Strings.ENERGYPIPE_UNLOCALIZEDNAME);

    }

}
