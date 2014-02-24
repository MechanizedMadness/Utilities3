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

        GameRegistry.registerTileEntity(TileEntityEnergyPipe.class, "tile" + Strings.ENERGYPIPE_BLOCKNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyPipeBrain.class, "tile" + Strings.ENERGYPIPE_BRAIN_BLOCKNAME);

        energyPipeBrain = new BlockEnergyPipeBrain();
        energyPipe = new BlockEnergyPipe();

        GameRegistry.registerBlock(energyPipeBrain, Strings.ENERGYPIPE_BRAIN_BLOCKNAME);
        GameRegistry.registerBlock(energyPipe, Strings.ENERGYPIPE_BLOCKNAME);

    }

}
