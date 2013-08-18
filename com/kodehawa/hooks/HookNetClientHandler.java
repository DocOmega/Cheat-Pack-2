package com.kodehawa.hooks;

import java.io.IOException;

import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.src.GuiDownloadTerrain;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.IntegratedServer;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.StatList;
import net.minecraft.src.WorldClient;
import net.minecraft.src.WorldSettings;

import com.google.common.base.Charsets;
import com.kodehawa.CheatingEssentials;


public class HookNetClientHandler extends NetClientHandler
{
	  public HookNetClientHandler(final Minecraft par1Minecraft, final String par2Str, final int par3) throws IOException {
          super(par1Minecraft, par2Str, par3);
	  }

	  public HookNetClientHandler(final Minecraft par1Minecraft, final String par2Str, final int par3, final GuiScreen par4GuiScreen) throws IOException {
          super(par1Minecraft, par2Str, par3, par4GuiScreen);
	  }

	  public HookNetClientHandler(final Minecraft par1Minecraft, final IntegratedServer par2IntegratedServer) throws IOException {
		  super(par1Minecraft, par2IntegratedServer);
	  }

  	@Override
  	public void handleLogin(final Packet1Login par1Packet1Login) {
          //CheatingEssentials.getMinecraftInstance().playerController = new HookPlayerControllerMP(CheatingEssentials.getMinecraftInstance(), this);
          CheatingEssentials.getMinecraftInstance().statFileWriter.readStat(StatList.joinMultiplayerStat, 1);
          CheatingEssentials.getMinecraftInstance().theWorld = new WorldClient(this, new WorldSettings(0L, par1Packet1Login.gameType, false,
          par1Packet1Login.hardcoreMode, par1Packet1Login.terrainType), par1Packet1Login.dimension,
          par1Packet1Login.difficultySetting, CheatingEssentials.getMinecraftInstance().mcProfiler, CheatingEssentials.getMinecraftInstance().getLogAgent());
          CheatingEssentials.getMinecraftInstance().theWorld.isRemote = true;
          CheatingEssentials.getMinecraftInstance().loadWorld(CheatingEssentials.getMinecraftInstance().theWorld);
          CheatingEssentials.getMinecraftInstance().thePlayer.dimension = par1Packet1Login.dimension;
          CheatingEssentials.getMinecraftInstance().displayGuiScreen(new GuiDownloadTerrain(this));
          CheatingEssentials.getMinecraftInstance().thePlayer.entityId = par1Packet1Login.clientEntityId;
          currentServerMaxPlayers = par1Packet1Login.maxPlayers;
          CheatingEssentials.getMinecraftInstance().playerController.setGameType(par1Packet1Login.gameType);
          CheatingEssentials.getMinecraftInstance().gameSettings.sendSettingsToServer();
          //CheatingEssentials.getMinecraftInstance().myNetworkManager.addToSendQueue(new Packet250CustomPayload("MC|Brand", ClientBrandRetriever.getClientModName().getBytes(Charsets.UTF_8)));
  }
}

