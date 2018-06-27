package com.derongan.minecraft.mineinabyss.Relic.Behaviour;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public interface ConsumeRelicBehaviour extends RelicBehaviour {
    public void onConsume(PlayerItemConsumeEvent event, ItemStack item, RelicSlot slot);
    public default void execute(Event e, ItemStack item, RelicSlot slot) {
        System.out.println("execute executes"); //this doesn't trigger
        onConsume((PlayerItemConsumeEvent) e, item, slot);
    }
}
