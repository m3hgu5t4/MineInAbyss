package com.derongan.minecraft.mineinabyss.Relic.Behaviour;

import org.bukkit.inventory.ItemStack;
import org.bukkit.event.player.PlayerMoveEvent;

public interface MoveRelicBehaviour extends RelicBehaviour {
    public default void execute(PlayerMoveEvent e, ItemStack item, RelicSlot slot) {
        onMove(e, item, slot);
    }
    public void onMove(PlayerMoveEvent e, ItemStack item, RelicSlot slot);
}
