<template>
    <lay-space :size="40"  style="margin-top: 20px;margin-left: 20px;"> 
    <lay-badge theme="orange" :value="value30">
     <lay-avatar @click="book" style="background-color: rgb(100, 100, 100);cursor: pointer;">住宿</lay-avatar>
    </lay-badge>
    <lay-badge  theme="blue" :value="invalue">
     <lay-avatar @click="infor" style="background-color: rgb(100, 100, 100);cursor: pointer;">记录</lay-avatar>
    </lay-badge>
    <lay-badge theme="cyan" :value="pinjiavalue">
     <lay-avatar @click="pingjia" style="background-color: rgb(100, 100, 100); cursor: pointer;">评价</lay-avatar>
    </lay-badge>
    </lay-space>
    
    <div style="display: flex;">
    
      <div id="myEcharts" :style="{ width: this.width, height: this.height }"></div>

        <div  style="display:flex;flex-direction: column;width: 800px; height: 600px;">
          <lay-notice-bar leftIcon="layui-icon-mute" text="让使用更方便-登龙酒店管理系统"></lay-notice-bar>
          <div style="flex: 1; margin-left: 5rem; ">
            <lay-carousel v-model="activeAnmi1" anim="updown" style="display:inline-block;width:80%;border: 1px solid gray;" :autoplay="true">
            <lay-carousel-item id="1">
      <img src="./img/背景图1.jpg" style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#009688;">
    </lay-carousel-item>
    <lay-carousel-item id="2">
      <img src="./img/背景图2.jpg" style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#5FB878;">
    </lay-carousel-item>
    <lay-carousel-item id="3">
      <img src="./img/背景图3.jpg" style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#FFB800;">
    </lay-carousel-item>
    <lay-carousel-item id="4">
      <img src="./img/背景图6.jpg" style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#FF5722;">
    </lay-carousel-item>
    </lay-carousel>
</div>
  <div style="flex: 1;">
    <lay-quote>好评数量{{ meituan.length }} 请查看详情</lay-quote>
    <lay-quote>差评数量{{ douyin.length }} 请查看详情</lay-quote>
  <div>
    
</div>
 
  </div>
  </div>
</div>
</template>


<script>
import { ref,onMounted,onUnmounted } from 'vue'
import { layer } from '@layui/layui-vue';
import * as echarts from 'echarts';
import { datag } from '@/datag.ts';
import axios from 'axios';
export default {
  setup() {  
          const allpayy = ref([])
          const bookroom = ref([])
          const lxxapi1 = async()=>{
             const lxx = datag.api +"/commit/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
              const response = await axios.get(lxx,headers);
       
              meituan.value=response.data.data.filter(item=>item.goodcommit==="好评");
              douyin.value=response.data.data.filter(item=>item.badcommit==="差评");
              response.data.data.forEach(item => {
                if (meituan.value) {
                    datag.allcommit.value.push(meituan.value);
                }else if(douyin.value){
                  datag.allcommit.value.push(douyin.value);
                }
            });
            console.log(response.data.data)
             pinjiavalue.value = datag.allcommit.value.length
             console.log("123123")
             console.log(meituan.value)
             }catch(error){
                 console.log(error)
             }
             console.log("123123")
         }
         const lxxapi3 = async()=>{
             const lxx = datag.api +"/user/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             datag.allcommit.value.splice(0, datag.allcommit.value.length);
              console.log(response.data.data)
       
             console.log(datag.allcommit.value)
             }catch(error){
                 console.log(error)
             }
         }
         const lxxapi2 = async()=>{
             const lxx = datag.api +"/recording/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             invalue.value=response.data.data.length
             console.log(response.data.data)
             console.log(invalue.value)
             }catch(error){
                 console.log(error)
             }
         }
         const lxxapi4 = async()=>{
             const lxx = datag.api +"/allpay/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             allpayy.value = response.data.data.map(item => ({
                  value: item.allmoney,
                  name: item.allid,
                }));
             console.log(response.data.data)
             console.log(invalue.value)
             }catch(error){
                 console.log(error)
             }
             console.log(allpayy.value)
             initChart();
         }

         const lxxapi5 = async()=>{
             const lxx = datag.api +"/room/all"
             const headers = {
                 method:"GET",
                 headers:{"Content-Type":"application/json",}
             }
             try{
             const response = await axios.get(lxx,headers);
             bookroom.value = [...response.data.data.filter(item=>item.roomstat=="已预订"||item.roomstat=="住宿")]
             value30.value=bookroom.value.length;
             console.log(response.data.data)
            
             }catch(error){
                 console.log(error)
             }
             console.log(allpayy.value)
             initChart();
         }
         const meituan =ref([])
         const douyin =ref([])
         const xiecheng =ref([])

         onMounted(async()=>{await lxxapi1()})
         onMounted(async()=>{await lxxapi2()})
         onMounted(async()=>{await lxxapi3()})
         onMounted(async()=>{await lxxapi4()})
         onMounted(async()=>{await lxxapi5()})

        const next = () => {
          if (active.value++ >=2) active.value = 0
        };
        const previous = () => {
          if (active.value-- ===0) active.value = 0
        };
        const Single = ref('2')
        const groupChange = (val) => {
        }
        let chart = null;
        const activeAnmi1 = ref("1")
        const activeAnmi2 = ref("1")
        const activeAnmi3 = ref("1")
        const activeAnmi4 = ref("1")
        onMounted(() => {
          initChart();
        });

        onUnmounted(() => {
          if (chart) {
            chart.dispose();
          }
        });

        function initChart() {
          chart = echarts.init(document.getElementById("myEcharts"));
          chart.setOption({
            title: {
              text: "各用户消费占比",
              left: "center",
            },
          
                  series: [
              {
                name: "人民币￥",
                type: "pie",
                radius: "55%",
                center: ["50%", "60%"],
                data: allpayy.value,
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: "rgba(0, 0, 0, 0.5)",
                  },
                },
                label: {
                  formatter: "{b}: {c}￥",
                },
              },
            ],

          });
          window.onresize = function () {
            chart.resize();
          };
        }
        const value30 = ref(0)
        const invalue= ref(0)
        const pinjiavalue = ref()
        const book = async()=>{
            layer.msg("新增住宿"+value30.value,{icon:7})
        }
        const infor = async()=>{
          layer.msg("新增信息"+invalue.value,{icon:7})
        }
        const pingjia = async()=>{
          layer.msg("新增评价"+pinjiavalue.value,{icon:7})
        }
    return {
      xiecheng,
      douyin,
      lxxapi4,
      meituan,
      lxxapi3,
      lxxapi5,
      bookroom,
      lxxapi2,
      allpayy,
      Single,
      groupChange,
      activeAnmi1,activeAnmi2,activeAnmi3,activeAnmi4,
      initChart,
      pinjiavalue,
      invalue,
      pingjia,
      infor,
      book,
      value30
    }
  }
}
</script>
<style>
#myEcharts{
    width: 50rem;
    height: 50rem;
}
</style>