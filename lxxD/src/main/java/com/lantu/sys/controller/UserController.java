package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Room;
import com.lantu.sys.entity.User;
import com.lantu.sys.service.IAdminService;
import com.lantu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sreat
 * @since 2023-12-18
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class UserController {
    @Autowired
    private IUserService userService;

    public UserController(){
    }
    @GetMapping("/all")
    public ResponseEntity<Result<List<User>>> getuser(){
        List<User> list = this.userService.list();
        Result<List<User>> result = Result.success(list,"查询成功");
        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<Result<User>> adduser(@RequestBody User user){
        boolean success = this.userService.save(user);
        if (success) {
            return ResponseEntity.ok(Result.success(user, "注册成功"));
        } else {
            return ResponseEntity.ok(Result.fail("注册失败"));
        }
    }
    @PostMapping("/markup")
    public ResponseEntity<Result<User>> uproom(@RequestBody User user){
        String userid = user.getUserid();
        boolean success = this.userService.upalldateUser(
                userid,
                String.valueOf(user.getName()),
                String.valueOf(user.getPhone())
        );
        if (success){
            return ResponseEntity.ok(Result.success(user,"编辑成功"));
        }else{
            return ResponseEntity.ok(Result.fail("编辑失败"));
        }
    }
    @PostMapping("/update")
    public ResponseEntity<Result<User>> upuser(@RequestBody User user){
        String userid = user.getUserid();
        boolean success = this.userService.updateUser(
                userid,
                String.valueOf(user.getName()),
                String.valueOf(user.getPhone()),
                String.valueOf(user.getBookinfo())
        );
        if (success){
            return ResponseEntity.ok(Result.success(user,"编辑成功"));
        }else{
            return ResponseEntity.ok(Result.fail("编辑失败"));
        }
    }
    @PostMapping("/comit")
    public ResponseEntity<Result<User>> commtroom(@RequestBody User user){
        String userid = user.getUserid();
        boolean success = this.userService.updateUser(userid,user.getBookinfo());
        if (success){
            return ResponseEntity.ok(Result.success(user,"评论成功"));
        }else {
            return ResponseEntity.ok(Result.fail("评论失败"));
        }
    }

    @PostMapping("/dele")
    public ResponseEntity<Result<User>> deleUser(@RequestBody User user){
        String userid = user.getUserid();
        boolean success = this.userService.delete(userid);
        if (success){
            return ResponseEntity.ok(Result.success(user,"删除成功"));
        }else{
            return ResponseEntity.ok(Result.fail("删除失败"));
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<Map<String,Object>> user(@RequestBody User user){
        Map<String,Object> data = this.userService.user(user);
        return data != null ? Result.success(data) : Result.fail(2000,"用户名或密码错误");
    }

}
