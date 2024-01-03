<template>
    <div class="all1box">
      <div class="xiubox1">
        <span>顾客id</span>
        <lay-input v-model="serveid" type="number" :max="300" :min="0"></lay-input>
      </div>
      <div class="xiubox1">
        <span>注册时间</span>
        <lay-date-picker v-model="servedata" placeholder="请选择日期" allowClear></lay-date-picker>
      </div>
  
      <div class="xiubox1">
        <span>手机号</span>
        <lay-input v-model="userid" type="tel"></lay-input>
      </div>
  
      <lay-button style="display: flex; margin: 0 auto;" border="bule" @click="stusure">确定</lay-button>
    </div>
  </template>
  
  <script>
  import { defineComponent, ref } from 'vue';
  import { datag } from '@/datag.ts';
  import { layer } from '@layui/layui-vue';
  import axios from 'axios';
  import { deleroom,deleuser,bookroom,delebookroom,lxxalluser,addbook } from '@/lxxapi/api.ts'
  export default defineComponent({
    setup() {
      const serveid = ref(null);
      const serveall = ref('');
      const servedata = ref(null);
      const userid = ref('');
  
      const stusure = async () => {
        if (!serveid.value || !servedata.value || !userid.value) {
          layer.msg('输入不能为空', { icon: 2 });
        } else {
          const lxxdata = {
            userid: serveid.value,
            name: "暂无",
            phone: userid.value,
            roomdata: servedata.value,
            bookinfo: '无',
          };
          console.log(lxxdata);
          const lxx = datag.api + '/user/add';
          try {
            const response = await axios.post(lxx, lxxdata);
            console.log(response);
            if (response.data.code == 20000) {
              layer.msg('添加成功', { icon: 1 });
              lxxalluser.api()
            }
          } catch (error) {
            console.log(error);
            layer.msg('添加失败', { icon: 2 });
          }
        }
      };
  
      return {
        serveid,
        serveall,
        servedata,
        userid,
        stusure,
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
  