package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.service.IServeService;
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
@RequestMapping("/serve")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class ServeController {
   @Autowired
    private IServeService serveService;
   public ServeController(){

   }
   @GetMapping("/all")
    public ResponseEntity<Result<List<Serve>>> getserve(){
       List<Serve> list = this.serveService.list();
       Result<List<Serve>> result = Result.success(list,"查询成功");
       return ResponseEntity.ok(result);
    }

   @PostMapping("/add")
    public ResponseEntity<Result<Serve>> addserve(@RequestBody Serve serve){
      boolean success=this.serveService.save(serve);
      if (success){
          return ResponseEntity.ok(Result.success(serve,"新添失败"));
      }else{
          return ResponseEntity.ok(Result.fail("注册失败"));
      }
   }

   @PostMapping("/dele")
    public ResponseEntity<Result<Serve>> deleserve(@RequestBody Serve serve){
       String serveid = serve.getServeid();
       boolean success=this.serveService.delete(serveid);
       if (success){
           return ResponseEntity.ok(Result.success(serve,"删除成功"));
       }else{
           return ResponseEntity.ok(Result.fail("删除失败"));
       }
   }

}
