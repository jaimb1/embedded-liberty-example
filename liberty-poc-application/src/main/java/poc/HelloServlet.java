package poc;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shared.library.SharedService;

import java.io.IOException;

public class HelloServlet extends HttpServlet {
  final SharedService sharedService;

  public HelloServlet(final SharedService sharedService) {
    this.sharedService = sharedService;
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println(this.sharedService.getHello(HelloServlet.class.getSimpleName()));
  }
}
