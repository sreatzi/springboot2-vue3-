<template>
  <div class="all1box">
    <div class="xiubox2">
      <span>房型</span>
      <lay-select v-model="room" placeholder="请选择" style="width: 280px;" @change="roomdate">
        <lay-select-option :value="1" label="豪华房"></lay-select-option>
        <lay-select-option :value="2" label="江景房"></lay-select-option>
        <lay-select-option :value="3" label="大床房"></lay-select-option>
        <lay-select-option :value="4" label="loft复式房"></lay-select-option>
        <lay-select-option :value="5" label="双人房"></lay-select-option>
        <lay-select-option :value="6" label="总统套房"></lay-select-option>
        <lay-select-option :value="7" label="暂无"></lay-select-option>
      </lay-select>
    </div>

    <div class="xiubox1">
      <span>房间号</span>
      <lay-select v-model="roomid" placeholder="请选择" style="width: 280px;" @change="shai">
        <lay-select-option v-for="item in roomid" :value="item" :label="item" :key="item"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox1">
      <span>入住天数</span>
      <lay-input v-model="roomday" placeholder="请输入入住天数">
      </lay-input>
    </div>
    <lay-button style="display: flex; margin: 0 auto;" border="bule" @click="stusure">确定</lay-button>
  </div>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import { datag } from '@/datag.ts';
import { layer } from '@layui/layui-vue';
import { deleroom,markuproom,bookroom,delebookroom,addbookroom,updateuser,lxxalluser,updateuserroom } from '@/lxxapi/api.ts'
import axios from 'axios';

export default defineComponent({
  setup() {
    const roomday = ref(datag.singuser.value.bookinfo)
    const shai = async()=>{
      console.log(roomid.value)
      roomlili.value = [...datag.allroom.value.filter(item => item.roomid === roomid.value)]
      console.log(roomlili.value)
    }
    const lxxapi = async () => {
      const lxx = datag.api + '/room/all';
      const headers = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      };
      try {
        const response = await axios.get(lxx, headers);
        console.log(response.data.data);
        datag.allroom.value = response.data.data;
        lxxalluser.api()
      } catch (error) {
        console.log(error);
      }
    };
    onMounted(async () => {
      await lxxapi();
    });
    const roomid = ref([]);
    const allroomid =ref([])
    const roomlili = ref([])
    const roomnb =ref([])
    console.log(datag.singuser.value);
    const room = ref(null); // 初始值设为 null
    switch (datag.singuser.value.name) {
      case '豪华房':
        room.value = 1;
        break;
      case '江景房':
        room.value = 2;
        break;
      case '大床房':
        room.value = 3;
        break;
      case 'loft复式房':
        room.value = 4;
        break;
      case '双人房':
        room.value = 5;
        break;
      case '总统套房':
        room.value = 6;
        break;
      case '暂无':
        room.value = 7;
        break;
      default:
        room.value = null;
    }
    const roomdate = async () => {
  let roomtype = '';
  let allroomid = [];
  if (room.value === 1) {
    roomtype = '豪华房';
  } else if (room.value === 2) {
    roomtype = '江景房';
  } else if (room.value === 3) {
    roomtype = '大床房';
  } else if (room.value === 4) {
    roomtype = 'loft复式房';
  } else if (room.value === 5) {
    roomtype = '双人房';
  } else if (room.value === 6) {
    roomtype = '总统套房';
  }
  roomnb.value = roomtype
  allroomid = datag.allroom.value.filter(item => item.room === roomtype && item.roomstat === "未预订").map(item => item.roomid);
  roomid.value = [...allroomid];
};

    const phone = ref('');
    const name = ref(null);
    const userid = ref(null);
    const stusure = async () => {
      await lxxalluser.api()
      if (!room.value || !roomid.value) {
        layer.msg('输入不能为空', { icon: 2 });
      } else {
        const lxxdata = {
          userid1: datag.singuser.value.userid,
          roomid1: roomid.value,
          booktime: "",
        };
        console.log(lxxdata);
        const lxx = datag.api + '/book/add';
        try {
          const response = await axios.post(lxx, lxxdata);
          console.log(response);
          if (response.data.code == 20000) {
            layer.msg('住宿成功', { icon: 1 });
          }
          updateuserroom.api(datag.singuser.value.userid,roomnb.value,roomday.value,datag.singuser.value.phone)
          markuproom.api(roomid.value,roomlili.value[0].room,roomlili.value[0].msize,"已预订",roomday.value,roomlili.value[0].wifi)
           lxxalluser.api()
        } catch (error) {
          console.log(error);
          layer.msg('住宿失败', { icon: 2 });
        }
      }
    };

    return {
      shai,
      roomlili,
      lxxapi,
      roomid,
      room,
      roomdate,
      name,
      phone,
      userid,
      stusure,
      roomnb,
      roomday,
    };
  },
});
</script>

<style>
.all1box {
  width: 300px;
}

.xiubox1 span {
  margin-top: 10px;
  width: 80px;
}

.xiubox2 span {
  margin-top: 10px;
  width: 80px;
}

.xiubox1,
.xiubox2 {
  margin-bottom: 10px;
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  display: flex;
}
</style>
