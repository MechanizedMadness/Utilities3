package darkevilmac.utilities.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import darkevilmac.utilities.tile.TileEntityEnergySolidifier;

public class GuiEnergySolidifier extends GuiContainer {

    public static ResourceLocation texture = new ResourceLocation("utilities3:textures/gui/energySolidifierGui.png");
    public TileEntityEnergySolidifier tile;
    public int textureCornerTopLeftX;
    public int textureCornerTopLeftY;

    public GuiEnergySolidifier(InventoryPlayer invPlayer, TileEntityEnergySolidifier entity) {
        super(new ContainerEnergySolidifier(invPlayer, entity));

        tile = entity;

        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        drawCenteredString(mc.fontRenderer, "Fluid Energy " + tile.getClientDisplayEnergy() + "mB", textureCornerTopLeftX + 0, textureCornerTopLeftY + 25, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        textureCornerTopLeftX = (width - xSize) / 2;
        textureCornerTopLeftY = (height - ySize) / 2;

    }

}
