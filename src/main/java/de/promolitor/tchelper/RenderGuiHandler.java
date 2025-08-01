package de.promolitor.tchelper;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class RenderGuiHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.type != ElementType.EXPERIENCE || !KeyHandler.drawing) {
			return;
		}
        TCHelperMain.LOG.info("Rendering GUI");
        //Don't Like this...
		new GuiMain(TCHelperMain.mc);
	}
}
