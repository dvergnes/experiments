package experiments.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "experiments", excludeFilters = { @Filter(Configuration.class) })
@ImportResource("/WEB-INF/spring/repository.xml")
@EnableTransactionManagement
public class MainConfig {

}
