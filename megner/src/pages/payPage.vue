<template>
    <lay-line contentPosition="left" theme="blue" border-style="dashed" border-width="3px">消费详情</lay-line>
     <div style="height: 300px; ">
      <lay-table 
           :resize="true"
           :height="'100%'"
           :columns="columns" 
           id="payid"
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
           <div style="display: flex;">
           <lay-button size="sm" @click="allgo">汇总</lay-button>
           <lay-input style="width: 100px; height: 32px;" v-model="userid" type="text"></lay-input></div>
         </template>
         <template v-slot:operator="{ row }">
         
           <lay-button size="xs" @click="leveRoom(row)">不通过</lay-button>
         </template>
       </lay-table>

       <lay-line contentPosition="left" theme="blue" border-style="dashed" border-width="3px">汇总详情</lay-line>
       <lay-table 
           :resize="true"
           :height="'100%'"
           :columns="columns1" 
           id="allid"
           :loading="loading"
           :default-toolbar="true"
           :even ="true"
           :data-source="dataSource1" 
           v-model:selected-keys="selectedKeys1"  
           @change="change"
           @sortChange="sortChange">
         <template #status="{ row }">
           <lay-switch :model-value="row.status" @change="changeStatus($event , row)"></lay-switch>
         </template>
         <template v-slot:toolbar>
           <div style="display: flex;"> <lay-button size="sm" type="normal" @click="adddele1">付账</lay-button>
           <lay-button size="sm" @click="look">查看</lay-button>
           <lay-input style="width: 100px; height: 32px;" v-model="data1" type="text"></lay-input></div>
         </template>
         <template v-slot:operator="{ row }">
           <lay-button size="xs" type="normal">编辑</lay-button>
           <lay-button size="sm">退房</lay-button>
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
     import { delerecording,addall,adddele,lxxallbook,lxxallroom,lxxallpay,lxxallre } from '@/lxxapi/api.ts'
     export default {
       setup() {
        
        const adddele1 =async()=>{
          selectedKeys1.value.forEach(item=>{
            recodingpay.value = [...allpay.value.filter(item=>item.allid==selectedKeys1.value)]
          })
        }
        const data1 = ref()
        const id = ref()
        const userid= ref()
        const money = ref()
        const allid= ref([]);
        const allroominfo = ref([])
        const roompay = ref([])
        const rompayy = ref([])
        const leveRoom =async(row)=>{
          delerecording.api(row.userid)
          lxxallpay.api()
          lxxallre.api()
        }
        const look = async()=>{
          dataSource1.value = [...datag.allpay.value.filter(item=>item.allid==data1.value)]
        }
        const allgo = async()=>{
          allroominfo.value=[]
          roompay.value=[]
           await lxxallbook.api();
           await lxxallroom.api();
           allroominfo.value =[...datag.allbook.value.filter(item =>item.userid1 ==userid.value)]
           allroominfo.value.forEach(item => {
            const matchingRooms = datag.allroom.value.filter(itemm => itemm.roomid === item.roomid1);
            roompay.value.push(...matchingRooms);
          });
           console.log(allroominfo.value)
           roompay.value.forEach(item=>{
            rompayy.value.push(item.wifi)
           })
           console.log(rompayy.value)
           
            delerecording.api(userid.value)
            id.value = [...datag.allre.value.filter(item=>item.userid===userid.value)]
            let money = 0;
            id.value.forEach(item => {
                money += parseInt(item.money);
            });
            let allmoneyy = parseInt(money) + parseInt(rompayy.value[0]);
            console.log(allmoneyy);

            datag.allpay.value.forEach(item=>{
             allid.value.push(item.allid)
            })
            if(allid.value.length==0){
              addall.api(0,allmoneyy)
            }else{
              addall.api(parseInt(allid.value[allid.value.length - 1]) + 1,allmoneyy)
            }
            console.log(allid.value[allid.value.length - 1]);
            lxxallpay.api()
            lxxallre.api()
        }
         const value = ref(null);

         const lxxapi = async()=>{
             const lxx = datag.api +"/recording/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             console.log(response.data.data)
             datag.allre.value = response.data.data 
             dataSource.value= datag.allre.value
             }catch(error){
                 console.log(error)
             }
         }
         watch(datag.allre,()=>{
          if(datag.allre.value==dataSource.value){
            console.log("一样的")
          }else{
            lxxapi()
            console.log("不一样")
          }
         })
         const lxxapi1 = async()=>{
             const lxx = datag.api +"/allpay/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             datag.allpay.value = response.data.data 
             dataSource1.value = datag.allpay.value
             }catch(error){
                 console.log(error)
             }
         }
         watch(datag.allpay,()=>{
          if(datag.allpay.value==dataSource1.value){
            console.log("一样的")
          }else{
            lxxapi1()
            console.log("不一样")
          }
         })
         const columns1 = ref([
           { title:"选项", width: "55px", type: "checkbox", fixed: "left" },
           { title:"汇总单号", width: "80px", key:"allid", fixed: "left", sort: "desc" },
           { title:"总消费", width: "80px", key:"allmoney", sort: "desc" },
          
         ]);
        onMounted(async()=>{await lxxapi()})
        onMounted(async()=>{await lxxapi1()})

         const dataSource = ref([])
         const dataSource1 = ref([])
         const loading = ref(false);
         const selectedKeys = ref([]);
         const selectedKeys1 = ref([])
         const columns = ref([
          
           { title:"完成单号", width: "80px", key:"payid", fixed: "left", sort: "desc" },
           { title:"人民币", width: "80px", key:"money", sort: "desc" },
           { title:"日期", width: "80px", key:"peydata"},
           { title:"客户id", width: "120px", key:"userid" },
           { title:"操作", width: "10px", customSlot:"operator", key:"operator", fixed: "right", ignoreExport: true }
         ]);
       
         return {
          rompayy,
          roompay,
          allroominfo,
          adddele1,
          look,
          data1,
          leveRoom,
          dataSource1,
          lxxapi1,
          columns1,
          id,
          money,
          allid,
          userid,
          selectedKeys1,
           value,
           allgo,
           lxxapi,
           columns,
           dataSource,
           selectedKeys,
         }
       }
     }
     </script>
     <style>
   .layui-line-text-left{
     background-color: rgb(241,241,241);
   }
   </style>