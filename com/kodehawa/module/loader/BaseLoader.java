package com.kodehawa.module.loader;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.classes.*;
import com.kodehawa.module.enums.EnumGuiCategory;

import static com.kodehawa.module.ModuleManager.*;

/**
 * Add all basic modules to the arrays.
 */
public final class BaseLoader {

    /**
     * Where it's the boolean?
     */
    private static Xray xray;
    private static ChestESP cesp;
    private static Fullbright fullbright;
    private static Mobaura killa;
    private static NoFall nofall;
    private static Waterwalk waterw;
    private static FastBreak fb;
    private static Unpushable nk;
    private static Sprint s;
    private static ClientFastPlace fp;
    private static Fly fly;
    private static CreativeFly fly2;
    private static Step step;
    private static BlockESP besp;
    private static Spectator spec;
    private static PlayerESP pesp;
    private static MobESP mesp;
    private static Tracers tracers;
	private volatile static BaseLoader instance;
	
	@ModuleLoader(type = "Charger")
    public BaseLoader( ){

		ModuleManager.getInstance().addPlayerModule(new CreativeFly( ) );
		ModuleManager.getInstance().addPlayerModule(new Fly( ) );
		ModuleManager.getInstance().addPlayerModule(new Step( ) );
		ModuleManager.getInstance().addPlayerModule(new NoFall( ));
		ModuleManager.getInstance().addPlayerModule(new Sprint( ));
        ModuleManager.getInstance().addPlayerModule(new Unpushable( ));
        ModuleManager.getInstance().addPlayerModule(new Waterwalk( ));
        ModuleManager.getInstance().addPlayerModule(new Mobaura());
        ModuleManager.getInstance().addPlayerModule(new Spectator());
        ModuleManager.getInstance().addWorldModule(new AutoRespawn( ));
        ModuleManager.getInstance().addWorldModule(new Xray( ));
        ModuleManager.getInstance().addWorldModule(new Fullbright( ));
		ModuleManager.getInstance().addWorldModule(new ClientFastPlace( ));
		ModuleManager.getInstance().addWorldModule(new FastBreak( ));
		ModuleManager.getInstance().addWorldModule(new ChestESP( ));
		ModuleManager.getInstance().addWorldModule(new BlockESP( ));
		ModuleManager.getInstance().addWorldModule(new PlayerESP( ));
		ModuleManager.getInstance().addWorldModule(new MobESP( ));
		ModuleManager.getInstance().addWorldModule(new Tracers( ));
        ModuleManager.getInstance().addWorldModule(new Breadcrumb( ));
        ModuleManager.getInstance().addUtilModule(new UtilMobHitbox( ));
        ModuleManager.getInstance().addUtilModule(new UtilReloadChunks( ));
        ModuleManager.getInstance().addUtilModule(new UtilAdvancedTooltips(  ));

        for(ModuleBase m : ModuleManager.getInstance().worldModules){
           ModuleManager.getInstance().modules.add(m);
        }
        for(ModuleBase m : ModuleManager.getInstance().playerModules){
            ModuleManager.getInstance().modules.add(m);
        }
        for(ModuleBase m : ModuleManager.getInstance().utilsModules){
            ModuleManager.getInstance().modules.add(m);
        }


        for(ModuleBase m : ModuleManager.getInstance().modules){

        if(!m.enabled){
            if(ModuleManager.getInstance().utilsModules.contains(m)){
                CheatingEssentials.CELogAgent("Util Module: " + m + "it's disabled due to internal petition");
                ModuleManager.getInstance().utilsModules.remove( m );
            }

            if(ModuleManager.getInstance().playerModules.contains(m)){
                CheatingEssentials.CELogAgent("Player Module: " + m + "it's disabled due to internal petition");
                ModuleManager.getInstance().playerModules.remove( m );
            }

            else if(ModuleManager.getInstance().worldModules.contains(m)){
                CheatingEssentials.CELogAgent("World Module: " + m + "it's disabled due to internal petition");
                ModuleManager.getInstance().worldModules.remove( m );
            }

        }
	}
    }

    public static void getKeybindingForModule(){
        xray = new Xray();
        fly = new Fly();
        cesp = new ChestESP();
        fullbright = new Fullbright();
        killa = new Mobaura();
        fb = new FastBreak();
        waterw = new Waterwalk();
        nk = new Unpushable();
        nofall = new NoFall();
        fp = new ClientFastPlace();
        s = new Sprint();
        fly2 = new CreativeFly();
        step = new Step();
        besp = new BlockESP();
        spec = new Spectator();
        pesp = new PlayerESP();
        mesp = new MobESP();
        tracers = new Tracers();
    }

	
	public static BaseLoader getInstance(){
		if(instance == null){
			instance = new BaseLoader();
		}
	    return instance;
	}
}
