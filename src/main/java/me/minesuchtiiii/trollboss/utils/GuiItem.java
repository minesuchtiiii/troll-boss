package me.minesuchtiiii.trollboss.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class GuiItem {

    public static ItemStack createGuiItem(int amount, Material mat, String displayName, String... lore) {

        ItemStack itemStack = new ItemStack(mat, amount);
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        ArrayList<String> metaLore = new ArrayList<>();
        metaLore.add("");
        metaLore.addAll(Arrays.asList(lore));
        itemStackMeta.setLore(metaLore);
        itemStackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStackMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemStackMeta);

        return itemStack;
    }
}
