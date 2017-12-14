package me.forxx.springboot4kt

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableCaching
@MapperScan("me.forxx.springboot4kt.dao")
@EnableScheduling
class Springboot4ktApplication

fun main(args: Array<String>) {
    SpringApplication.run(Springboot4ktApplication::class.java, *args)
}


