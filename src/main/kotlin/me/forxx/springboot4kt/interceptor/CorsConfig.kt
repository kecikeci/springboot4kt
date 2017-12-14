package me.forxx.springboot4kt.interceptor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * 允许跨域请求
 * Created by GaoMingQiang on 2017/12/12 0012 11:46.
 */
@Configuration
class CorsConfig {
    private fun buildConfig(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOrigin("*") // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*") // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*") // 3 设置访问源请求方法
        return corsConfiguration
    }
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", buildConfig()) // 4 对接口配置跨域设置
        return CorsFilter(source)
    }
}