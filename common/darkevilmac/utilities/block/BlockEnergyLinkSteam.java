package darkevilmac.utilities.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyLinkSteam;

public class BlockEnergyLinkSteam extends BlockEnergyLinkBase {

    protected BlockEnergyLinkSteam(int id) {
        super(id);
        this.setUnlocalizedName(Strings.ENERGY_LINK_STEAM_INGAMENAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyLinkSteam();
    }

}
