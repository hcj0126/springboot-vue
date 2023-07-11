package com.hcj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan({"com.hcj.dao"})
@EnableCaching
public class ServerApplication {

    public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  快递物流管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
				" .-------.       ____     __        \n" +
				" |  _ _   \\      \\   \\   /  /    \n" +
				" | ( ' )  |       \\  _. /  '       \n" +
				" |(_ o _) /        _( )_ .'         \n" +
				" | (_,_).' __  ___(_ o _)'          \n" +
				" |  |\\ \\  |  ||   |(_,_)'         \n" +
				" |  | \\ `'   /|   `-'  /           \n" +
				" |  |  \\    /  \\      /           \n" +
				" ''-'   `'-'    `-..-'              ");
	}

}
