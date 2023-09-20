package readingroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import readingroom.common.JwtInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor()) // 配置jwt的拦截规则
                .addPathPatterns("/**") //拦截所有路径
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/blog/list")
                .excludePathPatterns("/comment/getComment")
                .excludePathPatterns("/blog/search")
                .excludePathPatterns("/blog/getLikeDetail")
                .excludePathPatterns("/blog/searchByKeyword");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
