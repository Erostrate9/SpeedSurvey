import UserLayout from "@/layouts/UserLayout"
import BlankLayout from "@/layouts/BlankLayout"
// import BasicLayout from "@/layouts/BasicLayout"



export default [
    {
        path: '/user',
        component: UserLayout,
        redirect: '/user/login',
        children: [
            {
                path: 'login',
                name: 'login',
                component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
            },
            {
                path: 'register',
                name: 'register',
                component: () => import(/* webpackChunkName: "user" */ '@/views/user/Register')
            },
            {
                path: 'recover',
                name: 'recover',
                component: () => import(/* webpackChunkName: "user" */ '@/views/user/Register')
            }, {
                path: 'registerResult',
                name: 'registerResult',
                component: () => import(/* webpackChunkName: "user" */ '@/views/user/RegisterResult')
            }
        ]
    },
    {
        path: '/questionnaire',
        component: BlankLayout,
        children: [
            {
                path: 'detail',
                name: 'detail',
                component: () => import(/* webpackChunkName: "user" */ '@/views/questionnaire/Detail')
            }
        ]
    }
    ,
    {
        path: '/404',
        component: () => import('@/views/exception/404')
    }

]