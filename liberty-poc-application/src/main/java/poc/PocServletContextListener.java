package poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.shared.library.SharedService;
import poc.cdi.BindingsExtension;

@ApplicationScoped
@WebListener
public class PocServletContextListener implements ServletContextListener {
  ServletContext context = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Listener received servlet context.");
    this.context = sce.getServletContext();

    final SharedService sharedService = BindingsExtension.getInstance(SharedService.class);

    final var dynamicConfig =
        this.context.addServlet(HelloServlet.class.getName(), new HelloServlet(sharedService));
    dynamicConfig.addMapping("/hello");
    dynamicConfig.setLoadOnStartup(1);

  }

}
