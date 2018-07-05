package com.derongan.minecraft.mineinabyss.Relic.Behaviour;

import com.derongan.minecraft.mineinabyss.Relic.Relics.RelicType;
import org.bukkit.event.Event;

public interface RelicBehaviour <E extends Event> {
    default void setRelicType(RelicType type){}
    void execute(E e);
}
