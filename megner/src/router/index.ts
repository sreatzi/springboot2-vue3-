import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/loginPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import ('../views/mainPage.vue'),
      children:[
        {
          path:'/about/font',
          name:'fontPage',
          component:()=> import ('../pages/fontPage.vue'),
        },
        {
          path:'/about/inforPage',
          name:'inforPage',
          component:()=> import ('../pages/inforPage.vue'),
        },
        {
          path:'/about/payPage',
          name:'payPage',
          component:()=> import ('../pages/payPage.vue'),
        },
        {
          path:'/about/roomPage',
          name:'roomPage',
          component:()=> import ('../pages/roomPage.vue'),
        },
        {
          path:'/about/servePage',
          name:'servePage',
          component:()=> import ('../pages/servePage.vue'),
        },
        {
          path:'/about/userPage',
          name:'userPage',
          component:()=> import ('../pages/userPage.vue'),
        },
      ]
    }
  ]
})

export default router
