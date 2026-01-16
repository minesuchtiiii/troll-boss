package me.minesuchtiiii.trollboss.structures.schlong;

import me.minesuchtiiii.trollboss.structures.util.RelativeBlock;
import org.bukkit.Material;

import java.util.List;

public final class SchlongTorches {

    private static final List<RelativeBlock> BLOCKS = build();

    private SchlongTorches() {}

    public static List<RelativeBlock> blocks() {
        return BLOCKS;
    }

    private static List<RelativeBlock> build() {
        return List.of(
                new RelativeBlock( 8, 0,  8, Material.TORCH),
                new RelativeBlock(-8, 0,  8, Material.TORCH),
                new RelativeBlock( 8, 0, -8, Material.TORCH),
                new RelativeBlock(-8, 0, -8, Material.TORCH)
        );
    }
}
