package poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.shared.library.SharedService;
import poc.cdi.BindingsExtension;

@ApplicationScoped
@WebListener
public class InternalServletContextListener implements ServletContextListener {
  ServletContext context = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Internal application listener received servlet context.");
    this.context = sce.getServletContext();

    final SharedService sharedService = BindingsExtension.getInstance(SharedService.class);

    final var dynamicConfig =
        this.context.addServlet(InternalHelloServlet.class.getName(), new InternalHelloServlet(sharedService));
    dynamicConfig.addMapping("/hello");
    dynamicConfig.setLoadOnStartup(1);
  }
}
