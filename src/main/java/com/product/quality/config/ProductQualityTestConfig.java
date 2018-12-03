package com.product.quality.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.product.quality.service.QualityService;
import com.product.quality.service.impl.QualityServiceImpl;

@Profile("test")
@Configuration
public class ProductQualityTestConfig {

	public QualityService qualityService(){
		return Mockito.mock(QualityServiceImpl.class);
	}
}
