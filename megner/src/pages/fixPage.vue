<template>
  <div class="all1box">
    <div class="xiubox1">
      <span>房间号</span>
      <lay-input v-model="roomid" type="number"></lay-input>
    </div>

    <div class="xiubox2">
      <span>房间</span>
      <lay-select v-model="room" placeholder="请选择" style="width: 280px;">
        <lay-select-option value="1" label="豪华房"></lay-select-option>
        <lay-select-option value="2" label="江景房"></lay-select-option>
        <lay-select-option value="3" label="大床房"></lay-select-option>
        <lay-select-option value="4" label="loft复式房"></lay-select-option>
        <lay-select-option value="5" label="双人房"></lay-select-option>
        <lay-select-option value="6" label="总统套房"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox2">
      <span>打扫状态</span>
      <lay-select v-model="msize" placeholder="请选择" style="width: 280px;">
        <lay-select-option value="1" label="干净"></lay-select-option>
        <lay-select-option value="2" label="未打扫"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox2">
      <span>房间状态</span>
      <lay-select v-model="roomstat" placeholder="请选择" style="width: 280px;">
        <lay-select-option value="1" label="未预订"></lay-select-option>
        <lay-select-option value="2" label="已预订"></lay-select-option>
        <lay-select-option value="3" label="住宿"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox1">
      <span>费用</span>
      <lay-input v-model="wifi" placeholder="请输入" style="width: 280px;"></lay-input>
    </div>
    <lay-button style="display: flex; margin: 0 auto;" border="bule" @click="stusure">确定</lay-button>
  </div>
</template>

<script>
import { ref } from 'vue';
import { datag } from '@/datag.ts';
import { layer } from '@layui/layui-vue';
import axios from 'axios';
import { deleroom,markuproom,bookroom,delebookroom,addbookroom,lxxallroom } from '@/lxxapi/api.ts'
export default {
  setup() {
    console.log(datag.singroom.value);
    const roomid = ref(datag.singroom.value.roomid);
    const room = ref('');
    const msize = ref(null);
    const roomstat = ref('');
    const wifi = ref(datag.singroom.value.wifi);

    switch (datag.singroom.value.room) {
      case '豪华房':
        room.value = '1';
        break;
      case '江景房':
        room.value = '2';
        break;
      case '大床房':
        room.value = '3';
        break;
      case 'loft复式房':
        room.value = '4';
        break;
      case '双人房':
        room.value = '5';
        break;
      case '总统套房':
        room.value = '6';
        break;
      default:
        room.value = '';
    }

    switch (datag.singroom.value.msize) {
      case '干净':
        msize.value = '1';
        break;
      case '未打扫':
        msize.value = '2';
        break;
      default:
        msize.value = null;
    }

    switch (datag.singroom.value.roomstat) {
      case '未预订':
        roomstat.value = '1';
        break;
      case '已预订':
        roomstat.value = '2';
        break;
      case '住宿':
        roomstat.value = '3';
        break;
      default:
        roomstat.value = '';
    }

    const stusure = async () => {
      if (!roomid.value || !room.value || !msize.value || !roomstat.value || !wifi.value) {
        layer.msg('输入不能为空', { icon: 2 });
      } else {
        let roomType = '';
        switch (room.value) {
          case '1':
            roomType = '豪华房';
            break;
          case '2':
            roomType = '江景房';
            break;
          case '3':
            roomType = '大床房';
            break;
          case '4':
            roomType = 'loft复式房';
            break;
          case '5':
            roomType = '双人房';
            break;
          case '6':
            roomType = '总统套房';
            break;
          default:
            roomType = '';
        }

        let roomstats = '';
        switch (roomstat.value) {
          case '1':
            roomstats = '未预订';
            break;
          case '2':
            roomstats = '已预订';
            break;
          case '3':
            roomstats = '住宿';
            break;
          default:
            roomstats = '';
        }

        const lxxdata = {
          roomid: roomid.value,
          room: roomType,
          roomstat: roomstats,
          wifi: msize.value === '1' ? '干净' : '未打扫',
          airconditioning: '暂无', // 这里需要根据实际情况进行赋值
          msize: wifi.value,
        };
        console.log(lxxdata);

        const lxx = datag.api + '/room/markup';
        try {
          const response = await axios.post(lxx, lxxdata);
          console.log(response);
          if (response.data.code === 20000) {
            layer.msg('添加成功', { icon: 1 });
            lxxallroom.api()
          }
        } catch (error) {
          console.log(error);
        }
      }
    };

    return {
      stusure,
      roomid,
      room,
      msize,
      roomstat,
      wifi,
    };
  },
};
</script>

<style>
.all1box {
  width: 300px;
}
.xiubox1 span,
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
