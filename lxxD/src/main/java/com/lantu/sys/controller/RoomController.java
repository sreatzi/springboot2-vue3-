package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Admin;
import com.lantu.sys.entity.Room;
import com.lantu.sys.entity.Serve;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.RoomMapper;
import com.lantu.sys.service.IAdminService;
import com.lantu.sys.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@RequestMapping({"/room"})
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:5173"}
)
public class RoomController {
    @Autowired
    private IRoomService roomService;
    private RedisTemplate<String, Room> redisTemplate;
    public RoomController(){
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<Room>>> getAllAdmin() {
        List<Room> list = this.roomService.list();
        Result<List<Room>> result = Result.success(list, "查询成功");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/markup")
    public ResponseEntity<Result<Room>> uproom(@RequestBody Room room){
        String roomid = room.getRoomid();
        boolean success = this.roomService.updateRoom(
                roomid,
                String.valueOf(room.getRoom()),
                String.valueOf(room.getWifi()),
                String.valueOf(room.getRoomstat()),
                String.valueOf(room.getAirconditioning()),
                String.valueOf(room.getMsize())
        );
      if (success){
          return ResponseEntity.ok(Result.success(room,"编辑成功"));
      }else{
          return ResponseEntity.ok(Result.fail("编辑失败"));
      }
    }
   @PostMapping("/add")
    public ResponseEntity<Result<Room>> addroom(@RequestBody Room room){
        boolean success = this.roomService.save(room);
        if (success){
            return ResponseEntity.ok(Result.success(room,"新添房间成功"));
        }else{
            return ResponseEntity.ok(Result.fail("添加失败"));
        }
   }

   @PostMapping("/dele")
   public ResponseEntity<Result<Room>> deleroom(@RequestBody Room room){
        String roomid = room.getRoomid();
        boolean success = this.roomService.delete(roomid);
        if(success){
            return ResponseEntity.ok(Result.success(room,"删除成功"));
        }else {
            return ResponseEntity.ok(Result.fail("删除失败"));
        }
   }

}

