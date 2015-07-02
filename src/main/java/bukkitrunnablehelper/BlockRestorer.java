package bukkitrunnablehelper;

import bukkitrunnablehelper.interfaces.BlockManipulator;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A BlockRestorer class has functionality to restore blocks, affected by {@link BlockManipulator}.
 */
public class BlockRestorer {
    private Map<Pair<Block,Material>,BlockManipulator> affectedBlocks = new HashMap<Pair<Block, Material>,BlockManipulator>();

    /**
     * Stores a block in the list of affected blocks. If the block is already in the list, the manipulator of that
     * block is updated to the given manipulator.
     */
    public void storeBlockState(Block block,BlockManipulator manipulator){
        if(blockIsStored(block)){
            updateManipulator(block, manipulator);
        }else{
            affectedBlocks.put(new Pair<Block, Material>(block,block.getType()),manipulator);
        }
    }

    /**
     * Removes the block state of the list of affected blocks if the given manipulator is authorized to do so.
     * A manipulator is authorized to remove a block from the list of affected blocks, if it is the latest
     * manipulator of the given block.
     */
    private void removeBlockState(Block block, BlockManipulator manipulator){
        if(blockIsStored(block) && isAuthorizedManipulator(block, manipulator)){
            affectedBlocks.remove(getPairFromBlock(block));
        }
    }

    /**
     * Calls {@link BlockRestorer#storeBlockState(Block, BlockManipulator)} for each block in the given Iterable of blocks.
     */
    public void storeBlockStates(Iterable<Block> affectedBlocks, BlockManipulator manipulator){
        for(Block block : affectedBlocks){
            storeBlockState(block, manipulator);
        }
    }

    /**
     * Calls {@link BlockRestorer#removeBlockState(Block, BlockManipulator)} for each block in the given Iterable of blocks.
     */
    private void removeBlockStates(Iterable<Block> affectedBlocks, BlockManipulator manipulator){
        for(Block block : affectedBlocks){
            removeBlockState(block, manipulator);
        }
    }

    /**
     * Restores the blocks which were last affected by the given manipulator and removes the blocks from the list.
     */
    public void restoreBlocks(BlockManipulator manipulator){
        for(Pair<Block,Material> pair :  getAffectedBlocksFromManipulator(manipulator)){
            pair.getKey().setType(pair.getValue());
            affectedBlocks.remove(pair);
        }
    }

    private Iterable<Pair<Block, Material>> getAffectedBlocksFromManipulator(BlockManipulator manipulator) {
        Collection<Pair<Block,Material>> pairs = new ArrayList<Pair<Block,Material>>();
        for(Map.Entry<Pair<Block,Material>,BlockManipulator> entry : affectedBlocks.entrySet() ){
            if(entry.getValue() == manipulator){
                pairs.add(entry.getKey());
            }
        }
        return pairs;
    }

    /**
     * Checks whether the given block is linked to the given manipulator in the map.
     */
    private boolean isAuthorizedManipulator(Block block, BlockManipulator manipulator) {
        return affectedBlocks.get(getPairFromBlock(block)) == (manipulator);
    }

    /**
     * Assumes that the given block is stored as an affected block and updates its latest manipulator.
     */
    private void updateManipulator(Block block, BlockManipulator manipulator) {
        affectedBlocks.put(getPairFromBlock(block), manipulator);
    }

    private boolean blockIsStored(Block block) {
        return getPairFromBlock(block) != null;
    }

    /**
     * Returns the pair in the map which contains the given block. null otherwise.
     */
    private Pair<Block, Material> getPairFromBlock(Block block) {
        for(Pair<Block,Material> pair : affectedBlocks.keySet()){
            if(pair.getKey().equals(block)){
                return pair;
            }
        }
        return null;
    }
}
