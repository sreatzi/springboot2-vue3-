package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Admin;
import com.lantu.sys.service.IAdminService;
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
@RequestMapping({"/admin"})
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController(){
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<Admin>>> getAllAdmin() {
        List<Admin> list = this.adminService.list();
        Result<List<Admin>> result = Result.success(list, "查询成功");
        return ResponseEntity.ok(result);
    }



    @PostMapping("/user")
    @ResponseBody
    public Result<Map<String, Object>> admin(@RequestBody Admin admin) {
        Map<String, Object> data = this.adminService.admin(admin);
        return data != null ? Result.success(data) : Result.fail(20002, "用户名或密码错误");
    }
}
