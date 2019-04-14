package pl.damian.zamawiam.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.damian.zamawiam.repo.RepoConfig;

@Configuration
@EntityScan(basePackageClasses = RepoConfig.class)
@EnableJpaRepositories(basePackageClasses = RepoConfig.class)
public class ServiceConfig {
}
