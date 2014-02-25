package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyPipe;
import darkevilmac.utilities.tile.TileEntityEnergyPipeBrain;

public class ModBlocks {

    public static Block energyPipeBrain;
    public static Block energyPipe;
    public static Block discBurner;
    public static Block discPlayer;

    public static void init() {

        GameRegistry.registerTileEntity(TileEntityEnergyPipe.class, "tile" + Strings.ENERGYPIPE_BLOCKNAME);
        GameRegistry.registerTileEntity(TileEntityEnergyPipeBrain.class, "tile" + Strings.ENERGYPIPE_BRAIN_BLOCKNAME);

        energyPipeBrain = new BlockEnergyPipeBrain();
        energyPipe = new BlockEnergyPipe();
        discBurner = new BlockDiscBurner();
        discPlayer = new BlockDiscPlayer();

        GameRegistry.registerBlock(energyPipeBrain, Strings.ENERGYPIPE_BRAIN_BLOCKNAME);
        GameRegistry.registerBlock(energyPipe, Strings.ENERGYPIPE_BLOCKNAME);
        GameRegistry.registerBlock(discBurner, Strings.DISC_BURNER_BLOCKNAME);
        GameRegistry.registerBlock(discPlayer, Strings.DISC_PLAYER_BLOCKNAME);

    }

}
