package com.lantu.sys.controller;

import com.baomidou.mybatisplus.generator.IFill;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Allpay;
import com.lantu.sys.service.IAllpayService;
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
@RequestMapping("/allpay")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class AllpayController {
    @Autowired
    private IAllpayService allpayService;

    @GetMapping("/all")
    public ResponseEntity<Result<List<Allpay>>> getAllpay(){
        List<Allpay> list = allpayService.list();
        Result<List<Allpay>> result = Result.success(list,"查询成功");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<Allpay>> addallpay(@RequestBody Allpay allpay){
        boolean success = this.allpayService.save(allpay);
        if(success){
            return ResponseEntity.ok(Result.success(allpay,"汇总成功"));
        }else{
            return ResponseEntity.ok(Result.fail("汇总失败"));
        }
    }

    @PostMapping("/dele")
    public ResponseEntity<Result<Allpay>> deleallpay(@RequestBody Allpay allpay){
        String allid = allpay.getAllid();
        boolean success = this.allpayService.delete(allid);
        if(success){
            return ResponseEntity.ok(Result.success(allpay,"汇总完成"));
        }else{
            return ResponseEntity.ok(Result.fail("结账失败"));
        }
    }
}
