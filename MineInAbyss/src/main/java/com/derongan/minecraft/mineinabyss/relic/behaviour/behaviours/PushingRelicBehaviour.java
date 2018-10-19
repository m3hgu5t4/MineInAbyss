package com.derongan.minecraft.mineinabyss.relic.behaviour.behaviours;

import com.derongan.minecraft.mineinabyss.relic.behaviour.EntityHitRelicBehaviour;
import com.derongan.minecraft.mineinabyss.relic.behaviour.RelicBehaviour;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class PushingRelicBehaviour implements RelicBehaviour<EntityDamageByEntityEvent> {
    @Override
    public void execute(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damagee = event.getEntity();
        Vector damagerVec = damager.getLocation().toVector();
        Vector damageeVec = damagee.getLocation().toVector();

        // Vector pointing towards damagee
        Vector pushDir = damageeVec.subtract(damagerVec);
        pushDir.add(new Vector(0,.2,0)); // Force up for fun?

        damagee.setVelocity(pushDir.normalize().multiply(3));
        event.setCancelled(true);
    }
}
