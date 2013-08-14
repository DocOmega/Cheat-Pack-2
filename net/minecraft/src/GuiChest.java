package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatingEssentials;

public class GuiChest extends GuiContainer
{
    private static final ResourceLocation field_110421_t = new ResourceLocation("textures/gui/container/generic_54.png");
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;

    /**
     * window height is calculated with this values, the more rows, the heigher
     */
    private int inventoryRows;

    public GuiChest(IInventory par1IInventory, IInventory par2IInventory)
    {
        super(new ContainerChest(par1IInventory, par2IInventory));
        this.upperChestInventory = par1IInventory;
        this.lowerChestInventory = par2IInventory;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = par2IInventory.getSizeInventory() / 9;
        this.ySize = var4 + this.inventoryRows * 18;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(this.lowerChestInventory.isInvNameLocalized() ? this.lowerChestInventory.getInvName() : I18n.func_135053_a(this.lowerChestInventory.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(this.upperChestInventory.isInvNameLocalized() ? this.upperChestInventory.getInvName() : I18n.func_135053_a(this.upperChestInventory.getInvName()), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(field_110421_t);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
    
    @Override
    public void initGui(){
    	super.initGui();
    	int POS_Y = (height - ySize) / 2 + 28;
    	String BUTTON_STEAL = "Raid";
    	String BUTTON_SAVE = "Store";
    	this.buttonList.add(new GuiSmallButton(1, width / 2 - 140, POS_Y, 50, 20, BUTTON_STEAL));
    	this.buttonList.add(new GuiSmallButton(2, width / 2 + 90, POS_Y, 50, 20, BUTTON_SAVE));
    }
    
    @Override
    protected void actionPerformed(GuiButton button){
    	if(button.id == 1){
    	   try{
    		   for(int x = 0;
    				   x < lowerChestInventory.getSizeInventory();
    				   x++)
    		   {
    			   CheatingEssentials.getMinecraftInstance().
    			   playerController.windowClick
    			         (this.inventorySlots.windowId,
    					     x, 0, 1,
    					       CheatingEssentials.getMinecraftInstance().thePlayer);
    		   }
    	   }
    	   catch(Exception e){
    		  e.printStackTrace();
    	   }
    	}
    	if(button.id == 2){
    		try{
    			  for(int x = 0;
       				   x < this.inventorySlots.inventorySlots.size();
       				   x++)
       		   {
       			   CheatingEssentials.getMinecraftInstance().
       			   playerController.windowClick
       			         (this.inventorySlots.windowId,
       					     x, 0, 1,
       					       CheatingEssentials.getMinecraftInstance().thePlayer);
       		   }
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	
    }
}
