<template>
  <lay-line contentPosition="left" theme="blue" border-style="dashed" border-width="3px">服务详情</lay-line>
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
          <lay-button size="sm" @click="remove">完成服务</lay-button>
              <lay-select v-model="value" placeholder="服务类型" style="margin-left: 10px;
              width: 150px;" @change="pairoom">
              <lay-select-option :value="8" label="全部服务类型"></lay-select-option>
              <lay-select-option :value="1" label="接机"></lay-select-option>
              <lay-select-option :value="2" label="送机"></lay-select-option>
              <lay-select-option :value="3" label="餐食"></lay-select-option>
              <lay-select-option :value="4" label="健身"></lay-select-option>
              <lay-select-option :value="5" label="洗衣"></lay-select-option>
              <lay-select-option :value="6" label="会议"></lay-select-option>
              <lay-select-option :value="7" label="预订旅游景点"></lay-select-option>
      </lay-select></div>
         
        </template>
        <template v-slot:operator="{ row }">
          <lay-button size="xs" type="normal" @click="dele(row)">不通过</lay-button>
          <lay-button size="xs" @click="leveRoom(row)">通过</lay-button>
        </template>
      </lay-table>
    </div>
    </template>
    
    <script>
    import { ref, watch, reactive,onMounted,h, } from 'vue';
    import { layer } from '@layui/layui-vue';
    import axios from 'axios';
    import { datag } from '@/datag.ts';
    import { deleroom,markuproom,bookroom,delebookroom,delebookserve,addbook,lxxallserve } from '@/lxxapi/api.ts'
    import alert from './addservePage.vue'
    export default {
    
      setup() {
        const date =ref()
          const dele = async(row)=>{
          await  delebookserve.api(row.serveid)
          await lxxallserve.api()
          }
          function formatDate(year, month, day) {
            const dateObj = new Date(year, month - 1, day);  // 月份需要减去 1，因为月份是从 0 开始计数的
              const formatted = dateObj.toISOString().split('T')[0];  // 转换成 "yyyy-mm-dd" 格式
              return formatted;
      }
          const leveRoom = async(row) =>{
            lxxallserve.api()
            delebookserve.api(row.serveid)
            const year = row.servedata[0];  // 年份
            const month = row.servedata[1]; // 月份
            const day = row.servedata[2];   // 日
            const formattedDate = formatDate(year, month, day);  // 格式化日期
            date.value=formattedDate
            addbook.api(row.serveid,row.serveall,date.value,row.userid)
          await lxxallserve.api()
      }
          const chekguke= async(row)=>{
            bookroom.api()
            console.log(datag.allbookroom.value)

            if (datag.allbookroom.value.length > 0 && datag.allbookroom.value[0].roomid1 == row.roomid) {
            layer.confirm("顾客账号"+datag.allbookroom.value[0].userid1+"预订时间"+datag.allbookroom.value[0].booktime,{ title: '查看顾客' });
          } else {
            layer.msg("暂无顾客预订",{icon:7});
          }
        }

        const pairoom = async()=>{
          console.log(value.value)
          if(value.value==1){
            const stast = "接机"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }else if(value.value==2){
            const stast = "送机"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==3){
            const stast = "餐食"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==4){
            const stast = "健身"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==5){
            const stast = "洗衣"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==6){
            const stast = "会议"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==7){
            const stast = "预订旅游景点"
            dataSource.value =[...datag.allserve.value.filter(roomstat=>roomstat.servetype===stast)]
          }
          else if(value.value==8){
            const stast = "全部房间"
            dataSource.value=[]
            lxxallserve.api()
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
       
        const finshserve=ref([])
        const bookRoom = async(row) =>{
            markuproom.api(row.roomid,row.room,row.msize,"预订",row.airconditioning,row.wifi)
        }
        const remove = async() => {
          dataSource.value=[]
          console.log(selectedKeys.value)
          selectedKeys.value.forEach(item=>{
            finshserve.value=[...datag.allserve.value.filter(serveid=>serveid.serveid===item)]
            finshserve.value.forEach(item=>{
            delebookserve.api(item.serveid)
            const year = item.servedata[0];  // 年份
            const month =item.servedata[1]; // 月份
            const day = item.servedata[2];   // 日
            const formattedDate = formatDate(year, month, day);  // 格式化日期
           
            date.value=formattedDate
           
            addbook.api(item.serveid,item.serveall,date.value,item.userid)
            })
              lxxallserve.api()
            console.log(finshserve.value)
            
          })
        
          lxxallserve.api()
        }
        const lxxapi = async()=>{
            const lxx = datag.api +"/serve/all"
            const headers = {
                method:"GET",
                headers:{"Content-Type":"application/json",}
            }
            try{
            const response = await axios.get(lxx,headers);
            console.log(response.data.data)
            datag.allserve.value = response.data.data 
            dataSource.value = datag.allserve.value
            }catch(error){
                console.log(error)
            }
        }
        watch(datag.allserve,async()=>{
          if(datag.allserve.value===dataSource.value){
            console.log("一样的")
          }else{
            console.log("不一样")
           await lxxapi()
          }
          
        })
        
       onMounted(async()=>{await lxxapi()})
        const dataSource = ref([])
        const loading = ref(false);
        const selectedKeys = ref([]);
        const page = reactive({ current: 1, limit: 10, total: 100 });
        const columns = ref([
          { title:"选项", width: "55px", type: "checkbox", fixed: "left" },
          { title:"服务单号", width: "80px", key:"serveid", fixed: "left", sort: "desc" },
          { title:"服务类型", width: "80px", key:"servetype", fixed: "left", sort: "desc" },
          { title:"服务预算", width: "80px", key:"serveall", sort: "desc" },
          { title:"服务时间", width: "80px", key:"servedata"},
          { title:"顾客id", width: "120px", key:"userid" },
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
          finshserve,
          formatDate,
          date,
          dele,
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