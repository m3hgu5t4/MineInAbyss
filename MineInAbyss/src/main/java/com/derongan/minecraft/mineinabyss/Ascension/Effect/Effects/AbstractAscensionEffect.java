package com.derongan.minecraft.mineinabyss.Ascension.Effect.Effects;

import com.derongan.minecraft.mineinabyss.AbyssContext;
import com.derongan.minecraft.mineinabyss.Ascension.Effect.AscensionEffect;
import org.bukkit.entity.Player;

public abstract class AbstractAscensionEffect implements AscensionEffect {
    int durationRemaining;
    int elapsed;
    int strength;
    private long offset; //TODO honor this value

    AbstractAscensionEffect(long offset, int strength, int durationRemaining) {
        this.durationRemaining = durationRemaining;
        this.elapsed = 0;
        this.offset = offset;
        this.strength = strength;
    }

    @Override
    public void applyEffect(Player player, int ticks) {
        durationRemaining -= ticks;
        applyEffect(player);
    }

    abstract void applyEffect(Player player);

    @Override
    public boolean isDone() {
        return durationRemaining <= 0;
    }

    @Override
    public int getRemainingTicks() {
        return durationRemaining;
    }

    @Override
    public void setRemainingTicks(int remainingTicks) {
        durationRemaining = remainingTicks;
    }

    @Override
    public void cleanUp(Player player) {
    }
}
