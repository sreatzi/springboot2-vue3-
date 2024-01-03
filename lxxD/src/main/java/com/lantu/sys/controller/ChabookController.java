package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Chabook;
import com.lantu.sys.entity.Commit;
import com.lantu.sys.service.IChabookService;
import com.lantu.sys.service.ICommitService;
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
 * @since 2024-01-03
 */
@Controller
@RequestMapping("/chabook")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class ChabookController {
    @Autowired
    private IChabookService chabookService;

    @GetMapping("/all")
    public ResponseEntity<Result<List<Chabook>>> getchabook(){
        List<Chabook> list = chabookService.list();
        Result<List<Chabook>> result = Result.success(list, "查询成功");

        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<Result<Chabook>> addchabook(@RequestBody Chabook chabook){
        boolean success = this.chabookService.save(chabook);
        if(success){
            return ResponseEntity.ok(Result.success(chabook,"添加记录成功"));
        }else{
            return ResponseEntity.ok(Result.fail("添加失败"));
        }
    }

}
