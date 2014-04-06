package darkevilmac.utilities.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import darkevilmac.utilities.tile.TileEntityFluidNetworkBridge;

public class GuiFluidNetworkBridge extends GuiContainer {

    public static ResourceLocation texture = new ResourceLocation("utilities3:textures/gui/fluidNetworkBridgeGui.png");
    public TileEntityFluidNetworkBridge tile;
    public int textureCornerTopLeftX;
    public int textureCornerTopLeftY;

    public GuiFluidNetworkBridge(InventoryPlayer invPlayer, TileEntityFluidNetworkBridge entity) {
        super(new ContainerFluidNetworkBridge(invPlayer, entity));

        tile = entity;

        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        // Row 1
        if (y >= textureCornerTopLeftY + 18 && y <= textureCornerTopLeftY + 36) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(0).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(1).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(2).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(3).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(4).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(5).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(6).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
        }

        // Row 2
        if (y >= textureCornerTopLeftY + 36 && y <= textureCornerTopLeftY + 51) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(7).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(8).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(9).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(10).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(11).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(12).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                List fluidName = new ArrayList();
                fluidName.add(tile.fluidFilters.get(13).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
        }

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

        mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);

        // Row 1
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 26, textureCornerTopLeftY + 17, tile.fluidFilters.get(0).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 44, textureCornerTopLeftY + 17, tile.fluidFilters.get(1).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 62, textureCornerTopLeftY + 17, tile.fluidFilters.get(2).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 80, textureCornerTopLeftY + 17, tile.fluidFilters.get(3).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 98, textureCornerTopLeftY + 17, tile.fluidFilters.get(4).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 116, textureCornerTopLeftY + 17, tile.fluidFilters.get(5).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 134, textureCornerTopLeftY + 17, tile.fluidFilters.get(6).getStillIcon(), 16, 16);

        // Row 2
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 26, textureCornerTopLeftY + 35, tile.fluidFilters.get(7).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 44, textureCornerTopLeftY + 35, tile.fluidFilters.get(8).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 62, textureCornerTopLeftY + 35, tile.fluidFilters.get(9).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 80, textureCornerTopLeftY + 35, tile.fluidFilters.get(10).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 98, textureCornerTopLeftY + 35, tile.fluidFilters.get(11).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 116, textureCornerTopLeftY + 35, tile.fluidFilters.get(12).getStillIcon(), 16, 16);
        drawTexturedModelRectFromIcon(textureCornerTopLeftX + 134, textureCornerTopLeftY + 35, tile.fluidFilters.get(13).getStillIcon(), 16, 16);
    }

    @Override
    protected void mouseClicked(int x, int y, int clickType) {
        super.mouseClicked(x, y, clickType);

        // Row 1
        if (y >= textureCornerTopLeftY + 18 && y <= textureCornerTopLeftY + 36) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(0).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(0, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(0, FluidRegistry.getFluid(tile.fluidFilters.get(0).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(0).getID() == 1) {
                        tile.fluidFilters.set(0, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(0, FluidRegistry.getFluid(tile.fluidFilters.get(0).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(1).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(1, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(1, FluidRegistry.getFluid(tile.fluidFilters.get(1).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(1).getID() == 1) {
                        tile.fluidFilters.set(1, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(1, FluidRegistry.getFluid(tile.fluidFilters.get(1).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(2).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(2, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(2, FluidRegistry.getFluid(tile.fluidFilters.get(2).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(2).getID() == 1) {
                        tile.fluidFilters.set(2, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(2, FluidRegistry.getFluid(tile.fluidFilters.get(2).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(3).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(3, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(3, FluidRegistry.getFluid(tile.fluidFilters.get(3).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(3).getID() == 1) {
                        tile.fluidFilters.set(3, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(3, FluidRegistry.getFluid(tile.fluidFilters.get(3).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(4).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(4, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(4, FluidRegistry.getFluid(tile.fluidFilters.get(4).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(4).getID() == 1) {
                        tile.fluidFilters.set(4, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(4, FluidRegistry.getFluid(tile.fluidFilters.get(4).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(5).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(5, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(5, FluidRegistry.getFluid(tile.fluidFilters.get(5).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(5).getID() == 1) {
                        tile.fluidFilters.set(5, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(5, FluidRegistry.getFluid(tile.fluidFilters.get(5).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(6).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(6, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(6, FluidRegistry.getFluid(tile.fluidFilters.get(6).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(6).getID() == 1) {
                        tile.fluidFilters.set(6, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(6, FluidRegistry.getFluid(tile.fluidFilters.get(6).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }

        // Row 2
        if (y >= textureCornerTopLeftY + 36 && y <= textureCornerTopLeftY + 51) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(7).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(7, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(7, FluidRegistry.getFluid(tile.fluidFilters.get(7).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(7).getID() == 1) {
                        tile.fluidFilters.set(7, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(7, FluidRegistry.getFluid(tile.fluidFilters.get(7).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(8).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(8, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(8, FluidRegistry.getFluid(tile.fluidFilters.get(8).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(8).getID() == 1) {
                        tile.fluidFilters.set(8, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(8, FluidRegistry.getFluid(tile.fluidFilters.get(8).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(9).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(9, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(9, FluidRegistry.getFluid(tile.fluidFilters.get(9).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(9).getID() == 1) {
                        tile.fluidFilters.set(9, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(9, FluidRegistry.getFluid(tile.fluidFilters.get(9).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(10).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(10, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(10, FluidRegistry.getFluid(tile.fluidFilters.get(10).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(10).getID() == 1) {
                        tile.fluidFilters.set(10, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(10, FluidRegistry.getFluid(tile.fluidFilters.get(10).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(11).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(11, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(11, FluidRegistry.getFluid(tile.fluidFilters.get(11).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(11).getID() == 1) {
                        tile.fluidFilters.set(11, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(11, FluidRegistry.getFluid(tile.fluidFilters.get(11).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(12).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(12, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(12, FluidRegistry.getFluid(tile.fluidFilters.get(12).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(12).getID() == 1) {
                        tile.fluidFilters.set(12, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(12, FluidRegistry.getFluid(tile.fluidFilters.get(12).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                if (clickType == 0) {
                    if (tile.fluidFilters.get(13).getID() == FluidRegistry.getMaxID()) {
                        tile.fluidFilters.set(13, FluidRegistry.getFluid(1));
                    } else {
                        tile.fluidFilters.set(13, FluidRegistry.getFluid(tile.fluidFilters.get(13).getID() + 1));
                    }
                } else {
                    if (tile.fluidFilters.get(13).getID() == 1) {
                        tile.fluidFilters.set(13, FluidRegistry.getFluid(FluidRegistry.getMaxID()));
                    } else {
                        tile.fluidFilters.set(13, FluidRegistry.getFluid(tile.fluidFilters.get(13).getID() - 1));
                    }
                }
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }
    }

}
