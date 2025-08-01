package de.promolitor.tchelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.promolitor.tchelper.resolver.AspectCalculation;
import net.minecraft.client.renderer.texture.TextureManager;
import thaumcraft.api.aspects.Aspect;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import net.minecraft.util.ResourceLocation;


@SideOnly(Side.CLIENT)
public class GuiMain extends Gui {

    private ResourceLocation tchelperBackground =
        new ResourceLocation(TCHelperMain.MODID, "TCHelperBackground.png");
    private ResourceLocation tchelperRight =
        new ResourceLocation(TCHelperMain.MODID, "right.png");
    int posY;
    int posX;
    private TextureManager textureManager;
    ArrayList<Aspect> notPrimal = new ArrayList<Aspect>();


    public GuiMain(Minecraft mc) {
        int width = 20;
        int height = 20;
        int scale = Config.aspectScale;
        ScaledResolution scaled = new ScaledResolution(mc, width, height);

        textureManager = mc.getTextureManager();

        posX = 20;
        posY = Config.topDistance;

        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT);
        {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glPushAttrib(GL11.GL_TEXTURE_BIT);
            {


//                if (Config.leftSide) {
//                    notPrimal = new ArrayList<Aspect>();
//                    for (String[] solved : AspectCalculation.solvedIssues) {
//                        // System.out.println(posX + "/" + posY);
//                        int aspects = solved.length;
//                        int overallSpaces = aspects + aspects - 1;
//                        for (int y = 0; y < aspects; y++) {
//                            Aspect as = Aspect.aspects.get(solved[y]);
//                            int tmpY = posY;
//                            if (y > 0 && y < aspects - 1) {
//                                if (!as.isPrimal()) {
//                                    checkPrimalDeep(as);
//                                }
//                            }
//
//                            posY = tmpY;
//                            posX = 20;
//                            drawAspect(as, mc, scale, y);
//
//                            if (y != aspects - 1) {
//                                mc.getTextureManager().bindTexture(tchelperRight);
//                                drawModalRectWithCustomSizedTexture((posX + ((0 + y) * (scale * 2) + scale)), posY, 0, 0, scale,
//                                    scale, scale, scale);
//                            }
//
//                        }
//
//                        posY = posY + scale;
//
//                        for (Aspect as : notPrimal) {
//                            addParents(as, mc, scale, 1);
//                            posY = posY + scale;
//                        }
//                        if (!notPrimal.isEmpty()) {
//                            posY = posY + scale;
//                        }
//                        notPrimal.clear();
//                    }
//                }

                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glClearColor(-1.0f, -1.0f, -1.0f, 1.0f);
                GL11.glPopAttrib();
            }
            GL11.glPopAttrib();
        }
    }

    //idk if this is the correct name bc this came from 1.8.9
    public void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v,
                                                    int width, int height,
                                                    float textureWidth, float textureHeight) {
        func_146110_a(x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public void drawAspect(Aspect as, Minecraft mc, int scale, int y) {
        mc.getTextureManager().bindTexture(as.getImage());
        Color c = new Color(as.getColor());
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        // Thx for pointing out my float mistake -> gigaherz
        GL11.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, 1f);
        drawModalRectWithCustomSizedTexture((posX + (0 + y) * (scale * 2)), posY, 0, 0, scale, scale, scale, scale);

    }

    private void checkPrimalDeep(Aspect as) {
        if (!as.isPrimal()) {
            if (!notPrimal.contains(as)) {
                notPrimal.add(as);
            }
            checkPrimalDeep(as.getComponents()[0]);
            checkPrimalDeep(as.getComponents()[1]);
        }

    }

    public void addParents(Aspect as, Minecraft mc, int scale, int y) {
        y = 1;
        Aspect p1 = as.getComponents()[0];
        Aspect p2 = as.getComponents()[1];
        // System.out.println("Aspect: " + as.getTag() + " = " + p1.getTag() + "
        // + " + p2.getTag());
        drawAspect(as, mc, scale, y);
        mc.getTextureManager().bindTexture(tchelperRight);
        drawModalRectWithCustomSizedTexture((posX + ((0 + y) * (scale * 2) + scale)), posY, 0, 0, scale, scale, scale,
            scale);
        y++;
        drawAspect(p1, mc, scale, y);
        y++;
        drawAspect(p2, mc, scale, y);

    }


}
