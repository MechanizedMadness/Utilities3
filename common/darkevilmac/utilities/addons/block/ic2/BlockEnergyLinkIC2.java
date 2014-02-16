package darkevilmac.utilities.addons.block.ic2;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.addons.tile.ic2.TileEntityEnergyLinkIC2;
import darkevilmac.utilities.block.base.BlockEnergyLinkBase;
import darkevilmac.utilities.lib.Strings;

public class BlockEnergyLinkIC2 extends BlockEnergyLinkBase {

    protected BlockEnergyLinkIC2(int id) {
        super(id, Strings.IC2_LINK_UNLOCALIZEDNAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnergyLinkIC2();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        blockIcon = register.registerIcon("utilities:" + Strings.IC2_LINK_UNLOCALIZEDNAME);
    }

}
