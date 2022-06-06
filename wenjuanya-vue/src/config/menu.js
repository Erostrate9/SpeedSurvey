import BasicLayout from "@/layouts/BasicLayout"


export default [
    {
        path: '/',
        name: 'index',
        component: BasicLayout,
        redirect: '/square',

        children: [
            {
                path: 'square',
                name: 'square',
                meta: { title: '问卷广场', icon: 'home' },
                component: () => import('@/views/questionnaire/Square'),
            },
            {
                path: 'questionnaire',
                name: 'questionnaire',
                component: () => import('@/views/questionnaire/Mine'),
                meta: { title: '我的问卷', icon: 'file' },
            },
            {
                path: 'organization',
                name: 'organization',
                component: () => import('@/views/organization/Mine'),
                meta: { title: '我的组织', icon: 'team' },
                // children: [
                //     {
                //         path: 'square',
                //         name: 'square',
                //         meta: { title: '问卷广场', icon: 'home' },
                //         component: () => import('@/views/questionnaire/Square'),
                //     }
                // ]
            },
            {
                path: 'mine',
                name: 'mine',
                component: () => import('@/views/account/Center'),
                meta: { title: '个人中心', icon: 'user' },
            },
        ]
    }
]