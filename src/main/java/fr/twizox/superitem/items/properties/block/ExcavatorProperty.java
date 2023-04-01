package fr.twizox.superitem.items.properties.block;

import fr.twizox.superitem.SuperItems;
import fr.twizox.superitem.items.properties.ItemProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;

import java.util.List;

public class ExcavatorProperty implements ItemProperty<BlockBreakEvent> {

    private static final String BLOCK_METADATA = "excavate-skip";
    private final int radius, depth;
    private final List<Material> materials;

    public ExcavatorProperty(int radius, int depth, List<Material> materials) {
        this.radius = radius;
        this.depth = depth;
        this.materials = materials;
    }

    public int getRadius() {
        return radius;
    }

    public int getDepth() {
        return depth;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    @Override
    public ItemProperty<BlockBreakEvent> deserialize(ConfigurationSection section) {
        int radius = section.getInt("radius", 1);
        int depth = section.getInt("depth", 0);
        List<Material> materials = section.getStringList("materials").stream().map(Material::valueOf).toList();
        return new ExcavatorProperty(radius, depth, materials);
    }


    @Override
    public void handle(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (!materials.contains(block.getType()) || block.hasMetadata(BLOCK_METADATA)) return;

        Player player = event.getPlayer();
        RayTraceResult rayTraceResult = player.rayTraceBlocks(5);
        if (rayTraceResult == null) return;

        BlockFace face = rayTraceResult.getHitBlockFace();
        if (face == null) return;

        int depthAxis = getDepthAxis(face);
        int incrementValue = getIncrementValue(face);

        int[] minOffset = {-radius, -radius, -radius};
        int[] maxOffset = {radius, radius, radius};
        int[] offset = {1, 1, 1};
        minOffset[depthAxis] = 0;
        maxOffset[depthAxis] = depth * incrementValue;
        offset[depthAxis] = incrementValue;


        final ItemStack itemStack = player.getInventory().getItemInMainHand();

        for (int x = minOffset[0]; x != maxOffset[0] + offset[0]; x += offset[0]) {
            for (int y = minOffset[1]; y != maxOffset[1] + offset[1]; y += offset[1]) {
                for (int z = minOffset[2]; z != maxOffset[2] + offset[2]; z += offset[2]) {
                    Block relativeBlock = block.getRelative(x, y, z);
                    if (materials.contains(relativeBlock.getType()) && !relativeBlock.equals(block)) {
                        breakBlock(relativeBlock, itemStack, player);
                    }
                }
            }
        }

    }

    private static void breakBlock(Block block, ItemStack itemStack, Player player) {
        block.setMetadata(BLOCK_METADATA, new FixedMetadataValue(JavaPlugin.getPlugin(SuperItems.class), true));
        BlockBreakEvent blockBreakEvent = new BlockBreakEvent(block, player);
        Bukkit.getPluginManager().callEvent(blockBreakEvent);

        if (!blockBreakEvent.isCancelled()) block.breakNaturally(itemStack);
        else block.removeMetadata(BLOCK_METADATA, JavaPlugin.getPlugin(SuperItems.class));
    }

    private static int getDepthAxis(BlockFace face) {
        return switch (face) {
            case UP, DOWN -> 1;
            case NORTH, SOUTH -> 2;
            case EAST, WEST -> 0;
            default -> throw new IllegalStateException("Unexpected value: " + face);
        };
    }

    private static int getIncrementValue(BlockFace face) {
        return switch (face) {
            case UP, EAST, SOUTH -> -1;
            case DOWN, WEST, NORTH -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + face);
        };
    }


}
