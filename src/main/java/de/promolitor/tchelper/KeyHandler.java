package de.promolitor.tchelper;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.promolitor.tchelper.resolver.AspectCalculation;
import de.promolitor.tchelper.types.Combination;
import de.promolitor.tchelper.types.Hexagon;
import de.promolitor.tchelper.utils.Utils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;
import thaumcraft.api.aspects.Aspect;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class KeyHandler {
    private static final KeyBinding tcSolver = new KeyBinding("Solve TC Research Node",
        Keyboard.KEY_MINUS,
        "TC Research Helper");

    //Fake Mutex?????
    public static boolean drawing = false;

    public KeyHandler() {
        FMLCommonHandler.instance().bus().register(this);
        ClientRegistry.registerKeyBinding(tcSolver);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event) {
        if (tcSolver.isPressed()) {
            if (drawing) {
                drawing = false;
                return;
            }

            TCHelperMain.LOG.info("Pressed TC Solver!");

            ItemStack heldItem = TCHelperMain.mc.thePlayer.inventory.getCurrentItem();

            var researchAspects = Utils.getResearchAspectHexagons(heldItem);

            TCHelperMain.LOG.info("Given Research Nodes:" + researchAspects);

            var calculator = new AspectCalculation(researchAspects);
            calculator.completePaths();
//            drawing = true;

//            for (Combination combination : toCheck) {
//
//                if (!Aspect.aspects.containsKey(combination.h1.aspect)
//                    || !Aspect.aspects.containsKey(combination.h2.aspect)) {
//                    TCHelperMain.mc.thePlayer
//                        .addChatMessage(new
//                            ChatComponentText("Sorry, no Addon Aspect supported yet!"));
//                    return;
//                }
//                System.out.println("Checking" + combination.h1 + " to");
////                AspectCalculation.solveIussesDeep(
////                    Aspect.aspects.get(combination.h1.aspect),
////                    Aspect.aspects.get(combination.h2.aspect),
////                    AspectCalculation.getDistance(combination.h1, combination.h2)
////                );
//
//
//                drawing = true;
//            }
        }
    }



}
