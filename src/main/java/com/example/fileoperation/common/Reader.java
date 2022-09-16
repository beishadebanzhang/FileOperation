package com.example.fileoperation.common;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Reader {

    public static byte[] reader(String path) throws Exception {
        try (FileChannel channel = new RandomAccessFile(path, "r").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            return buffer.array();
        }
    }


}
