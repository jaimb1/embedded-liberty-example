package poc;

import com.ibm.wsspi.kernel.embeddable.Server;
import com.ibm.wsspi.kernel.embeddable.ServerBuilder;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

public class WebServer {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // liberty.wlp.home is either poorly documented or not documented at all
    // I discovered it through the use of CoPilot
    // It is necessary so liberty finds the directory structure it expects to find when it starts
    final String wlpHome = System.getProperty("liberty.wlp.home",
        Paths.get("target", "liberty", "wlp").toAbsolutePath().toString());

    final Server server = new ServerBuilder()
        .setName("libertyPoC")
        .setInstallDir(new File(wlpHome))
        .build();
    final Server.Result startResult = server.start().get();

    System.out.println("Liberty server started=" + startResult.successful());
    Runtime.getRuntime().addShutdownHook(new Thread(() ->
    {
      try {
        final Server.Result stopResult = server.stop().get();
        System.out.println(
            "Liberty server stopped=" + stopResult.successful());
      } catch (final Exception e) {
        System.out.println(
            "Liberty server stopped exceptionally: " + e);
      }
    }));
    Thread.currentThread().join();
  }
}
