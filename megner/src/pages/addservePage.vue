<template>
    <div class="all1box">
      <div class="xiubox1">
      <span>服务单号</span> <lay-input 
    v-model="serveid"  type="number" :max="300" :min="0"></lay-input>
    </div>
  
    <div class="xiubox2">
      <span>服务类型</span>
      <lay-select v-model="servetype" placeholder="请选择" style="
              width: 280px;">
           
              <lay-select-option :value="2" label="接机"></lay-select-option>
              <lay-select-option :value="3" label="送机"></lay-select-option>
              <lay-select-option :value="4" label="餐食"></lay-select-option>
              <lay-select-option :value="5" label="健身"></lay-select-option>
              <lay-select-option :value="6" label="洗衣"></lay-select-option>
              <lay-select-option :value="7" label="会议"></lay-select-option>
              <lay-select-option :value="8" label="预订旅游景点"></lay-select-option>
      </lay-select>
    </div>
    <div class="xiubox2">
      <span>预算</span>
      <lay-input 
    v-model="serveall"  type="number" :max="300" :min="0"></lay-input>
     
    </div>
    <div class="xiubox1">
      <span>服务时间</span>
      <lay-date-picker v-model="servedata" placeholder="click me" allowClear></lay-date-picker>
    </div>
    <div class="xiubox1">
      <span>顾客账号</span>
      <lay-select v-model="userid" placeholder="请选择" style="width: 280px;">
        <lay-select-option v-for="item in sele" :value="item" :label="item" :key="item"></lay-select-option>
      </lay-select>
    </div>
    <lay-button style="display: flex; margin: 0 auto;" border="bule" @click="stusure">确定</lay-button>
    </div>

    </template>
    <script>
    import { ref,onMounted } from 'vue'
    import { datag } from '@/datag.ts';
    import { layer } from '@layui/layui-vue';
    import { deleroom,markuproom,bookroom,delebookroom,delebookserve,addbook,lxxallserve } from '@/lxxapi/api.ts'
    import axios from 'axios';
    export default {
      setup() {
        const lxxapi = async()=>{
              const lxx = datag.api +"/user/all"
              const headers = {
                  method:"GET",
                  headers:{"Content-Type":"application/json",}
              }
              try{
              const response = await axios.get(lxx,headers);
              console.log(response.data.data)
              datag.alluser.value = response.data.data
              datag.alluser.value.forEach(item=>{
                userid.value.push(item.userid)
              })
              sele.value = userid.value
              }catch(error){
                  console.log(error)
              }
          }
         onMounted(async()=>{await lxxapi()})
        const serveid = ref()
        const servetype = ref()
        const serveall = ref(null)
        const roomstat = ref()
        const servedata = ref(null)
        const userid = ref([])
        const sele = ref([])
        const stusure = async()=>{
            if (!serveid.value || !servetype.value || !serveall.value|| !servedata.value || !userid.value) {
            layer.msg('输入不能为空',{icon:2});
            } else {
                let roomType = '';
                switch (servetype.value) {
                case 1:
                    roomType = '接机';
                    break;
                case 2:
                    roomType = '送机';
                    break;
                case 3:
                    roomType = '餐食';
                    break;
                case 4:
                    roomType = '健身';
                    break;
                case 5:
                    roomType = '洗衣';
                    break;
                  case 6:
                    roomType = '会议';
                    break;
                    case 7:
                    roomType = '预订旅游景点';
                    break;
                    
                default:
                    roomType = '';
                }
                const lxxdata ={
                  serveid: serveid.value,
                  servetype: roomType,
                  serveall: serveall.value,
                  servedata : servedata.value,
                  userid: userid.value,
                }
                console.log(lxxdata)
                const lxx = datag.api+"/serve/add"
                try{
                    const response = await axios.post(lxx,lxxdata)
                    console.log(response)
                    if (response.data.code ==20000) {
                        layer.msg("添加成功",{icon:1})
                        lxxallserve.api()
                    }
                }catch(error){
                    console.log(error)
                }
            }
        }

        return {
          sele,
          serveid,
          servetype,
          serveall,
          servedata,
          userid,
          stusure,
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