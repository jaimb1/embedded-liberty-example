package poc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@ApplicationScoped
@WebListener
public class InternalServletContextListener implements ServletContextListener {
  ServletContext context = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Internal application listener received servlet context.");
    this.context = sce.getServletContext();

    final var dynamicConfig = this.context.addServlet(InternalHelloServlet.class.getName(), new InternalHelloServlet());
    dynamicConfig.addMapping("/hello");
    dynamicConfig.setLoadOnStartup(1);
  }
}
