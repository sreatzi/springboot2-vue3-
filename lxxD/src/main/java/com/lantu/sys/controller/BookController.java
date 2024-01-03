package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Book;
import com.lantu.sys.entity.Recording;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
@Controller
@RequestMapping("/book")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class BookController {
    @Autowired
    private IBookService bookService;
    public BookController(){
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<Book>>> getAllre(){
        List<Book> list = bookService.list();
        Result<List<Book>> result = Result.success(list, "查询成功");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<Book>> addbook(@RequestBody Book book){
       boolean success = this.bookService.save(book);
       if (success){
           return ResponseEntity.ok(Result.success(book,"预订失败"));
       }else{
           return ResponseEntity.ok(Result.fail("注册失败"));
       }
    }

    @PostMapping("/dele")
    public ResponseEntity<Result<Book>> deleserve(@RequestBody Book book){
        String roomid1 =book.getRoomid1();
        boolean success=this.bookService.delete(roomid1);
        if (success){
            return ResponseEntity.ok(Result.success(book,"退房成功"));
        }else{
            return ResponseEntity.ok(Result.fail("退房失败"));
        }
    }
}
