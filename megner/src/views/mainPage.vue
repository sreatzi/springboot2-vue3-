<template>
  <input type="checkbox"  id="check">
  <label for="check">
      
  </label>
  <div class="conter">
    <img  class="login-two-ball1" src="https://assets.codehub.cn/micro-frontend/login/4bcf705dad662b33a4fc24aaa67f6234.png">
      <img class="login-one-ball1" src="https://assets.codehub.cn/micro-frontend/login/fca1d5960ccf0dfc8e32719d8a1d80d2.png">
      <div class="sidebar">
          <header>酒店管理系统</header>
          <ul v-for="(item, index) in menuList" :key="index" @click="setConm(index)">
        <li>
          <a href="#">
            <img :src="imageList[index]" alt="" />
            <i>{{ item }}</i>
          </a>
        </li>
      </ul>
      </div>
      <div class="headernav">
          <nav class="navbar-word-1">
        <p>
          <img src="./登龙.png" alt="">
          <span class="word-a" href="#">前台管理</span>
        </p>
  
        
      </nav>
      <div class="po move-right">
        <span class="word-op">账号:{{ user }}</span>
        <img src="./头像.jpg" alt="">
     </div>
      </div>
  
  </div>
  
    <div class="all-box">
  <div class="container-demo">
    <router-view></router-view> 
  </div>
  </div>
  
  
  <div>
  
  </div>
  
  
  </template>
  <script>
  // import { datag } from '@/datag.ts';
  import { onMounted, ref } from 'vue';
  import router from '@/router';
  import Cookies from 'js-cookie'
  export default {
      setup(){
        // localStorage.clear()
        const user = localStorage.getItem('admin')
        
        const tkk = async()=>{
      const takelString = localStorage.getItem('mytoken');
      if (takelString) {
        const takelObject = JSON.parse(takelString);
        console.log(takelObject);
      } else {
        router.push('/')
        console.log('No takel value found in localStorage.');
      }
    }
    const checkCookies = () => {
      const data = Cookies.get('data');
      if (data) {
        console.log("Cookies中存在值：" + data)
        Cookies.remove('data');
      } else {
        console.log("Cookies中不存在值")
        router.push('/')
      }
    }
    window.onload = checkCookies;
    onMounted(()=>{tkk()})
          const menuList=["首页","房间", "服务", "顾客","信息","消费"];
          const imageList=[
          "https://img.icons8.com/cotton/20/undefined/computer.png",
          "https://img.icons8.com/cotton/20/undefined/home.png",
          "https://img.icons8.com/stickers/20/undefined/photo-album.png",
          "https://img.icons8.com/fluency/20/undefined/spiral-bound-booklet.png",
          "https://img.icons8.com/ultraviolet/20/undefined/speech-bubble-with-dots.png",
          "https://img.icons8.com/ultraviolet/20/undefined/money.png",
            ];
  
        const setConm = async (index) => {
          switch(index){
          case 0:
          router.push('/about/font')
            break;
            case 1:
            router.push('/about/roomPage')
            break;
            case 2:
            router.push('/about/servePage')
            break;
            case 3:
            router.push('/about/userPage')
            break;
            case 4:
            router.push('/about/inforPage')
            break;
            case 5:
            router.push('/about/payPage')
            break;
            default:
            this.Component = "";
          }
      };
      // onMounted(()=>{setConm()})
          return{
            user,
            tkk,
            // allstu,
            checkCookies,
             setConm,
              imageList,
              menuList,
          }
      }
  }
  </script>
  <style scoped>
  @import "./css/font.css";
  </style>
  