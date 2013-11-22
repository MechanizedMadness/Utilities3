package darkevilmac.utilities.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyLinkIC2;

public class BlockEnergyLinkIC2 extends BlockEnergyLinkBase {

    protected BlockEnergyLinkIC2(int id) {
        super(id);
        this.setUnlocalizedName(Strings.ENERGY_LINK_IC2_INGAMENAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyLinkIC2();
    }

}
