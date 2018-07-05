package com.derongan.minecraft.mineinabyss.Relic;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum RelicSlot {
	HEAD { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getHelmet()); }},
	CHEST { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getChestplate()); }},
	LEGS { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getLeggings()); }},
	FEET { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getBoots()); }},
	USED { @Override
	public List<ItemStack> getItem(Player player) { //cheat a bit
		List<ItemStack> items = MAIN_HAND.getItem(player);
		items.addAll(OFF_HAND.getItem(player));
		return items;
	}},
	MAIN_HAND { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getItemInMainHand()); }},
	OFF_HAND { @Override
	public List<ItemStack> getItem(Player player) {
		return Arrays.asList(player.getInventory().getItemInOffHand()); }};

	public abstract List<ItemStack> getItem(Player player);
}