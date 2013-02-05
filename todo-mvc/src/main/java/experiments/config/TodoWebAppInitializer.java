package experiments.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestExporterServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class TodoWebAppInitializer implements WebApplicationInitializer {
	private static final Logger logger = LoggerFactory
			.getLogger(TodoWebAppInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		logger.info("Starting web application");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.scan("experiments.config");

		servletContext
				.addListener(new ContextLoaderListener(applicationContext));

		// Register and map the dispatcher servlet
		// DispatcherServlet servlet = new
		// DispatcherServlet(applicationContext);
		// ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
		// "dispatcher", servlet);
		// dispatcher.setLoadOnStartup(1);
		// dispatcher.addMapping("/rest/*");

		RepositoryRestExporterServlet exporter = new RepositoryRestExporterServlet();

		ServletRegistration.Dynamic reg = servletContext.addServlet(
				"rest-exporter", exporter);
		reg.setLoadOnStartup(1);
		reg.addMapping("/*");

	}

}
