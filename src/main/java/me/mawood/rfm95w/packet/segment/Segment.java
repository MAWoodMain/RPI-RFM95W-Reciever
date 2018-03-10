package me.mawood.rfm95w.packet.segment;

import com.google.gson.JsonObject;
import me.mawood.rfm95w.packet.block.Block;

import java.util.Arrays;

public abstract class Segment
{
    public static final byte SEGMENT_FLAG = (byte) 0xAA;
    public static final int TYPE_FLAG_LENGTH = 2;

    protected final Block[] blocks;

    public Segment(Block[] blocks)
    {
        this.blocks = blocks;
    }

    public int getLength()
    {
        return 1 + TYPE_FLAG_LENGTH + Arrays.stream(blocks).mapToInt(Block::getLength).sum();
    }

    public abstract JsonObject toJson();

    public abstract String getJsonName();
}
