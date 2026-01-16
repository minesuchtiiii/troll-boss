package me.minesuchtiiii.trollboss.structures.noob;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.structures.util.StructureRecorder;
import me.minesuchtiiii.trollboss.trolls.NoobManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import me.minesuchtiiii.trollboss.utils.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import static me.minesuchtiiii.trollboss.structures.util.AnimationUtil.later;
import static me.minesuchtiiii.trollboss.structures.util.StructureUtil.placeAndRecord;
import static me.minesuchtiiii.trollboss.structures.noob.NoobLetters.*;

public class NoobStructure {

    private final StructureRecorder recorder;
    private final Pair<Block, Material> glassUnder;
    private final Player target;
    private final Location beforeNoob;
    private final Location structureView;

    public NoobStructure(Player player) {
        this.target = player;
        this.beforeNoob = player.getLocation().clone();

        this.structureView = beforeNoob.clone();
        this.structureView.setY(220);
        this.structureView.setYaw(180);
        this.structureView.setPitch(0);

        this.recorder = new StructureRecorder();
        Block blockUnder = structureView.clone().subtract(0, 1, 0).getBlock();
        this.glassUnder = new Pair<>(blockUnder, blockUnder.getType());
    }

    public void generateAndTeleportToBuild() {
        Location base = structureView.clone();
        World world = target.getWorld();

        glassUnder.getLeft().setType(Material.GLASS);
        teleportToStructure();

        later(() -> buildLetter(world, base, N), 40L);
        later(() -> buildLetter(world, base, O1), 80L);
        later(() -> buildLetter(world, base, O2), 120L);
        later(() -> buildLetter(world, base, B), 160L);
    }

    public void removeAndTeleportFromBuild() {
        later(this::clearBlocksAndTeleportBack, 240L); // It breaks the old "animation", but I think it's fine *for now*
    }

    private void clearBlocksAndTeleportBack() {
        // Not ideal to handle this here, but we must do it in the schedule
        TrollManager.deactivate(target.getUniqueId(), TrollType.NOOB);
        NoobManager.setCanNoob(true);

        recorder.clear();
        glassUnder.getLeft().setType(glassUnder.getRight());

        teleportBack();
    }

    private void teleportToStructure() {
        target.teleport(structureView);
    }

    private void teleportBack() {
        target.teleport(beforeNoob);
    }

    private void buildLetter(World world, Location base, int[][] letter) {
        placeAndRecord(world, base, -33, Material.QUARTZ_BLOCK, letter, recorder);
        placeAndRecord(world, base, -34, Material.GLOWSTONE, letter, recorder);
    }
}
