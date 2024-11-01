package com.example.multipledatasources.configuration;

import com.example.multipledatasources.repository.member.MemberRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackageClasses = MemberRepository.class, entityManagerFactoryRef = "entityManagerFactory")
class MemeberJPAConfiguration {}
