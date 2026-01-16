package me.minesuchtiiii.trollboss.structures.schlong;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.structures.util.RelativeBlock;
import me.minesuchtiiii.trollboss.structures.util.StructureRecorder;
import me.minesuchtiiii.trollboss.trolls.SchlongManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import static me.minesuchtiiii.trollboss.structures.util.AnimationUtil.later;

public class SchlongStructure {

    private final Player target;
    private final StructureRecorder recorderMain = new StructureRecorder();
    private final StructureRecorder recorderDecor = new StructureRecorder();

    private final Location before;
    private final Location buildView;
    private final Location buildTp;

    public SchlongStructure(Player target) {
        this.target = target;

        this.before = target.getLocation().clone();

        this.buildView = before.clone();
        this.buildView.setY(200); // Right above
        this.buildView.setYaw(0f);
        this.buildView.setPitch(90f);

        this.buildTp = buildView.clone();
        this.buildTp.setY(214);
    }

    public void generateAndTeleportToBuild() {
        later(this::build, 20L);
    }

    public void removeAndTeleportFromBuild() {
        later(this::cleanup, 20L * 20); // 20 seconds
    }

    private void build() {
        teleportToBuild();
        World world = buildView.getWorld();

        // Floor and structure
        place(world, SchlongFloor.blocks(), recorderMain);
        place(world, SchlongShape.blocks(), recorderMain);

        // Blocks affected by physics after everything else
        place(world, SchlongTorches.blocks(), recorderDecor);
    }


    private void cleanup() {
        TrollManager.deactivate(target.getUniqueId(), TrollType.SCHLONG);
        SchlongManager.setCanSchlong(true);

        recorderDecor.clear();
        recorderMain.clear();
        teleportBack();
    }

    private void teleportToBuild() {
        target.teleport(buildTp);
    }

    private void teleportBack() {
        target.teleport(before);
    }

    private void place(World world, Iterable<RelativeBlock> blocks, StructureRecorder recorder) {
        int bx = buildView.getBlockX();
        int by = buildView.getBlockY();
        int bz = buildView.getBlockZ();

        for (RelativeBlock rb : blocks) {
            Block block = world.getBlockAt(bx + rb.x(), by + rb.y(),bz + rb.z());
            recorder.record(block);
            block.setType(rb.material());
        }
    }
}
