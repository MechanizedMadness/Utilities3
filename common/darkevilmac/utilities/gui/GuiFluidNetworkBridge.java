package darkevilmac.utilities.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class GuiFluidNetworkBridge extends GuiContainer {

    public static ResourceLocation texture = new ResourceLocation("textures/gui/fluidNetworkBridgeGui.png");
    public ContainerFluidNetworkBridge container;

    public GuiFluidNetworkBridge(InventoryPlayer invPlayer, TileEntityFluidNetworkBridge entity) {
        super(new ContainerFluidNetworkBridge(invPlayer, entity));
    
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        // Row 1
        if (y >= 18 && y <= 36) {
            if (x >= 27 && x <= 42) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(0).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 45 && x <= 60) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(1).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 63 && x <= 78) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(2).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 81 && x <= 96) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(3).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 99 && x <= 114) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(4).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 117 && x <= 132) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(5).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 135 && x <= 150) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(6).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
        }

        // Row 2
        if (y >= 36 && y <= 51) {
            if (x >= 27 && x <= 42) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(7).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 45 && x <= 60) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(8).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 63 && x <= 78) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(9).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 81 && x <= 96) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(10).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 99 && x <= 114) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(11).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 117 && x <= 132) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(12).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
            if (x >= 135 && x <= 150) {
                List fluidName = new ArrayList();
                fluidName.add(container.tile.fluidFilters.get(13).getLocalizedName());
                drawHoveringText(fluidName, x, y, mc.fontRenderer);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);

        // Draw Fluids in slots

        // Row 1
        drawTexturedModelRectFromIcon(27, 18, container
                .tile
                .fluidFilters
                .get(0)
                .getIcon()
                , 16, 16);
        drawTexturedModelRectFromIcon(45, 18, container.tile.fluidFilters.get(1).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(63, 18, container.tile.fluidFilters.get(2).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(81, 18, container.tile.fluidFilters.get(3).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(99, 18, container.tile.fluidFilters.get(4).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(117, 18, container.tile.fluidFilters.get(5).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(135, 18, container.tile.fluidFilters.get(6).getIcon(), 16, 16);

        // Row 2
        drawTexturedModelRectFromIcon(27, 36, container.tile.fluidFilters.get(7).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(45, 36, container.tile.fluidFilters.get(8).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(63, 36, container.tile.fluidFilters.get(9).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(81, 36, container.tile.fluidFilters.get(10).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(99, 36, container.tile.fluidFilters.get(11).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(117, 36, container.tile.fluidFilters.get(12).getIcon(), 16, 16);
        drawTexturedModelRectFromIcon(135, 36, container.tile.fluidFilters.get(13).getIcon(), 16, 16);
    }

    @Override
    protected void mouseClicked(int x, int y, int par3) {
        super.mouseClicked(x, y, par3);

        // Row 1
        if (y >= 18 && y <= 36) {
            if (x >= 27 && x <= 42) {
                if (container.tile.fluidFilters.get(0).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(0, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(0, FluidRegistry.getFluid(container.tile.fluidFilters.get(0).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 45 && x <= 60) {
                if (container.tile.fluidFilters.get(1).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(1, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(1, FluidRegistry.getFluid(container.tile.fluidFilters.get(1).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 63 && x <= 78) {
                if (container.tile.fluidFilters.get(2).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(2, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(2, FluidRegistry.getFluid(container.tile.fluidFilters.get(2).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 81 && x <= 96) {
                if (container.tile.fluidFilters.get(3).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(3, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(3, FluidRegistry.getFluid(container.tile.fluidFilters.get(3).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 99 && x <= 114) {
                if (container.tile.fluidFilters.get(4).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(4, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(4, FluidRegistry.getFluid(container.tile.fluidFilters.get(4).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 117 && x <= 132) {
                if (container.tile.fluidFilters.get(5).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(5, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(5, FluidRegistry.getFluid(container.tile.fluidFilters.get(5).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 135 && x <= 150) {
                if (container.tile.fluidFilters.get(6).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(6, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(6, FluidRegistry.getFluid(container.tile.fluidFilters.get(6).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }

        // Row 2
        if (y >= 36 && y <= 51) {
            if (x >= 27 && x <= 42) {
                if (container.tile.fluidFilters.get(7).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(7, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(7, FluidRegistry.getFluid(container.tile.fluidFilters.get(7).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 45 && x <= 60) {
                if (container.tile.fluidFilters.get(8).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(8, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(8, FluidRegistry.getFluid(container.tile.fluidFilters.get(8).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 63 && x <= 78) {
                if (container.tile.fluidFilters.get(9).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(9, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(9, FluidRegistry.getFluid(container.tile.fluidFilters.get(9).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 81 && x <= 96) {
                if (container.tile.fluidFilters.get(10).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(10, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(10, FluidRegistry.getFluid(container.tile.fluidFilters.get(10).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 99 && x <= 114) {
                if (container.tile.fluidFilters.get(1).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(11, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(11, FluidRegistry.getFluid(container.tile.fluidFilters.get(11).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 117 && x <= 132) {
                if (container.tile.fluidFilters.get(12).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(12, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(12, FluidRegistry.getFluid(container.tile.fluidFilters.get(12).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= 135 && x <= 150) {
                if (container.tile.fluidFilters.get(13).getID() == FluidRegistry.getMaxID()) {
                    container.tile.fluidFilters.set(13, FluidRegistry.getFluid(1));
                } else {
                    container.tile.fluidFilters.set(13, FluidRegistry.getFluid(container.tile.fluidFilters.get(13).getID() + 1));
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }
    }

}
