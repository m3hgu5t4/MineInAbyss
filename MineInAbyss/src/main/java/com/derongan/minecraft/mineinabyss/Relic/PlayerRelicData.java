package com.derongan.minecraft.mineinabyss.Relic;

import com.derongan.minecraft.mineinabyss.Relic.Behaviour.RelicBehaviour;
import com.derongan.minecraft.mineinabyss.Relic.Relics.RelicType;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerRelicData {
	//"should be fine with just Class<Event> but dont quote me on htat" - derongan
	//"Sure add the ? It does make it more clear" - also derongan a bit later
	private Multimap<RelicSlot, RelicType> data;
	public Collection<RelicType> getSlot(RelicSlot slot) {
		return data.get(slot);
	}
	public Collection<RelicBehaviour> getBehaviours(Class<? extends Event> e) {
		ArrayList<RelicBehaviour> rb = new ArrayList<>();
		for (RelicSlot slot : data.keySet()) {
			for (RelicType type : data.get(slot)) {
				if (type.getEvents(slot).contains(e)) {
					rb.addAll(type.getBehaviours(slot, e));
				}
			}
		}
		return rb;
	}
	public void updateSlot(Player player, RelicSlot slot) {
		ArrayList<RelicType> types = new ArrayList<>();
		for (ItemStack item : slot.getItem(player)) {
			types.add(RelicType.getRegisteredRelicType(item));
		}

		Collection<RelicType> currentTypes = new ArrayList<>(data.get(slot));
		currentTypes.removeAll(types); //get any relics removed from the slot
		types.removeAll(data.get(slot)); //get any relics added to the slot

		/*
		if (!data.get(slot).equals(types)) {
			for (RelicType type : data.get(slot)) {
				if (type.getEvents(slot).contains(RelicUnequipEvent.class)) {
					for (RelicBehaviour rb : type.getBehaviours(RelicUnequipEvent.class, slot)) {
						rb.execute(new RelicUnequipEvent(player));
					}
				}
			}
		}
		*/
		for (RelicType type : currentTypes) {
			if (type.getEvents(slot).contains(RelicUnequipEvent.class)) {
				for (RelicBehaviour rb : type.getBehaviours(slot, RelicUnequipEvent.class)) {
					rb.execute(new RelicUnequipEvent(player));
				}
			}
		}
		for (RelicType type : types) {
			if (type.getEvents(slot).contains(RelicUnequipEvent.class)) {
				for (RelicBehaviour rb : type.getBehaviours(slot, RelicEquipEvent.class)) {
					rb.execute(new RelicEquipEvent(player));
				}
			}
		}
	}
	public void register(RelicType type, RelicSlot slot) {
		if (type.inSlot(slot)) {
			data.put(slot, type);
		}
	}
	public void register(ItemStack item, RelicSlot slot) {
		RelicType type = RelicType.getRegisteredRelicType(item);
		if (type != null) {
			if (type.inSlot(slot) || ( //if its in the same slot
					(slot == RelicSlot.MAIN_HAND || slot == RelicSlot.OFF_HAND) && //if its meant to be "used" and is in one of the hands
					type.inSlot(RelicSlot.USED)
			)) {
				register(type, slot);
			}
		}
	}
	public void remove(Class<Event> e, RelicBehaviour rb) {
		data.remove(e, rb);
	}
	public void clear() {
		data.clear();
	}

	public PlayerRelicData() { data = HashMultimap.create(); }
	public PlayerRelicData(Multimap<RelicSlot, RelicType> m) {
		data = m;
	}
}
