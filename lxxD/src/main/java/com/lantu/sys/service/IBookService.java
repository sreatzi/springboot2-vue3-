package com.lantu.sys.service;

import com.lantu.sys.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
public interface IBookService extends IService<Book> {
    boolean saveBook(String userid1,String roomid1,String booktime);

    boolean delete(String roomid1);
}
