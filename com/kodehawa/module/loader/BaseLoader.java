package com.kodehawa.module.loader;

import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.classes.ChestESP;
import com.kodehawa.module.classes.ClientFastPlace;
import com.kodehawa.module.classes.CreativeFly;
import com.kodehawa.module.classes.FastBreak;
import com.kodehawa.module.classes.Fly;
import com.kodehawa.module.classes.NoFall;
import com.kodehawa.module.classes.Sprint;
import com.kodehawa.module.classes.Step;
import com.kodehawa.module.classes.Xray;


/**
 * Add all basic modules to the correspondient arrays.
 */
public final class BaseLoader {
	
	private volatile static BaseLoader instance;
	
	@ModuleLoader(type = "Charger")
    public BaseLoader( ){
		ModuleManager.getInstance().addPlayerModule(new CreativeFly( ) );
		ModuleManager.getInstance().addPlayerModule(new Fly( ) );
		ModuleManager.getInstance().addPlayerModule(new Step( ) );
		ModuleManager.getInstance().addPlayerModule(new NoFall( ));
		ModuleManager.getInstance().addPlayerModule(new Sprint( ));
		ModuleManager.getInstance().addWorldModule(new Xray( ));
		ModuleManager.getInstance().addWorldModule(new ClientFastPlace( ));
		ModuleManager.getInstance().addWorldModule(new FastBreak( ));
		ModuleManager.getInstance().addWorldModule(new ChestESP( ));
	}
	
	public static BaseLoader getInstance(){
		if(instance == null){
			instance = new BaseLoader();
		}
	    return instance;
	}
}
