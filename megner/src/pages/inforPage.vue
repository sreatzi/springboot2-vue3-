<template>
    <div class="allbox">
       
    <div class="commitbox">
        <lay-tab v-model="current11">
        <lay-tab-item id="3">
        <template #title>
          好评
          <lay-icon type="layui-icon-face-smile" style="margin-left:8px"></lay-icon>
        </template>
        <lay-scroll height="600px" style="background-color: whitesmoke" thumbColor="#000000">
        <lay-container>
      <lay-row>
      <lay-col span="24">
        </lay-col>  
  <lay-quote  v-for="(n, index) in goodcommit"
      :key="n">顾客账号:{{ n.userid }}</lay-quote>
      </lay-row>
    </lay-container>
</lay-scroll>
      </lay-tab-item>


      <lay-tab-item id="4">
        <template #title>
          差评
          <lay-icon type="layui-icon-face-cry" style="margin-left:8px"></lay-icon>
        </template>
        <lay-quote  v-for="(n, index) in badcommit"
      :key="n" >顾客账号:{{ n.userid }}</lay-quote>
      </lay-tab-item>
    </lay-tab>
    </div>
    <div class="anslye">
        <div id="myEcharts" :style="{ width: this.width, height: this.height }"></div>
</div>
</div>
  </template>
  
  <script>
  import { ref,h , Fragment,resolveComponent,onMounted } from 'vue'
  import * as echarts from "echarts";
  import { datag } from '@/datag.ts';
  import axios from 'axios';
  export default {
    setup() {
      const current11 = ref("1")
      const goodcommit = ref([])
      const badcommit = ref([])

      const allcommit = ref([
        { value: 0, name: '好评' },
        { value: 0, name: '差评' }
      ])
       console.log(badcommit.value)
       const lxxapi = async () => {
  const lxx = datag.api + "/commit/all"
  const headers = {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  }
  try {
    const response = await axios.get(lxx, headers)
    datag.alluser.value = response.data.data
    goodcommit.value = [...response.data.data.filter(item => item.goodcommit == "好评")]
    badcommit.value = [...response.data.data.filter(item => item.badcommit == "差评")]
    allcommit.value = [
      { value: goodcommit.value.length, name: '好评' },
      { value: badcommit.value.length, name: '差评' }
    ]
    total.value = response.data.data.length
    initChart() // 在获取数据之后再调用 initChart()
  } catch (error) {
    console.log(error)
  }
}
          
          
         onMounted(async()=>{await lxxapi()})
      const classpo = ref([
            { value: 335, name: '差评' },
            { value: 310, name: '好评' },
        ]);
      const total = ref()
      function initChart() {
        let chart = echarts.init(document.getElementById("myEcharts"), "purple-passion");
  chart.setOption({
    title: {
      text: "评论统计",
      left: "center",
    },
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
      {
        name: "占比率",
        type: "pie",
        radius: "50%",
        center: ["50%", "50%"],
        data: allcommit.value, // 使用 classpo.value 替换原有的数据项
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)"
          }
        }
      }
    ]
  });
  window.onresize = function () {
    chart.resize();
  };
}
onMounted(() => {
        initChart();
      });
      return {
        badcommit,
        goodcommit,
        total,
        classpo,
        initChart,
        current11,
        lxxapi,
      }
    }
  }
  </script>
  <style>

  .allbox{
    display: flex;
    width: 100%;
    height: 100%;
  }
.commitbox{
 border-radius: 10px;
 border: 1px solid rgb(183, 183, 183);
 width: 50%;
}
.anslye{
   
    width: 50%;
}
#myEcharts{
    width: 50rem;
    height: 50rem;
}
</style>