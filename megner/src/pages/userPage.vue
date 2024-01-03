<template>
    <lay-line contentPosition="left" theme="blue" border-style="dashed" border-width="3px">用户详情</lay-line>
      <div style="height: 800px;">
       <lay-table 
            :resize="true"
            :height="'100%'"
            :columns="columns" 
            id="serveid"
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
            <lay-button size="sm" @click="remove">查看</lay-button>
            <lay-input style="width: 100px; height: 32px;" v-model="data1" type="text"></lay-input>
               </div>
          </template>
          <template v-slot:operator="{ row }">
            <lay-button size="xs" type="normal" @click="dele(row)">分配</lay-button>
            <lay-button size="xs" @click="leveRoom(row)">删除</lay-button>
            <lay-button size="xs" @click="notpass(row)">不通过</lay-button>
          </template>
        </lay-table>
      </div>
      </template>
      <script>
      import { ref, watch, reactive,onMounted,h } from 'vue';
      import { layer } from '@layui/layui-vue';
      import axios from 'axios';
      import { datag } from '@/datag.ts';
      import { deleroom,deleuser,bookroom,delebookroom,delebookserve,addbook,lxxalluser,updateuserroom } from '@/lxxapi/api.ts'
      import alert from './useraddPage.vue'
      import midd from './usermidPage.vue'
      export default {
        setup() {
          const notpass=async(row)=>{
            updateuserroom.api(row.userid,"暂无","暂无",row.phone)
            lxxalluser.api()
            await lxxapi()
          }
            const data1 = ref(0);
          const date =ref()
            const dele = async(row)=>{
            datag.singuser.value=row
            console.log(datag.singuser)
                layer.open({
                type: "page",
                title: "分配",
                content: h(midd)
                 })
                 
            }
        
            const leveRoom = async(row) =>{
            await deleuser.api(row.userid)
            lxxalluser.api()
            }
          const value = ref(null);
          const addroom = async()=>{
                  layer.open({
                type: "page",
                title: "新增",
                content: h(alert)
              })
          }
          const finshserve=ref([])
          const remove = async() => {
           console.log(data1.value)
           dataSource.value = [...datag.alluser.value.filter(userid=>userid.userid===data1.value)]
          }
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
              dataSource.value = datag.alluser.value
              }catch(error){
                  console.log(error)
              }
          }
          watch(datag.alluser,()=>{
          if(datag.alluser.value==dataSource.value){
            console.log("一样的")
            
          }else{
            console.log("不一样")
            console.log(dataSource.value)
            console.log(datag.alluser.value)
            lxxapi()
          }
        })
         onMounted(async()=>{await lxxapi()})
          const dataSource = ref([])
          const loading = ref(false);
          const selectedKeys = ref([]);
          const page = reactive({ current: 1, limit: 10, total: 100 });
          const columns = ref([
          
            { title:"顾客id", width: "120px", key:"userid" },
            { title:"手机号", width: "80px", key:"phone", sort: "desc" },
            { title:"预订房间", width: "80px", key:"name", fixed: "left", sort: "desc" },
            { title:"入住天数", width: "80px", key:"bookinfo"},
            { title:"预订时间", width: "80px", key:"roomdata"},
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
            notpass,
            data1,
            finshserve,
            date,
            dele,
            leveRoom,
            addroom,
            value,
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