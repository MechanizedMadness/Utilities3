package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyPipeBrain;

public class BlockEnergyPipeBrain extends BlockUtilitiesContainer {

    public BlockEnergyPipeBrain(int id) {
        super(id, Material.iron);
        setUnlocalizedName(Strings.ENERGYPIPE_BRAIN_UNLOCALIZEDNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyPipeBrain();
    }

}
