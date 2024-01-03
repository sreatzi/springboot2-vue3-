package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Commit;
import com.lantu.sys.entity.Recording;
import com.lantu.sys.entity.Room;
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
 * @since 2023-12-27
 */
@Controller
@RequestMapping("/commit")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class CommitController {
      @Autowired
     private ICommitService commitService;

    @GetMapping("/all")
    public ResponseEntity<Result<List<Commit>>> getAllcommit(){
        List<Commit> list = commitService.list();
        Result<List<Commit>> result = Result.success(list, "查询成功");
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<Result<Commit>> addcommit(@RequestBody Commit commit){
        boolean success = this.commitService.save(commit);
        if(success){
            return ResponseEntity.ok(Result.success(commit,"添加记录成功"));
        }else{
            return ResponseEntity.ok(Result.fail("添加失败"));
        }
    }

    @PostMapping("/markup")
    public ResponseEntity<Result<Commit>> uproom(@RequestBody Commit commit){
        String userid = commit.getUserid();
        boolean success = this.commitService.updatecommit(
                userid,
                String.valueOf(commit.getBadcommit()),
                String.valueOf(commit.getGoodcommit())
        );
        if (success){
            return ResponseEntity.ok(Result.success(commit,"编辑成功"));
        }else{
            return ResponseEntity.ok(Result.fail("编辑失败"));
        }
    }
    @PostMapping("/dele")
    public ResponseEntity<Result<Commit>> deleroom(@RequestBody Commit commit){
        String userid = commit.getUserid();
        boolean success = this.commitService.delete(userid);
        if(success){
            return ResponseEntity.ok(Result.success(commit,"删除成功"));
        }else {
            return ResponseEntity.ok(Result.fail("删除失败"));
        }
    }
}
