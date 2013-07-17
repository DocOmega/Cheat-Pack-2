package com.kodehawa.util;

public interface CModTicks {

	public void modTicks();
	public Utils getUtils();
	public void handleKeyPress();
	public void updateArray();
	public void addToTick(Tickable tickable);
	public void removeFromCurrentTick(Tickable tickable);
	public void tick();
	
}
