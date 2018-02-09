package me.mawood.packet.block.blocks;

import me.mawood.packet.block.Block;
import me.mawood.packet.block.InvalidBlockException;

import java.nio.ByteBuffer;

/**
 * 4 byte integer block
 */
public class IntBlock extends Block<Integer>
{
    public static final byte[] TYPE_FLAG = {0x03};
    private final int value;

    public IntBlock(byte[] data) throws InvalidBlockException
    {
        super(data);
        ByteBuffer buffer = ByteBuffer.wrap(data);
        value = buffer.getInt();
    }

    @Override
    public Integer getData()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "IntBlock{" +
                "value=" + value +
                '}';
    }
}
