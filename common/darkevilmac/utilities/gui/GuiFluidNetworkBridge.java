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

import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.network.packet.PacketFluidFilterChange;
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
        if (y >= textureCornerTopLeftY + 17 && y <= textureCornerTopLeftY + 33) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[0]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[1]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[2]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[3]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[4]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[5]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[6]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
        }

        // Row 2
        if (y >= textureCornerTopLeftY + 35 && y <= textureCornerTopLeftY + 50) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[7]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[8]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[9]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[10]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[11]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[12]).getLocalizedName());
                drawHoveringText(fluidName, x - textureCornerTopLeftX, y - textureCornerTopLeftY, mc.fontRenderer);
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                List fluidName = new ArrayList();
                fluidName.add(FluidRegistry.getFluid(tile.fluidFilters[13]).getLocalizedName());
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

        int i = 0;
        int drawX = 26;
        while (i < 14) {
            if (i > 6) {
                if (drawX == 152) {
                    drawX = 26;
                }
                drawTexturedModelRectFromIcon(textureCornerTopLeftX + drawX, textureCornerTopLeftY + 35, FluidRegistry.getFluid(tile.fluidFilters[i]).getStillIcon(), 16, 16);
            } else {
                drawTexturedModelRectFromIcon(textureCornerTopLeftX + drawX, textureCornerTopLeftY + 17, FluidRegistry.getFluid(tile.fluidFilters[i]).getStillIcon(), 16, 16);
            }
            drawX = drawX + 18;
            i++;
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int clickType) {
        super.mouseClicked(x, y, clickType);

        // Row 1
        if (y >= textureCornerTopLeftY + 17 && y <= textureCornerTopLeftY + 33) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                if (clickType == 0) {
                    if (tile.fluidFilters[0] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[0] = 1;
                    } else {
                        tile.fluidFilters[0] = tile.fluidFilters[0] + 1;
                    }
                } else {
                    if (tile.fluidFilters[0] == 1) {
                        tile.fluidFilters[0] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[0] = tile.fluidFilters[0] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[0], 0));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                if (clickType == 0) {
                    if (tile.fluidFilters[1] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[1] = 1;
                    } else {
                        tile.fluidFilters[1] = tile.fluidFilters[1] + 1;
                    }
                } else {
                    if (tile.fluidFilters[1] == 1) {
                        tile.fluidFilters[1] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[1] = tile.fluidFilters[1] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[1], 1));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                if (clickType == 0) {
                    if (tile.fluidFilters[2] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[2] = 1;
                    } else {
                        tile.fluidFilters[2] = tile.fluidFilters[2] + 1;
                    }
                } else {
                    if (tile.fluidFilters[2] == 1) {
                        tile.fluidFilters[2] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[2] = tile.fluidFilters[2] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[2], 2));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                if (clickType == 0) {
                    if (tile.fluidFilters[3] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[3] = 1;
                    } else {
                        tile.fluidFilters[3] = tile.fluidFilters[3] + 1;
                    }
                } else {
                    if (tile.fluidFilters[3] == 1) {
                        tile.fluidFilters[3] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[3] = tile.fluidFilters[3] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[3], 3));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                if (clickType == 0) {
                    if (tile.fluidFilters[4] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[4] = 1;
                    } else {
                        tile.fluidFilters[4] = tile.fluidFilters[4] + 1;
                    }
                } else {
                    if (tile.fluidFilters[4] == 1) {
                        tile.fluidFilters[4] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[4] = tile.fluidFilters[4] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[4], 4));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                if (clickType == 0) {
                    if (tile.fluidFilters[5] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[5] = 1;
                    } else {
                        tile.fluidFilters[5] = tile.fluidFilters[5] + 1;
                    }
                } else {
                    if (tile.fluidFilters[5] == 1) {
                        tile.fluidFilters[5] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[5] = tile.fluidFilters[5] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[5], 5));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                if (clickType == 0) {
                    if (tile.fluidFilters[6] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[6] = 1;
                    } else {
                        tile.fluidFilters[6] = tile.fluidFilters[6] + 1;
                    }
                } else {
                    if (tile.fluidFilters[6] == 1) {
                        tile.fluidFilters[6] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[6] = tile.fluidFilters[6] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[6], 6));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }

        // Row 2
        if (y >= textureCornerTopLeftY + 35 && y <= textureCornerTopLeftY + 50) {
            if (x >= textureCornerTopLeftX + 27 && x <= textureCornerTopLeftX + 42) {
                if (clickType == 0) {
                    if (tile.fluidFilters[7] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[7] = 1;
                    } else {
                        tile.fluidFilters[7] = tile.fluidFilters[7] + 1;
                    }
                } else {
                    if (tile.fluidFilters[7] == 1) {
                        tile.fluidFilters[7] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[7] = tile.fluidFilters[7] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[7], 7));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 45 && x <= textureCornerTopLeftX + 60) {
                if (clickType == 0) {
                    if (tile.fluidFilters[8] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[8] = 1;
                    } else {
                        tile.fluidFilters[8] = tile.fluidFilters[8] + 1;
                    }
                } else {
                    if (tile.fluidFilters[8] == 1) {
                        tile.fluidFilters[8] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[8] = tile.fluidFilters[8] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[8], 8));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 63 && x <= textureCornerTopLeftX + 78) {
                if (clickType == 0) {
                    if (tile.fluidFilters[9] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[9] = 1;
                    } else {
                        tile.fluidFilters[9] = tile.fluidFilters[9] + 1;
                    }
                } else {
                    if (tile.fluidFilters[9] == 1) {
                        tile.fluidFilters[9] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[9] = tile.fluidFilters[9] - 1;
                    }
                }
                Utilities.packetPipeline.sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[9], 9));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 81 && x <= textureCornerTopLeftX + 96) {
                if (clickType == 0) {
                    if (tile.fluidFilters[10] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[10] = 1;
                    } else {
                        tile.fluidFilters[10] = tile.fluidFilters[10] + 1;
                    }
                } else {
                    if (tile.fluidFilters[10] == 1) {
                        tile.fluidFilters[10] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[10] = tile.fluidFilters[10] - 1;
                    }
                }
                Utilities.packetPipeline
                        .sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[10], 10));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 99 && x <= textureCornerTopLeftX + 114) {
                if (clickType == 0) {
                    if (tile.fluidFilters[11] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[11] = 1;
                    } else {
                        tile.fluidFilters[11] = tile.fluidFilters[11] + 1;
                    }
                } else {
                    if (tile.fluidFilters[11] == 1) {
                        tile.fluidFilters[11] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[11] = tile.fluidFilters[11] - 1;
                    }
                }
                Utilities.packetPipeline
                        .sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[11], 11));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 117 && x <= textureCornerTopLeftX + 132) {
                if (clickType == 0) {
                    if (tile.fluidFilters[12] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[12] = 1;
                    } else {
                        tile.fluidFilters[12] = tile.fluidFilters[12] + 1;
                    }
                } else {
                    if (tile.fluidFilters[12] == 1) {
                        tile.fluidFilters[12] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[12] = tile.fluidFilters[12] - 1;
                    }
                }
                Utilities.packetPipeline
                        .sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[12], 12));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (x >= textureCornerTopLeftX + 135 && x <= textureCornerTopLeftX + 150) {
                if (clickType == 0) {
                    if (tile.fluidFilters[13] == FluidRegistry.getMaxID()) {
                        tile.fluidFilters[13] = 1;
                    } else {
                        tile.fluidFilters[13] = tile.fluidFilters[13] + 1;
                    }
                } else {
                    if (tile.fluidFilters[13] == 1) {
                        tile.fluidFilters[13] = FluidRegistry.getMaxID();
                    } else {
                        tile.fluidFilters[13] = tile.fluidFilters[13] - 1;
                    }
                }
                Utilities.packetPipeline
                        .sendToServer(new PacketFluidFilterChange(tile.world.provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, tile.fluidFilters[13], 13));
                mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }
    }
}
