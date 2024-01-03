package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Recording;
import com.lantu.sys.service.IRecordingService;
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
@RequestMapping("/recording")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class RecordingController {
      @Autowired
     private IRecordingService recordingService;

      @GetMapping("/all")
      public ResponseEntity<Result<List<Recording>>> getAllre(){
          List<Recording> list = recordingService.list();
          Result<List<Recording>> result = Result.success(list, "查询成功");
          return ResponseEntity.ok(result);
      }

    @PostMapping("/add")
    public ResponseEntity<Result<Recording>> addrecording(@RequestBody Recording recording){
          boolean success = this.recordingService.save(recording);
          if(success){
              return ResponseEntity.ok(Result.success(recording,"添加记录成功"));
          }else{
              return ResponseEntity.ok(Result.fail("添加失败"));
          }

    }
    @PostMapping("/dele")
    public ResponseEntity<Result<Recording>> delerecrding(@RequestBody Recording recording){
          String userid = recording.getUserid();
          boolean success = this.recordingService.delete(userid);
          if (success){
              return ResponseEntity.ok(Result.success(recording,"结账完成"));
          }else{
              return ResponseEntity.ok(Result.fail("结账失败"));
          }

    }


}
