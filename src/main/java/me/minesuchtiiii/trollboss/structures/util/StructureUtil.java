package me.minesuchtiiii.trollboss.structures.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public final class StructureUtil {

    private StructureUtil() {}

    public static void placeAndRecord(World world, Location base, int z, Material mat, int[][] shape, StructureRecorder recorder) {
        int bx = base.getBlockX();
        int by = base.getBlockY();
        int bz = base.getBlockZ();

        for (int[] p : shape) {
            Block b = world.getBlockAt(bx + p[0], by + p[1], bz + z);
            recorder.record(b);
            b.setType(mat);
        }
    }

    public static void placeAndRecord(World world, Location base, Material mat, int[][] shape, StructureRecorder recorder) {
        int bx = base.getBlockX();
        int by = base.getBlockY();
        int bz = base.getBlockZ();

        for (int[] p : shape) {
            Block b = world.getBlockAt(bx + p[0], by + p[1], bz + p[2]);
            recorder.record(b);
            b.setType(mat);
        }
    }
}
