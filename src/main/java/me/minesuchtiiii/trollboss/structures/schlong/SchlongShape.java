package me.minesuchtiiii.trollboss.structures.schlong;

import me.minesuchtiiii.trollboss.structures.util.RelativeBlock;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public final class SchlongShape {

    private static final List<RelativeBlock> BLOCKS = build();

    private SchlongShape() {}

    public static List<RelativeBlock> blocks() {
        return BLOCKS;
    }

    private static List<RelativeBlock> build() {
        List<RelativeBlock> b = new ArrayList<>();

        // Main
        for (int y = 0; y <= 10; y++) {
            b.add(new RelativeBlock( 1, y,  0, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-1, y,  0, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock( 0, y,  1, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock( 0, y, -1, Material.WHITE_TERRACOTTA));
        }


        // Diagonal edges
        for (int y = 0; y <= 3; y++) {
            b.add(new RelativeBlock( 1, y,  1, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-1, y,  1, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock( 1, y, -1, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-1, y, -1, Material.WHITE_TERRACOTTA));
        }


        // Sides
        for (int z : new int[]{2, 3, -2, -3}) {
            b.add(new RelativeBlock( 0, 2, z, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock( 1, 2, z, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-1, 2, z, Material.WHITE_TERRACOTTA));
        }


        // Front and backplates
        for (int x = -1; x <= 1; x++) {
            b.add(new RelativeBlock(x, 0,  4, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(x, 1,  4, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(x, 0, -4, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(x, 1, -4, Material.WHITE_TERRACOTTA));
        }

        // Balls
        for (int z : new int[]{-1, 1, 2, 3, 4, -2, -3, -4}) {
            b.add(new RelativeBlock( 2, 0, z, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-2, 0, z, Material.WHITE_TERRACOTTA));
        }

        for (int z : new int[]{-2, -3, 2, 3}) {
            b.add(new RelativeBlock( 2, 1, z, Material.WHITE_TERRACOTTA));
            b.add(new RelativeBlock(-2, 1, z, Material.WHITE_TERRACOTTA));
        }


        // Tip core
        for (int y = 11; y <= 12; y++) {
            b.add(new RelativeBlock( 1, y,  0, Material.MAGENTA_TERRACOTTA));
            b.add(new RelativeBlock(-1, y,  0, Material.MAGENTA_TERRACOTTA));
            b.add(new RelativeBlock( 0, y,  1, Material.MAGENTA_TERRACOTTA));
            b.add(new RelativeBlock( 0, y, -1, Material.MAGENTA_TERRACOTTA));
        }

        b.add(new RelativeBlock(0, 13, 0, Material.MAGENTA_TERRACOTTA));

        // Accents
        for (int x : new int[]{-1, 1}) {
            b.add(new RelativeBlock(x, 10, -1, Material.MAGENTA_TERRACOTTA));
            b.add(new RelativeBlock(x, 11, -1, Material.MAGENTA_TERRACOTTA));
        }

        for (int x : new int[]{-1, 1}) {
            for (int y = 10; y <= 13; y++) {
                b.add(new RelativeBlock(x, y, 1, Material.MAGENTA_TERRACOTTA));
            }
        }

        b.add(new RelativeBlock(0, 13, 1, Material.MAGENTA_TERRACOTTA));

        return List.copyOf(b);
    }
}
