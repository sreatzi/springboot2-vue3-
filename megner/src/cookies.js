import { createApp } from 'vue';
import { createCookies } from 'vue3-cookies';

const app = createApp(App);

// 创建 VueCookies 实例
const cookies = createCookies();

// 使用 VueCookies
app.use(cookies);

// 配置 Cookie 过期时间为7天
cookies.config('7d');

export default cookies;
