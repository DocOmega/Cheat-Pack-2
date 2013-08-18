package com.kodehawa.module.classes;

import net.minecraft.src.EntityOtherPlayerMP;
import net.minecraft.src.EntityPlayerSP;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.reeszrbteam.ce.util.EntitySpectator;

public class Spectator extends ModuleBase implements com.kodehawa.util.Tickable{

	private EntityOtherPlayerMP entity = null;
	public EntitySpectator freecamEnt = null;
	private double freecamX, freecamY, freecamZ, freecamPitch, freecamYaw;
	
	public Spectator() {
		super("Spectate", "Spectate and Spy on People", "1.6.2", 0, EnumGuiCategory.PLAYER, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnableModule() {
		
	}

	@Override
	public void onDisableModule() {
		
	}

	@Override
	public void tick() {
		if(getEnabled() && freecamEnt != null)
		{
			freecamEnt.rotationPitch = CheatingEssentials.getMinecraftInstance().thePlayer.rotationPitch;
			freecamEnt.rotationYaw = CheatingEssentials.getMinecraftInstance().thePlayer.rotationYaw;
			freecamEnt.rotationYawHead = CheatingEssentials.getMinecraftInstance().thePlayer.rotationYawHead;
		}
		try {
			if(getEnabled() && freecamEnt != null)
			{
				EntityPlayerSP player = CheatingEssentials.getMinecraftInstance().thePlayer;
				freecamEnt.inventory = player.inventory;
				freecamEnt.yOffset = player.yOffset;
				freecamEnt.ySize = player.ySize;
				freecamEnt.flyMode = ModuleManager.getInstance().getModuleByClass(Fly.class).getEnabled();
				freecamEnt.setMovementInput(player.movementInput);
				freecamEnt.rotationPitch = player.rotationPitch;
				freecamEnt.rotationYaw = player.rotationYaw;
				freecamEnt.rotationYawHead = player.rotationYawHead;
				freecamEnt.setSprinting(player.isSprinting());
				
				if(CheatingEssentials.getMinecraftInstance().renderViewEntity != freecamEnt)
				{
					CheatingEssentials.getMinecraftInstance().renderViewEntity = freecamEnt;
				}
			}else if(getEnabled())
			{
				this.toggleModule();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
