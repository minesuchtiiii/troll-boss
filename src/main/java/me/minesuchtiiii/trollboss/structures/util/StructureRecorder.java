package me.minesuchtiiii.trollboss.structures.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class StructureRecorder {

    private final Map<Block, Material> blocks = new HashMap<>();

    public void record(Block block) {
        blocks.putIfAbsent(block, block.getType());
    }

    public void clear() {
        for (Map.Entry<Block, Material> blockEntry : blocks.entrySet()) {
            blockEntry.getKey().setType(blockEntry.getValue());
        }
        blocks.clear();
    }
}
