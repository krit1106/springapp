package java.spring.config;

import java.spring.dao.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScanBeanDefinitionParser;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter{
		@Bean
		public ViewResolver getViewResolver(){
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/views");
			resolver.setSuffix(".jsp");
			return resolver;
			
		}
		
		
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry){
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
		
		@Bean
		public DataSource getDataSource(){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
			dataSource.setUsername("root");
			dataSource.setPassword("consultadd");
			return dataSource;
			
		}
		
		@Bean
		public ContactDAO getContactDAO(){
			return new ContactDAOImpl(getDataSource());
		}
}
