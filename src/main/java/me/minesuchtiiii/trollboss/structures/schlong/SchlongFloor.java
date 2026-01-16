package me.minesuchtiiii.trollboss.structures.schlong;

import me.minesuchtiiii.trollboss.structures.util.RelativeBlock;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public final class SchlongFloor {

    private static final List<RelativeBlock> BLOCKS = build();

    private SchlongFloor() {}

    public static List<RelativeBlock> blocks() {
        return BLOCKS;
    }

    private static List<RelativeBlock> build() {
        List<RelativeBlock> b = new ArrayList<>();

        for (int x = -9; x <= 9; x++) {
            for (int z = -9; z <= 9; z++) {
                b.add(new RelativeBlock(x, -1, z, Material.GRASS_BLOCK));
            }
        }

        return List.copyOf(b);
    }
}
