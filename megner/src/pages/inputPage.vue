<template>
    <div class="all1box">
      <div class="xiubox1">
      <span>房间号</span> <lay-input 
    v-model="roomid"  type="number" :max="300" :min="0"></lay-input>
    </div>
  
    <div class="xiubox2">
      <span>房间</span>
      <lay-select v-model="room" placeholder="请选择" style="
              width: 280px;">
              <lay-select-option :value="1" label="豪华房"></lay-select-option>
              <lay-select-option :value="2" label="江景房"></lay-select-option>
              <lay-select-option :value="3" label="大床房"></lay-select-option>
              <lay-select-option :value="4" label="loft复式房"></lay-select-option>
              <lay-select-option :value="5" label="双人房"></lay-select-option>
              <lay-select-option :value="6" label="总统套房"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox2">
      <span>打扫状态</span>
      <lay-select v-model="msize" placeholder="请选择" style="
              width: 280px;">
              <lay-select-option :value="1" label="干净"></lay-select-option>
              <lay-select-option :value="2" label="未打扫"></lay-select-option>
      </lay-select>
     
    </div>
   
    <div class="xiubox1">
      <span>费用</span>
      <lay-input v-model="wifi" placeholder="请输入" style="
              width: 280px;">
            
      </lay-input>
    </div>
    <lay-button style="display: flex; margin: 0 auto;" border="bule" @click="stusure">确定</lay-button>
    </div>

    </template>
    <script>
    import { ref } from 'vue'
    import { datag } from '@/datag.ts';
    import { layer } from '@layui/layui-vue';
    import axios from 'axios';
    import { deleroom,markuproom,bookroom,delebookroom,addbookroom,lxxallroom } from '@/lxxapi/api.ts'
    export default {
      setup() {
        const roomid = ref()
        const room = ref()
        const msize = ref(null)
        const roomstat = ref()
        const airconditioning = ref(null)
        const wifi = ref(null)
        const stusure = async()=>{
            if (!roomid.value || !room.value || !msize.value || !wifi.value) {
            layer.msg('输入不能为空',{icon:2});
            } else {
                let roomType = '';
                switch (room.value) {
                case 1:
                    roomType = '豪华房';
                    break;
                case 2:
                    roomType = '江景房';
                    break;
                case 3:
                    roomType = '大床房';
                    break;
                case 4:
                    roomType = 'loft复式房';
                    break;
                case 5:
                    roomType = '双人房';
                    break;
                case 6:
                    roomType = '总统套房';
                    break;
                default:
                    roomType = '';
                }
                const lxxdata ={
                    roomid: roomid.value,
                    room: roomType,
                    roomstat: "未预订",
                    msize : msize.value === 1 ? '干净' : '未打扫',
                    airconditioning:"暂无",
                    wifi: wifi.value,
                }
                console.log(lxxdata)
                const lxx = datag.api+"/room/add"
                try{
                    const response = await axios.post(lxx,lxxdata)
                    console.log(response)
                    if (response.data.code ==20000) {
                        layer.msg("添加成功",{icon:1})
                        lxxallroom.api()
                    }
                }catch(error){
                    console.log(error)
                }
            }
        }

        return {
            stusure,
            roomid,
            room,
            msize,
            roomstat,
            airconditioning,
            wifi
        }
      }
    }
    </script>
    <style>
    .all1box{
      width: 300px;
    }
    .xiubox1 span{
      margin-top: 10px;
      width: 80px;
    }
    .xiubox2 span{
      margin-top: 10px;
      width: 80px;
    }
  .xiubox1{
    margin-bottom: 10px;
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
    display: flex;
  }
  .xiubox2{
    margin-bottom: 10px;
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
    display: flex;
  }
  </style>