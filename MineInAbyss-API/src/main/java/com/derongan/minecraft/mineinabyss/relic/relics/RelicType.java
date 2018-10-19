package com.derongan.minecraft.mineinabyss.relic.relics;

import com.derongan.minecraft.mineinabyss.relic.behaviour.RelicBehaviour;
import com.derongan.minecraft.mineinabyss.relic.RelicRarity;
import com.google.common.collect.HashMultimap;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public interface RelicType {
    Map<RelicTypeKey, RelicType> registeredRelics = new HashMap<>();

    String getName();

    List<String> getLore();

    Material getMaterial();

    short getDurability();

    public Collection<RelicBehaviour<? extends Event>> getBehaviours(Class<? extends Event> event);
    //RelicBehaviour getBehaviour();

    RelicRarity getRarity();


    default ItemStack getItem() {
        ItemStack item = new org.bukkit.inventory.ItemStack(getMaterial(), 1, getDurability());

        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setLore(getLore());

        meta.setDisplayName(getRarity().getColor() + getName());


        item.setItemMeta(meta);

        return item;
    }

    static void registerRelicType(RelicType type) {
        registeredRelics.put(type.getKey(), type);
    }

    static void unregisterAllRelics() {
        registeredRelics.clear();
    }

    static RelicType getRegisteredRelicType(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        return getRegisteredRelicType(itemStack.getDurability(), itemStack.getType());
    }

    static RelicType getRegisteredRelicType(Short durability, Material material) {
        return getRegisteredRelicType(new RelicTypeKey(durability, material));
    }

    static RelicType getRegisteredRelicType(RelicTypeKey key) {
        return registeredRelics.get(key);
    }

    default RelicTypeKey getKey() {
        return new RelicTypeKey(getDurability(), getMaterial());
    }


    class RelicTypeKey {
        private Short durability;
        private Material material;

        public RelicTypeKey(Short durability, Material material) {
            this.durability = durability;
            this.material = material;
        }

        @Override
        public int hashCode() {
            return durability.hashCode() ^ material.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof RelicTypeKey))
                return false;
            RelicTypeKey other = (RelicTypeKey) o;
            return this.material.equals(other.material) && this.durability.equals(other.durability);
        }
    }

    static class RelicTypeBuilder {
        String name;
        List<String> lore = new ArrayList<>();
        Material material;
        short damage;
        HashMultimap<Class<? extends Event>, RelicBehaviour<? extends Event>> behaviours;
        RelicRarity rarity;

        RelicTypeBuilder setName(String name) {
            this.name = name;
            return this;
        }
        RelicTypeBuilder addLore(String... lore) {
            for (String line : lore) {
                this.lore.add(line);
            }
            return this;
        }
        RelicTypeBuilder setMaterial(Material material) {
            this.material = material;
            return this;
        }
        RelicTypeBuilder setDamage(short damage) {
            this.damage = damage;
            return this;
        }
        RelicTypeBuilder setDamage(int damage) {
            return setDamage((short) damage);
        }
        RelicTypeBuilder setRarity(RelicRarity rarity) {
            this.rarity = rarity;
            return this;
        }

        <E extends Event> RelicTypeBuilder addBehaviour(Class<E> event, RelicBehaviour<E> behaviour) { // the garbage one
            this.behaviours.put(event, behaviour);
            return this;
        }
    }
}
