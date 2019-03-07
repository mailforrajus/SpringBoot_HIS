package com.his;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * this class is use to initializer class
 * @author Nitish
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
    

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources( HisApplication.class);
	}

}

