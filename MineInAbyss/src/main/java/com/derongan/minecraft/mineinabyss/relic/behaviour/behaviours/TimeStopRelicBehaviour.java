package com.derongan.minecraft.mineinabyss.relic.behaviour.behaviours;

import com.derongan.minecraft.mineinabyss.relic.behaviour.ChatRelicBehaviour;
import com.derongan.minecraft.mineinabyss.relic.behaviour.RelicBehaviour;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TimeStopRelicBehaviour implements RelicBehaviour<AsyncPlayerChatEvent> {
    @Override
    public void execute(AsyncPlayerChatEvent event) {
//        if (!event.getMessage().equals("ZA WARUDO!")) {
//            return;
//        }
//
//
//        Player player = event.getPlayer();
//
//        //Color the message
//        event.setCancelled(true);
//        player.sendMessage(ChatColor.RED + player.getName() + ": ZA WARUDO!");
//
//        Set<LivingEntity> others = player.getNearbyEntities(16, 16, 16).stream()
//                .filter(a -> a instanceof LivingEntity)
//                .map(a -> (LivingEntity) a)
//                .filter(a -> a != player).collect(Collectors.toSet());
//
//        for (LivingEntity a : others) {
//            a.setGravity(false);
//            a.setVelocity(new Vector(0, 0, 0));
//            a.setAI(false);
//
//            if (a instanceof Player) {
//                ((Player) a).setFlySpeed(0);
//                ((Player) a).setWalkSpeed(0);
//            }
//        }
//
//        player.getServer().getScheduler().scheduleSyncDelayedTask(JavaPlugin.getProvidingPlugin(this.getClass()), () -> {
//            for (LivingEntity other : others) {
//                other.setGravity(true);
//                other.setAI(true);
//
//                if (other instanceof Player) {
//                    ((Player) other).setFlySpeed(.2f);
//                    ((Player) other).setWalkSpeed(.2f);
//                }
//            }
//        }, TickUtils.milisecondsToTicks(5000));
    }
}
