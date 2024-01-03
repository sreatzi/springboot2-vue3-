import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import '@layui/layui-vue/lib/index.css'
import Layui from '@layui/layui-vue'

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(Layui)
app.mount('#app')