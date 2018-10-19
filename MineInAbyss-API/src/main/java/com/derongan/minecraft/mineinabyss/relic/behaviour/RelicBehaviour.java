package com.derongan.minecraft.mineinabyss.relic.behaviour;

import com.derongan.minecraft.mineinabyss.relic.relics.RelicType;
import org.bukkit.event.Event;

/*
public interface RelicBehaviour {
    default void setRelicType(RelicType type){}
}
*/

public interface RelicBehaviour <E extends Event> {
    public void execute(E event);
    default void setRelicType(RelicType type) {}
}