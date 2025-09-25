package poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.shared.library.SharedService;

@ApplicationScoped
@WebListener
public class PocServletContextListener implements ServletContextListener {
  ServletContext context = null;
  @Inject
  SharedService sharedService;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Listener received servlet context.");
    this.context = sce.getServletContext();

    final var dynamicConfig =
        this.context.addServlet(HelloServlet.class.getName(), new HelloServlet(this.sharedService));
    dynamicConfig.addMapping("/hello");
    dynamicConfig.setLoadOnStartup(1);

  }

}
