package com.lantu;

import com.lantu.sys.entity.Admin;
import com.lantu.sys.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LxxDApplicationTests {

    @Resource
    private AdminMapper adminMapper;

    @Test
    void contextLoads() {
        List <Admin> logins = adminMapper.selectList(null);
        logins.forEach(System.out::println);
    }

}
