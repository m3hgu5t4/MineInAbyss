package com.derongan.minecraft.mineinabyss.Relic;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RelicEquipEvent extends Event {
	Player player;

	public Player getPlayer() {
		return player;
	}
	public RelicEquipEvent(Player p) {
		player = p;
	}

	@Override
	public HandlerList getHandlers() { //this isn't a real event, it just needs to count as extending from Event
		return null;
	}
}
