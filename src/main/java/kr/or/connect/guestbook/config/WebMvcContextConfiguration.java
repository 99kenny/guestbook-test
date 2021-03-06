package kr.or.connect.guestbook.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//dispatcher servelt이 읽는 설정들
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.connect.guestbook.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
		
		@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
	        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	    }
		
	    // default servlet handler를 사용하게 합니다.
		//매핑정보 없는 url 처리
	    @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	    //특정 url ('/') 컨트롤러 없이 설정
	    //index라는 뷰 보여주게 설정
	    @Override
	    public void addViewControllers(final ViewControllerRegistry registry) {
	    		System.out.println("addViewControllers가 호출됩니다. ");
	        registry.addRedirectViewController("/","/login");
	    }
	    
	    //뷰에 prefix, suffix 설정 /views/index.jsp
	    @Bean
	    public InternalResourceViewResolver getInternalResourceViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/views/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }
	    
	    //파일 업로드 설정
	    @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver getResolver() throws IOException { 
	    	CommonsMultipartResolver resolver= new CommonsMultipartResolver();
	    	
	    	//10MV
	    	resolver.setMaxUploadSize(1024 * 1024 * 10);
	    	
	    	//2MB
	    	resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
	    	
	    	//temp upload
	    	resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));
	    	resolver.setDefaultEncoding("UTF-8");
	    	return resolver;
	    }
}
