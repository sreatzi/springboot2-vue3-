<template>
    <div>
      <section class="w3l-hotair-form">
        <h1></h1>
        <div class="container">
            <div class="workinghny-form-grid">
                <div class="main-hotair">
                    <div class="content-wthree">
                        <h2>前台-登陆</h2>
                        <lay-line border-style="dashed" theme="blue"></lay-line>
                            <input v-model="user" type="text" class="text" name="text" placeholder="用户名" required="" autofocus>
                            <input v-model="password" type="password" class="password" name="password" placeholder="密码" required="" autofocus>
                            <lay-line border-style="dashed" theme="blue"></lay-line>
                            <lay-checkbox value="" name="like" v-model="remember" skin="primary" label="1" @click="rem">记住密码</lay-checkbox>
                            <lay-button type="normal" class="btn" @click="loginlxx" :loading="loging" :fluid="true"
                      loadingIcon="layui-icon-loading">登陆</lay-button>
                        <p class="account">让使用更方便 <a>登龙</a></p>
                    </div>
                    <div class="w3l_form align-self">
                        <div class="left_grid_info">
                            <img src="./images/1.png" alt="" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright text-center">
            <p class="copy-footer-29">© 2023 Electronic Bull Bull Electric You Bull Cow </p>
        </div>
    </section>
    </div>
  </template>  
  <script>
  import { ref, watch, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { layer } from '@layui/layui-vue';
import Cookies from 'js-cookie'

import axios from 'axios';
export default {
  setup(){
    const stats = ref('0');
    var i = 0;
    stats.value = 0;

    const rem = async()=>{
      i++;
      if (i % 2 === 0) {
        stats.value = 0;
      } else {
        stats.value = 1;
      }
      console.log(stats.value);
    }
    
    const lxxapi = async()=>{} 
    const router = useRouter()
    const user = ref()
    const remember = ref(false)
    const loging = ref(false);
    const password = ref()
    const loginlxx = async () => {
      loging.value = true;
      const lxxapi = "http://localhost:6699/admin/user"
      const lxxdata = {
        user:user.value,
        password:password.value
      }
      const headers={
        method:'POST',
        headers:{
          "content-type":"application/json"
        },
        body:JSON.stringify(lxxdata)
      }
      try{
        const response = await axios.post(lxxapi, lxxdata);
        console.log(response.data);
        setTimeout(() => {   
          if (response.data.code===20000) 
          {
            layer.msg("登陆成功",{ icon: 1 })
            if(stats.value==0){
              console.log("不存密码")
            }else if(stats.value==1){
              console.log("存入密码")
              Cookies.set('data', response.data.data);
            }
            router.push('/about/font')
          }
          else
          {
            layer.msg("登陆失败",{ icon: 2 })
          }}, 1000);

      } catch (error) {
        console.error(error);
      }
    };

    // 页面加载时判断Cookies中是否存在值
    const checkCookies = () => {
      const data = Cookies.get('data');
      if (data) {
        console.log("Cookies中存在值：" + data)
        Cookies.remove('data');
        router.push('/about/font')
      } else {
        console.log("Cookies中不存在值")
      }
    }
    window.onload = checkCookies;

    return{
      rem,
      stats,
      remember,
      loging,
      router,
      loginlxx,
      user,
      password,
    }
  }
}
  </script>
<style scoped>
@import './css/font-awesome.min.css';
@import './css/style.css';
</style>