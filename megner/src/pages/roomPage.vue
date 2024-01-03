<template>
  <lay-line contentPosition="left" theme="blue" border-style="dashed" border-width="3px">房间详情</lay-line>
   <div style="height: 800px;">
    <lay-table 
         :resize="true"
         :height="'100%'"
         :columns="columns" 
         id="roomid"
         :loading="loading"
         :default-toolbar="true"
         :even ="true"
         :data-source="dataSource" 
         v-model:selected-keys="selectedKeys"  
         @change="change"
         @sortChange="sortChange">
       <template #status="{ row }">
         <lay-switch :model-value="row.status" @change="changeStatus($event , row)"></lay-switch>
       </template>
       <template v-slot:toolbar>
         <div style="display: flex;"> <lay-button size="sm" type="normal" @click="addroom">新增</lay-button>
         <lay-button size="sm" @click="remove">删除</lay-button>
             <lay-select v-model="value" placeholder="请选择" style="margin-left: 10px;
             width: 150px;" @change="pairoom">
             <lay-select-option :value="1" label="已预订"></lay-select-option>
             <lay-select-option :value="2" label="住宿"></lay-select-option>
             <lay-select-option :value="3" label="未预订"></lay-select-option>
             <lay-select-option :value="4" label="全部房间"></lay-select-option>
     </lay-select></div>
        
       </template>
       <template v-slot:operator="{ row }">
         <lay-button size="xs" type="normal" @click="bianj(row)">编辑</lay-button>
         <lay-button size="xs" @click="bookRoom(row)">住房</lay-button>
         <lay-button size="xs" @click="leveRoom(row)">退房</lay-button>
     
         <lay-button size="xs" @click="chekguke(row)">查看顾客</lay-button>
       </template>
     </lay-table>
   </div>
 </template>
   
   <script>
   import { ref, watch, reactive,onMounted,h } from 'vue';
   import { layer } from '@layui/layui-vue';
   import axios from 'axios';
   import { datag } from '@/datag.ts';
   import { deleroom,markuproom,bookroom,delebookroom,addbookroom,lxxallroom,lxxalluser,updateuserroom,addbookf} from '@/lxxapi/api.ts'
   import alert from './inputPage.vue'
   import fix from './fixPage.vue'
   export default {
     setup() {
        const passRoom = async(row)=>{}
       const inRoom = async(row)=>{

       }
         const bianj = async(row)=>{
           datag.singroom.value = row
           layer.open({
           type: "page",
           title: "编辑",
           content: h(fix)
         })
       }

         const chekguke= async(row)=>{
           await bookroom.api()
           console.log(row.roomstat)
           console.log(datag.allbookroom.value)
           let alluserinfo = []
           alluserinfo = [...datag.allbookroom.value.filter(item=>item.roomid1==row.roomid)]
           console.log(alluserinfo)
             if (alluserinfo.length>0 && (row.roomstat=="预订"||row.roomstat=="住宿")) {
           layer.confirm("顾客账号："+alluserinfo[0].userid1,{ title: '查看顾客' });
         }
         else {
           layer.msg("暂无顾客预订",{icon:7});
         }
       }

       const pairoom = async()=>{
         console.log(value.value)
         if(value.value==1){
           const stast = "已预订"
           dataSource.value =[...datag.allroom.value.filter(roomstat=>roomstat.roomstat===stast)]
         }else if(value.value==2){
           const stast = "住宿"
           dataSource.value =[...datag.allroom.value.filter(roomstat=>roomstat.roomstat===stast)]
         }else if(value.value==3){
           const stast = "未预订"
           dataSource.value =[...datag.allroom.value.filter(roomstat=>roomstat.roomstat===stast)]
         }else if(value.value==4){
           const stast = "全部房间"
           dataSource.value=[]
           await lxxapi()
         }
       }
       const value = ref(null);
       const addroom = async()=>{
         layer.open({
       type: "page",
       title: "新增",
       content: h(alert)
     })
       }
       const leveRoom = async(row) =>{
         await bookroom.api()
         delebookroom.api(row.roomid)
         lxxallroom.api()
         await lxxalluser.api()
         let userinfo=[]
         markuproom.api(row.roomid,row.room,"干净","未预订","暂无",row.wifi)
         await lxxapi()
         dataSource.value=[]
         console.log(datag.allbookroom.value)
         let alluserinfo = []
         alluserinfo = [...datag.allbookroom.value.filter(item=>item.roomid1==row.roomid)]
         console.log(alluserinfo)
             if (alluserinfo.length>0) {
               userinfo = [...datag.alluser.value.filter(item=>item.userid==alluserinfo[0].userid1)]
               console.log("1231233")
               console.log(userinfo)
               updateuserroom.api(userinfo[0].userid,"暂无","暂无",userinfo[0].phone)
               layer.msg("退房成功",{icon:1});
         } else {
           layer.msg("暂无顾客预订",{icon:7});
         }
         await lxxapi()
       }

       const bookRoom = async(row) =>{
          await bookroom.api()
          let currentDate = new Date();
          let year = currentDate.getFullYear();
          let month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // 月份从0开始，需要+1，且保证两位数格式
          let day = ('0' + currentDate.getDate()).slice(-2); // 保证两位数格式
          let formattedDate = `${year}-${month}-${day}`;
           let alluserinfo = []
           alluserinfo = [...datag.allbookroom.value.filter(item=>item.roomid1==row.roomid)]
         if(row.roomstat=="已预订"){
          await  markuproom.api(row.roomid,row.room,row.msize,"住宿",row.airconditioning,row.wifi)
          await addbookf.api(alluserinfo[0].userid1,row.roomid,formattedDate)
        }else if(row.roomstat=="住宿"){
          layer.msg("已有顾客住宿",{icon:7});
         }
         else{
           layer.msg("暂无顾客预订",{icon:7});
         }
          await lxxallroom.api()
       }
       const remove = async() => {
         lxxallroom.api()
         dataSource.value=[]
         selectedKeys.value.forEach(item=>{
          deleroom.api(item)
         })
         lxxallroom.api()
       }
       
       const lxxapi = async()=>{
        
           const lxx = datag.api +"/room/all"
           const headers = {
               method:"GET",
               headers:{"Content-Type":"application/json",}
           }
           try{
           const response = await axios.get(lxx,headers);
           console.log(response.data.data)
           datag.allroom.value = response.data.data 
           dataSource.value= datag.allroom.value
           }catch(error){
               console.log(error)
           }
       }
       watch(datag.allroom,()=>{
         if(datag.allroom.value===dataSource.value){
           console.log("一样的")
         }else{
           console.log("不一样")
           console.log()
           lxxapi()
         }
         
       })
       
      onMounted(async()=>{await lxxapi()})
       const dataSource = ref([])
       const loading = ref(false);
       const selectedKeys = ref([]);
       const page = reactive({ current: 1, limit: 10, total: 100 });
       const columns = ref([
         { title:"选项", width: "55px", type: "checkbox", fixed: "left" },
         { title:"房间号", width: "80px", key:"roomid", fixed: "left", sort: "desc" },
         { title:"房间", width: "80px", key:"room", sort: "desc" },
         { title:"打扫状态", width: "80px", key:"msize"},
         { title:"房间状态", width: "120px", key:"roomstat" },
         { title:"时间", width: "80px", key:"airconditioning" },
         { title:"费用", width: "80px", key:"wifi"},
         { title:"操作", width: "150px", customSlot:"operator", key:"operator", fixed: "right", ignoreExport: true }
       ]); 
       const change = (page) => {
         loading.value = true;
         setTimeout(() => {
           dataSource.value = loadDataSource(page.current, page.limit);
           loading.value = false;
         }, 1000);
       }
       const sortChange = (key, sort) => {
         layer.msg(`字段${key} - 排序${sort}, 你可以利用 sort-change 实现服务端排序`)
       }
       const changeStatus = (isChecked, row) => {
         dataSource.value.forEach((item) => {
           if(item.id === row.id) {
             layer.msg("Success", { icon: 1 }, () => {
               item.status = isChecked;
             })
           }
         })
       }
       const loadDataSource = (page, pageSize) => {
         var response = [];
         var startIndex = ((page - 1) * pageSize) + 1;
         var endIndex = page * pageSize;
         for (var i = startIndex; i <= endIndex; i++) {
             response.push({
               id:`${i}`, 
               age:"18",
               sex: "男", 
               name:`张三${i}`, 
               email: "test@qq.com",
               remark: '花开堪折直须折,莫待无花空折枝.',  
               joinTime: "2022-02-09", 
               city: "浙江杭州", 
               status: true
             })
         }
         return response;
       }
   
       return {
         inRoom,
         passRoom,
         bianj,
         chekguke,
         pairoom,
         addroom,
         value,
         bookRoom,
         leveRoom,
           lxxapi,
         columns,
         dataSource,
         selectedKeys,
         page,
         change,
         changeStatus,
         remove
       }
     }
   }
   </script>
   <style>
 .layui-line-text-left{
   background-color: rgb(241,241,241);
 }
 </style>