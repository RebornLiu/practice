package ion;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChannelFile {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\WORKPLACE\\practice\\Base\\src\\main\\java\\ion\\ChannelFile.java");
        //File file = path.toFile();
        FileChannel channel = FileChannel.open(path);
        //long size = channel.size();
        ByteBuffer buffer = ByteBuffer.allocate(64);

        while (channel.read(buffer) != -1) {
            System.out.print(new String(buffer.array(), "utf8"));
            buffer.clear();
        }
    }
}
