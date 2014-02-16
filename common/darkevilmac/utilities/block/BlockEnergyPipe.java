package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyPipe;

public class BlockEnergyPipe extends BlockUtilitiesContainer {

    public BlockEnergyPipe(int id) {
        super(id, Material.iron);
        setUnlocalizedName(Strings.ENERGYPIPE_UNLOCALIZEDNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyPipe();
    }

}
