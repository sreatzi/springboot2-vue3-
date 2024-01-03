package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.sys.entity.Book;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.mapper.BookMapper;
import com.lantu.sys.mapper.ServeMapper;
import com.lantu.sys.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private RedisTemplate<String,Book>redisTemplate;
    public BookServiceImpl(){}

    public boolean saveBook(String userid1,String roomid1,String booktime){
        Book book = new Book();
        book.setUserid1(userid1);
        book.setRoomid1(roomid1);
        book.setBooktime(booktime);
        String key = "book:"+ UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(key,book,30L, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public boolean delete(String roomid1) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roomid1",roomid1);
        ((BookMapper)this.baseMapper).delete(queryWrapper);
        String keyPattern = "book:*";
        Set<String> keys = this.redisTemplate.keys(keyPattern);
        Iterator var5 = keys.iterator();

        while(var5.hasNext()){
            String key = (String) var5.next();
            Book book = (Book) this.redisTemplate.opsForValue().get(key);
            if (book !=null && book.getRoomid1().equals(roomid1)){
                this.redisTemplate.delete(key);
            }
        }
        return true;
    }
}
