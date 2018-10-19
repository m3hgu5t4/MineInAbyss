package com.derongan.minecraft.mineinabyss.relic.relics;

import com.derongan.minecraft.mineinabyss.relic.behaviour.behaviours.*;
import com.derongan.minecraft.mineinabyss.relic.behaviour.RelicBehaviour;
import com.derongan.minecraft.mineinabyss.relic.RelicRarity;
import com.google.common.collect.HashMultimap;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum StandardRelicType implements RelicType {
    /*
    BLAZE_REAP(Material.DIAMOND_PICKAXE,
            0,
            new BlazeReapRelicBehaviour(),
            "Blaze Reap",
            Arrays.asList("An abnormally large pickaxe", "that contains Everlasting Gunpowder."),
            RelicRarity.FIRST_GRADE
    ),
    INCINERATOR(Material.FLINT_AND_STEEL,
            0,
            new IncineratorRelicBehaviour(),
            "Incinerator",
            Arrays.asList("A severed robotic arm.", "You can fiddle with it", "and things explode."),
            RelicRarity.SPECIAL_GRADE
    ),
    PUSHER(Material.WOOD_SPADE,
            1,
            new PushingRelicBehaviour(),
            "Push Stick",
            Arrays.asList("It pushes things."),
            RelicRarity.SECOND_GRADE
    ),
    UNHEARD_BELL(Material.WATCH,
            0,
            new TimeStopRelicBehaviour(),
            "Unheard Bell",
            Arrays.asList("What does it do?", "It is rumored to stop time."),
            RelicRarity.SPECIAL_GRADE
    ),
    ROPE_LADDER(Material.LEASH,
            0,
            new UnfurlLadderRelicBehaviour(),
            "Rope Ladder",
            Arrays.asList("Places a Ladder"),
            RelicRarity.TOOL
    ),
    THOUSAND_MEN_PINS(Material.STONE_HOE,
            1,
            new ThousandMenPinsRelicBehaviour(),
            "Thousand-Men Pins",
            Arrays.asList("Each pin is said to bestow", "the strength of a thousand men", "", "Right click to consume"),
            RelicRarity.FIRST_GRADE
    ),
    SLAVE_STICK(Material.STICK,
            5,
            new SlaveRelicBehaviour(),
            "Slave Stick",
            Arrays.asList("Ha"),
            RelicRarity.NOT_IMPLEMENTED
    );
    */
    BLAZE_REAP(new RelicTypeBuilder()
            .setName("Blaze Reap")
            .setMaterial(Material.DIAMOND_PICKAXE)
            .setDamage(0)
            .setRarity(RelicRarity.FIRST_GRADE)
            .addLore("An abnormally large pickaxe", "that contains Everlasting Gunpowder.")
            .addBehaviour(EntityDamageByEntityEvent.class, new BlazeReapRelicBehaviour.OnHitBehaviour())
            .addBehaviour(PlayerInteractEvent.class, new BlazeReapRelicBehaviour.OnUseBehaviour())
    ),
    INCINERATOR(new RelicTypeBuilder()
            .setMaterial(Material.FLINT_AND_STEEL)
            .setDamage(0)
            .addBehaviour(PlayerInteractEvent.class, new IncineratorRelicBehaviour())
            .setName("Incinerator")
            .addLore("A severed robotic arm.", "You can fiddle with it", "and things explode.")
            .setRarity(RelicRarity.SPECIAL_GRADE)
    ),
    PUSHER(new RelicTypeBuilder()
            .setMaterial(Material.WOOD_SPADE)
            .setDamage(1)
            .addBehaviour(EntityDamageByEntityEvent.class, new PushingRelicBehaviour())
            .setName("Push Stick")
            .addLore("It pushes things.")
            .setRarity(RelicRarity.SECOND_GRADE)
    ),
    UNHEARD_BELL(new RelicTypeBuilder()
            .setMaterial(Material.WATCH)
            .setDamage(0)
            .addBehaviour(AsyncPlayerChatEvent.class, new TimeStopRelicBehaviour())
            .setName("Unheard Bell")
            .addLore("What does it do?", "It is rumored to stop time.")
            .setRarity(RelicRarity.SPECIAL_GRADE)
    ),
    ROPE_LADDER(new RelicTypeBuilder()
            .setMaterial(Material.LEASH)
            .setDamage(0)
            .addBehaviour(PlayerInteractEvent.class, new UnfurlLadderRelicBehaviour())
            .setName("Rope Ladder")
            .addLore("Places a Ladder")
            .setRarity(RelicRarity.TOOL)
    ),
    THOUSAND_MEN_PINS(new RelicTypeBuilder()
            .setMaterial(Material.STONE_HOE)
            .setDamage(1)
            .addBehaviour(PlayerInteractEvent.class, new ThousandMenPinsRelicBehaviour())
            .setName("Thousand-Men Pins")
            .addLore("Each pin is said to bestow", "the strength of a thousand men", "", "Right click to consume")
            .setRarity(RelicRarity.FIRST_GRADE)
    ),
    SLAVE_STICK(new RelicTypeBuilder()
            .setMaterial(Material.STICK)
            .setDamage(5)
            .addBehaviour(PlayerInteractEntityEvent.class, new SlaveRelicBehaviour())
            .setName("Slave Stick")
            .addLore("Ha")
            .setRarity(RelicRarity.NOT_IMPLEMENTED)
    );

    private final Material material;
    private final short durability;
    private final String name;
    private final List<String> lore;
    //private final RelicBehaviour behaviour;
    private final HashMultimap<Class<? extends Event>, RelicBehaviour<? extends Event>> behaviours;
    private final RelicRarity rarity;

    /*
    StandardRelicType(Material material, long durability, RelicBehaviour behaviour, String name, List<String> lore, RelicRarity rarity) {
        this.durability = (short) durability;
        this.material = material;
        this.behaviour = behaviour;
        this.name = name;
        this.lore = lore;
        this.rarity = rarity;

        behaviour.setRelicType(this);
    }
    */
    StandardRelicType(RelicTypeBuilder b) {
        this.durability = b.damage;
        this.material = b.material;
        this.behaviours = b.behaviours;
        this.name = b.name;
        this.lore = b.lore;
        this.rarity = b.rarity;

        for (Class<? extends Event> e : this.behaviours.keySet()) {
            for (RelicBehaviour rb : this.behaviours.get(e)) {
                rb.setRelicType(this);
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public short getDurability() {
        return durability;
    }

    /*
    @Override
    public RelicBehaviour getBehaviour() {
        return behaviour;
    }
    */
    @Override
    public Collection<RelicBehaviour<? extends Event>> getBehaviours(Class<? extends Event> event) {
        return behaviours.get(event);
    }

    @Override
    public RelicRarity getRarity() {
        return rarity;
    }
}
