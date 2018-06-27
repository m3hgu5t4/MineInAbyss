package com.derongan.minecraft.mineinabyss.Relic.Behaviour;

import com.derongan.minecraft.mineinabyss.Relic.Relics.RelicType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public interface RelicBehaviour {
    default void setRelicType(RelicType type) {}
    default void execute(Event e, ItemStack item, RelicSlot slot) {} //temporarily default until confirm it works so no compile errors
}
