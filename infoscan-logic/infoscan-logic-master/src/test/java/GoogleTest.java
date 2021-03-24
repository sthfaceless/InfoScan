import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class GoogleTest {

  public static void main(String[] args) throws Exception{
    new GoogleTest().run();
  }

  private void run() throws Exception{

      URL url = new URL("http://google.com/search?q=fun");

      ReadableByteChannel readableByteChannel = Channels.newChannel(url.openConnection().getInputStream());
      ByteBuffer buffer = ByteBuffer.allocate(1024);

      WritableByteChannel writableByteChannel = Channels.newChannel(
              new FileOutputStream(new File("result.html")));
      while (readableByteChannel.read(buffer) != 0)
          writableByteChannel.write(buffer);

      readableByteChannel.close();
      writableByteChannel.close();
  }
}
