package com.kodehawa.hooks;

import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.classes.Fly;
import com.kodehawa.module.classes.Spectator;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet10Flying;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;
import net.minecraft.src.Packet19EntityAction;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.Session;
import net.minecraft.src.World;

public class HookEntityClientPlayerMP extends EntityClientPlayerMP{
	
    private double oldPosX;

    /** Old Minimum Y of the bounding box */
    private double oldMinY;
    private double oldPosY;
    private double oldPosZ;
    private float oldRotationYaw;
    private float oldRotationPitch;

    /** Check if was on ground last update */
    private boolean wasOnGround;

    /** should the player stop sneaking? */
    private boolean shouldStopSneaking;
    private boolean wasSneaking;
    private int field_71168_co;

    /** has the client player's health been set? */
    private boolean hasSetHealth;
    private String field_142022_ce;

    public HookEntityClientPlayerMP(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler)
    {
        super(par1Minecraft, par2World, par3Session, par4NetClientHandler);
    }
    
    @Override
    public void sendMotionUpdates() {
		if(ModuleManager.getInstance().getModuleByClass(Spectator.class).getEnabled()) return;
		boolean var1 = this.isSprinting();

		if (var1 != this.wasSneaking)
		{
			if (var1)
			{
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
			}
			else
			{
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
			}

			this.wasSneaking = var1;
		}

		boolean var2 = this.isSneaking();

		if (var2 != this.shouldStopSneaking)
		{
			if (var2)
			{
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
			}
			else
			{
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
			}

			this.shouldStopSneaking = var2;
		}

		double var3 = this.posX - this.oldPosX;
		double var5 = this.boundingBox.minY - this.oldMinY;
		double var7 = this.posZ - this.oldPosZ;
		double var9 = (double)(this.rotationYaw - this.oldRotationYaw);
		double var11 = (double)(this.rotationPitch - this.oldRotationPitch);
		boolean var13 = var3 * var3 + var5 * var5 + var7 * var7 > 9.0E-4D || this.field_71168_co >= 20;
		boolean var14 = var9 != 0.0D || var11 != 0.0D;

		if (this.ridingEntity != null)
		{
			this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.motionX, -999.0D, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
			var13 = false;
		}
		else if (var13 && var14)
		{
			this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround));
		}
		else if (var13)
		{
			this.sendQueue.addToSendQueue(new Packet11PlayerPosition(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.onGround));
		}
		else if (var14)
		{
			this.sendQueue.addToSendQueue(new Packet12PlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
		}
		else
		{
			this.sendQueue.addToSendQueue(new Packet10Flying(this.onGround));
		}

		++this.field_71168_co;
		this.wasOnGround = this.onGround;

		if (var13)
		{
			this.oldPosX = this.posX;
			this.oldMinY = this.boundingBox.minY;
			this.oldPosY = this.posY;
			this.oldPosZ = this.posZ;
			this.field_71168_co = 0;
		}

		if (var14)
		{
			this.oldRotationYaw = this.rotationYaw;
			this.oldRotationPitch = this.rotationPitch;
		}
	}

	public void moveEntity(double par1, double par3, double par5)
	{
		if(ModuleManager.getInstance().getModuleByClass(Spectator.class).getEnabled()) return;
		float d = distanceWalkedModified;
		super.moveEntity(par1, par3, par5);
		if(ModuleManager.getInstance().getModuleByClass(Fly.class).getEnabled() && !ModuleManager.getInstance().getModuleByClass(Spectator.class).getEnabled())
		{
			distanceWalkedModified = d;
			onGround = true;
			this.inWater = false;
		}
	}

	public boolean handleWaterMovement() {

		return super.handleWaterMovement();
	}

	@Override
	public void jump() {
		super.jump();
	}

	public void onUpdate()
	{
		if (this.worldObj.blockExists(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)))
		{
			
		}
	}
}
