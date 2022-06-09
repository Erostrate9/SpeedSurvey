# SpeedSurvey
An online survey system based on Vue.js and SSM.

## 目录结构

README.md：项目说明

SpeedSurvey：后端项目，RESTful风格，SSM，SpringBoot，MySQL

speed-survey-vue：前端项目，Vue.js + Ant Design + Ant Design pro + Element-UI

## 需求说明

### 模块划分

1. 登录注册
2. 问卷广场
3. 问卷管理
4. 组织管理
5. 用户管理

### 角色划分

authority：0-普通用户，1-高级用户，2-管理员

### 功能要求

#### 登录注册

邮箱注册

用户名、密码、邮箱

注册后默认是普通用户0

不需要头像和备注。

#### 问卷广场

解释：所有被设置为公开（password_required\==0）且不是组织问卷（belong_organization\==0）的问卷会被展示在这里。（卡片列表）

用户在此处的操作：1.填写这些问卷 2.查看这些问卷的统计结果（统计结果指每个非隐私问题（is_private==0）的选项分布，举例：共50人答题，选项A 35/50），用户不能看到具体每一份问卷的作答情况，只能看到大数据统计结果。

#### 问卷管理

注意：设置开始时间和结束时间会导致系统需要设置定时任务，太麻烦了，为简单起见，我们先像问卷喵一样，用户手动发布，手动结束

解释：请看https://www.wjcat.com/home，登录账号eros，密码123456

用户操作：

1. 创建问卷。设置参数：问卷标题，问卷描述，是否设置限制ip，访问密码（若为空则为无密码问卷），发布到某一组织（只有高级用户和管理员才可见该设置项）
2. 设置问卷。设置参数：问卷标题，问卷描述，是否设置限制ip，访问密码（若为空则为无密码问卷），发布到某一组织（只有高级用户和管理员才可见该设置项）

3. 发布问卷。选择某一问卷，点击发布问卷，则问卷状态（state）由已创建（0）或已结束（2）更新为进行中（1）。

4. 设计问卷。题型为填空(0)、单选(1)、多选(2)，可设置参数有:题目标题title，是否必填(is_required)，是否隐私(is_private)

5. 预览问卷。选择某一问卷，展示该问卷的填写页面，同9。
6. 删除问卷。选择某一问卷，删除该问卷（涉及questionaire表、option表、option_result表、text_result表、ip_restrict表）
7. 分享问卷。显示该问卷填写页面的url和二维码。
8. 回答统计。查看每道题每个选项的选择数量和占比。填空题查看所有结果。前端展示图表、导出excel
9. 填写问卷。用户填写问卷，提交问卷。成功提交问卷的要求：回答了所有必填题(is_required) 且 该问卷已发布（state==1）。两种特殊情景：1.问卷设置了密码（password），需要正确输入密码，才能向后端提交问卷。2.问卷为组织内问卷(belong_organization != 0)，用户必需登录并验证身份（属于该组织），后端才能返回该问卷内容。

#### 组织管理

普通用户用例：

1. 加入组织。根据id或name搜索组织，输入正确邀请码（invite_code）后加入组织。
2. 查看组织问卷。展示发布在用户所在组织的所有问卷，以及用户是否填写过该问卷。

高级用户、管理员用例：

1. 普通用户的所有用例

2. 创建组织：设置name，description，invite_code、
3. 设置组织：修改已创建组织的name，description，invite_code
4. 查看成员列表。查看组织内所有成员的name、邮箱号
5. 删除成员。选择某一特定成员，并删除。
6. 查看组织内问卷填写情况。获取belong_organizition为该组织的所有问卷，展示每份问卷的成员填写情况（已填写者可以查看填写情况（复用填写问卷页面），未填写者显示未填写）
7. 邮件提醒，选择某一问卷，所有未填写该问卷的组织成员会受到邮件提示（问卷标题，问卷url）

#### 用户管理

只有管理员可见

修改用户name、password、authority（只能在0和1之间修改）

## 数据字典

### user

说明：用户信息表，不需要avatar和remark

| 序号 | 字段名称  | 注释                        | 必填 | 数据类型    | 主键唯一 |
| ---- | --------- | --------------------------- | ---- | ----------- | -------- |
| 1    | id        | 用户id                      | 是   | int(11)     | 是       |
| 2    | username  | 用户名                      | 是   | varchar(50) |          |
| 3    | password  | 密码                        | 是   | varchar(50) |          |
| 4    | email     | 邮箱                        | 是   | varchar(50) |          |
| 5    | authority | 0普通用户/1高级用户/2管理员 |      | int(11)     |          |

### ip_restrict

说明：被设置为ip限制的（ip_limit\==1）问卷，同一IP只能提交一次，该表用来储存（ip_limit\==1）问卷的ip（不统计用户id）

| 序号 | 字段名称         | 注释       | 必填 | 数据类型    | 主键唯一 |
| ---- | ---------------- | ---------- | ---- | ----------- | -------- |
| 1    | id               |            | 是   | int(11)     | 是       |
| 2    | questionnaire_id | 所填问卷id | 是   | int(11)     |          |
| 3    | ip               | 填写者ip   | 是   | varchar(16) |          |

### questionnaire

说明：

* 为简化流程，不需要三个time，用户手动发布问卷或停止问卷（只改变state）。
* 不需要passwrod_required字段，password为空时即为无密码问卷。
* belong_organization为所属组织id，为0或为null时为普通问卷。
* 问卷url由问卷id做处理得到，不需要加域名等前缀，实际网址由前端拼接。

| 序号 | 字段名称            | 注释                    | 必填 | 数据类型     | 主键唯一 |
| ---- | ------------------- | ----------------------- | ---- | ------------ | -------- |
| 1    | id                  | 问卷id                  | 是   | int(11)      | 是       |
| 2    | user_id             | 发布者id                | 是   | int(11)      |          |
| 3    | title               | 问卷标题                | 是   | varchar(255) |          |
| 4    | description         | 问卷描述                |      | varchar(255) |          |
| 5    | password            | 访问密码                |      | char(8)      |          |
| 6    | ip_limit            | 0不限制/1限制ip         |      | int(11)      |          |
| 7    | belong_organization | null或0普通问卷/组织id  |      | int(11)      |          |
| 8    | url                 | 问卷url                 |      | varchar(255) |          |
| 9    | state               | 0已创建/1进行中/2已结束 |      | int(11)      |          |

### question

说明：

* 该表存储所有问题。questionnaire_id是来自questionnaire_id的外键。
* 不需要create_time
* 当is_private为1时，问卷广场中不应显示该问题的统计结果

| 序号 | 字段名称         | 注释                           | 必填 | 数据类型     | 主键唯一 |
| ---- | ---------------- | ------------------------------ | ---- | ------------ | -------- |
| 1    | id               | 问题id                         | 是   | int(11)      | 是       |
| 2    | questionnaire_id | 问卷id。外键                   | 是   | int(11)      |          |
| 3    | order_number     | 问题序号。指该问卷中第几个问题 | 是   | int(11)      |          |
| 4    | title            | 标题                           | 是   | varchar(255) |          |
| 5    | type             | 0填空/1单选/2多选              | 是   | int(11)      |          |
| 6    | is_required      | 0非必填/1必填                  |      | int(11)      |          |
| 7    | is_private       | 0公开/1隐私                    |      | int(11)      |          |

### text_result

说明：

* 该表存储用户提交的填空题答案。填空题不需要额外存储题面，题面就是question表title。
* user_id仅在组织内问卷中需要填写，本系统中非组织问卷（belong_organization==0）是不需要提交登录信息的
* 不需要create_time

| 序号 | 字段名称    | 注释                                   | 必填 | 数据类型     | 主键唯一 |
| ---- | ----------- | -------------------------------------- | ---- | ------------ | -------- |
| 1    | id          | 填空题答题id                           | 是   | int(11)      | 是       |
| 2    | user_id     | 填写者的用户id。user表外键id。可以为空 | 否   | int(11)      |          |
| 3    | question_id | 问题id。question表外键id               | 是   | int(11)      |          |
| 4    | answer      | 填写者填写的回答                       | 是   | varchar(255) |          |

### option

说明：

* 本表存储所有选择题的选项，即answer表中每一个type==1或2的题目的选项。
* 需求中暂无自动批阅，故不需要is_answer
* 因为只有组内问卷需要复现每一份问卷的结果，对于公开问卷，如果也存储每一个选择题的结果增加了数据冗余，故增加一项count，

| 序号 | 字段名称     | 注释                                              | 必填 | 数据类型     | 主键唯一 |
| ---- | ------------ | ------------------------------------------------- | ---- | ------------ | -------- |
| 1    | id           | 选项id                                            | 是   | int(11)      | 是       |
| 2    | question_id  | 问题id。question表外键id                          | 是   | int(11)      |          |
| 3    | content      | 选项内容                                          | 是   | varchar(255) |          |
| 4    | order_number | 选项序号。指该question的第几个选项                | 是   | int(11)      |          |
| 5    | count        | 该选项被选择的次数。例如有5人选A，则A选项count==5 |      |              |          |

### option_result

说明：

* 该表仅存储组织内问卷的选择题统计结果；本系统中非组织问卷（belong_organization==0）选择题的结果是不需要提交到此处的。
* 不需要create_time

| 序号 | 字段名称  | 注释                   | 必填 | 数据类型 | 主键唯一 |
| ---- | --------- | ---------------------- | ---- | -------- | -------- |
| 1    | id        | 选择题答题id           | 是   | int(11)  | 是       |
| 2    | user_id   | 提交者id。user表外键id | 是   | int(11)  |          |
| 3    | option_id | 选项id。option表外键id | 是   | int(11)  |          |

### organization

| 序号 | 字段名称    | 注释                     | 必填 | 数据类型     | 主键唯一 |
| ---- | ----------- | ------------------------ | ---- | ------------ | -------- |
| 1    | id          | 组织id                   | 是   | int(11)      | 是       |
| 2    | name        | 组织名称                 | 是   | varchar(50)  |          |
| 3    | description | 组织描述                 |      | varchar(255) |          |
| 4    | logo        | 组织标志                 |      | varchar(255) |          |
| 5    | invite_code | 邀请码                   |      | varchar(50)  |          |
| 6    | owner_id    | 组织创建者，user表外键id | 是   | int(11)      |          |

### user_organization

| 序号 | 字段名称        | 注释   | 必填 | 数据类型 | 主键唯一 |
| ---- | --------------- | ------ | ---- | -------- | -------- |
| 1    | id              |        | 是   | int(11)  | 是       |
| 2    | user_id         | 用户id | 是   | int(11)  |          |
| 3    | organization_id | 组织id | 是   | int(11)  |          |

### assignment

说明：

* 该表记录组织内问卷的完成情况。
* 每当一个组织内问卷发布，在本表内添加每个成员对应的数据项，初始化is_finished为0。
* 当组织成员完成组织内问卷时，对应数据项的is_finished应更新为1。

| 序号 | 字段名称         | 注释           | 必填 | 数据类型 | 主键唯一 |
| ---- | ---------------- | -------------- | ---- | -------- | -------- |
| 1    | id               |                | 是   | int(11)  | 是       |
| 2    | user_id          | 用户id         | 是   | int(11)  |          |
| 3    | questionnaire_id | 组织内问卷id   | 是   | int(11)  |          |
| 4    | is_finished      | 0未完成，1完成 | 是   | int(1)   |          |

## 接口规范

### org_list

| 接口地址            | HTTP方法 | 传入参数              | 返回参数类型 | 功能                       |
| ------------------- | -------- | --------------------- | ------------ | -------------------------- |
| /api/org/owner/list | GET      | 从session中获取userId | json         | 获取用户**创建**的所有组织 |

返回数据格式

```json
//获取所有owner_id为session中用户id的组织
//orgId:orgnization表的id
//orgName:orgnization表的name
//msg:返回提示信息
//code: 0为成功，500为失败
{
    data:[
        {
            orgId:"@id()",
            orgName:"@cname()的组织",
        },
        {
            orgId:"@id()",
            orgName:"@cname()的组织",
        },
        {
            orgId:"@id()",
            orgName:"@cname()的组织",
        },
    ],
    msg: '',
    code: '0'
}
```

### wj_list

| 接口地址                        | HTTP方法 | 传入参数              | 返回参数类型 | 功能                       |
| ------------------------------- | -------- | --------------------- | ------------ | -------------------------- |
| /api/questionnaire/creater/list | GET      | 从session中获取userId | json         | 获取用户**创建**的所有问卷 |

返回数据格式

```json
//获取questionnaire表中所有user_id为session中用户id的组织
//id:questionnaire表的id
//title:questionnaire表的name
//desc:questionnaire表的description
//status:questionnaire表的state
//msg:返回提示信息
//code: 0为成功，500为失败
{
    "data":[
        {
            "id": "@id()",
            "title": "@cname",
            "desc":"@cname",
            "status":"@integer(0, 1)"
        },
        {
            "id": "@id()",
            "title": "@cname",
            "desc":"@cname",
            "status":"@integer(0, 1)"
        },
        {
            "id": "@id()",
            "title": "@cname",
            "desc":"@cname",
            "status":"@integer(0, 1)"
        },
    ],
    msg:"",
    code:"0"
}
```

### question_list

| 接口地址                       | HTTP方法 | 传入参数 | 返回参数类型 | 功能                   |
| ------------------------------ | -------- | -------- | ------------ | ---------------------- |
| /api/questionnaire/queryDetail | GET      | wjId     | json         | 获取某一问卷的所有问题 |

传入参数

| 参数名 | 参数类型 | 说明   |      |
| ------ | -------- | ------ | ---- |
| wjId   | int      | 问卷id |      |

返回数据格式

```json
//需要进行权限验证,session应为问卷创建者
//获取question表中所有questionnaire_id为wjId的问题
//id:	question表的id
//title:	question表的title
//type:	0填空/1单选/2多选
//row:	options的数量，最小为1
//must:	question表的is_required
//isPrivate:	question表的is_private
//options:	选择题的所有选项，填空题此处为空[]
//'radioValue': -1 ,//接收单选框的值
//'checkboxValue' : [] ,//接收多选框的值
//'textValue' : '',//接收输入框的值
//msg:返回提示信息
//code: 0为成功，500为失败
{
    data:[
        {
            title:"单选题",
            type:1,
            id:"@id()",
            row:4,
            must:"@boolean()",
            options:[
                {
                    title:"@cname()",//option表的content
                    id:"@id()"//option表的id
                },
                {
                    title:"@cname()",
                    id:"@id()"
                },
                {
                    title:"@cname()",
                    id:"@id()"
                },
                {
                    title:"@cname()",
                    id:"@id()"
                }
            ],
            'radioValue': -1 ,//接收单选框的值
            'checkboxValue' : [] ,//接收多选框的值
            'textValue' : '',//接收输入框的值
        },
        {
            title:"多选题",
            type:2,
            id:"@id()",
            row:3,
            must:"@boolean()",
            options:[
                {
                    title:"@cname()",
                    id:"@id()"
                },
                {
                    title:"@cname()",
                    id:"@id()"
                },
                {
                    title:"@cname()",
                    id:"@id()"
                }
            ],
            'radioValue' : -1 ,//接收单选框的值
            'checkboxValue' : [] ,//接收多选框的值
            'textValue' : '',//接收输入框的值
            
        },
        {
            title:"填空题",
            type:0,
            id:"@id()",
            row:"@integer(1,3)",
            must:"@boolean()",
            options:[],
            'radioValue' : -1,//接收单选框的值
            'checkboxValue' : [], //接收多选框的值
            'textValue' : '',//接收输入框的值
            
        }
    ],
    msg:"success",
    code:"0"
}
```

### data_analysis

| 接口地址           | HTTP方法 | 传入参数 | 返回参数类型 | 功能                             |
| ------------------ | -------- | -------- | ------------ | -------------------------------- |
| /api/data_analysis | GET      | wjId     | json         | 获取某一问卷的所有问题的统计结果 |

传入参数

| 参数名 | 参数类型 | 说明   | 对应字段          |
| ------ | -------- | ------ | ----------------- |
| wjId   | int      | 问卷id | questionnaire表id |

返回数据格式

```json
//获取question表中所有questionnaire_id为wjId的问题的统计结果

{
    data:[
        {
        "title": "@ctitle()",//question表title
        "type": "1",//question表type,0填空/1单选/2多选
        "questionId":"@id()",//question表id
        result:[
            {
                'option':"@ctitle()",//option表content
                'count':"@integer(0,100)",//option表count
                'percent':"@integer(0,100)%"//占该问题所有选项count之和的百分比
            },
            {
                'option':"@ctitle()",
                'count':"@integer(0,100)",
                'percent':"@integer(0,100)%"
            }
        ]
        },
        {
        "title": "@ctitle()",
        "type": "checkbox",
        result:[
            {
                'option':"@ctitle()",
                'count':"@integer(0,100)",
                'percent':"@integer(0,100)%"
            },
            {
                'option':"@ctitle()",
                'count':"@integer(0,100)",
                'percent':"@integer(0,100)%"
            }
        ],
        "questionId":"@id()"
        },
        {
        "title": "@ctitle()",
        "type": 0,
        result:'',//填空题的result返回空
        "questionId":"@id()"
        },

    ],
    msg: '',
    code: '0'
}
```

### text_answer_detail

| 接口地址         | HTTP方法 | 传入参数 | 返回参数类型 | 功能                     |
| ---------------- | -------- | -------- | ------------ | ------------------------ |
| /api/text_result | GET      | 见下表   | json         | 获取某一填空题所有的答案 |

**说明**

由于前端需要分页功能，后端要实现分页，按id排序，每页有pageSize项。

必须验证权限，session与问卷创建者id一致才能返回数据。

**传入参数**

| 参数名      | 参数类型 | 说明       | 对应字段     |
| ----------- | -------- | ---------- | ------------ |
| questionId  | int      | 问题id     | question表id |
| currentPage | int      | 当前页数   |              |
| pageSize    | int      | 每页的容量 |              |

**返回数据格式**

```json
//context:text_result表中的answer
//total:detail的length
{
    "data":{
        detail:[
            {'context': "@cname()"},
            {'context': "@cname()"},
            {'context': "@cname()"},
            {'context': "@cname()"},
            {'context': "@cname()"},
            {'context': "@cname()"},
        ],
        total:6
    },
    msg:"success",
    code:"0"
}
```

### delete_wj

| 接口地址       | HTTP方法 | 传入参数 | 返回参数类型 | 功能         |
| -------------- | -------- | -------- | ------------ | ------------ |
| /api/delete_wj | GET      | wjId     | json         | 删除某一问卷 |

**说明**

必须验证权限，session与问卷创建者id一致才能删除问卷。

**传入参数**

| 参数名 | 参数类型 | 说明   | 对应字段          |
| ------ | -------- | ------ | ----------------- |
| wjId   | int      | 问卷id | questionnaire表id |

**返回数据格式**

```json
{
    data:''
    msg:"success",
    code:"0"
}

{
    data:''
    msg:"没有权限",
    code:"500"
}

{
    data:''
    msg:"找不到该问卷",
    code:"404"
}
```

### logincheck

| 接口地址        | HTTP方法 | 传入参数 | 返回参数类型 | 功能           |
| --------------- | -------- | -------- | ------------ | -------------- |
| /api/logincheck | GET      | 无       | json         | 验证是否已登录 |

**说明**

必须验证session。

若已登录返回0，未登录返回500

**传入参数**

| 参数名 | 参数类型 | 说明 | 对应字段 |
| ------ | -------- | ---- | -------- |
|        |          |      |          |

**返回数据格式**

```json5
{
    data:''
    msg:"已登录",
    code:"0"
}

{
    data:''
    msg:"您还未登录，请登录",
    code:"500"
}
```

### push_wj

| 接口地址     | HTTP方法 | 传入参数    | 返回参数类型 | 功能              |
| ------------ | -------- | ----------- | ------------ | ----------------- |
| /api/push_wj | POST     | wjId,status | json         | 发布问卷/暂停问卷 |

**说明**

必须验证session，与问卷发布者一致才能改变文件状态。

若成功返回0，失败返回500

**传入参数**

| 参数名 | 参数类型 | 说明                                  | 对应字段             |
| ------ | -------- | ------------------------------------- | -------------------- |
| wjId   | int      | 问卷id                                | questionnaire表id    |
| status | int      | 问卷状态,0代表暂停文件，1代表发布问卷 | questionnaire表state |

**返回数据格式**

```json
{
    data:''
    msg:"问卷状态修改成功",
    code:"0"
}

{
    data:''
    msg:"问卷状态修改失败",
    code:"500"
}
```

### logincheck

| 接口地址        | HTTP方法 | 传入参数 | 返回参数类型 | 功能           |
| --------------- | -------- | -------- | ------------ | -------------- |
| /api/logincheck | GET      | 无       | json         | 验证是否已登录 |

**说明**

必须验证session。

若已登录返回0，未登录返回500

**传入参数**

| 参数名 | 参数类型 | 说明 | 对应字段 |
| ------ | -------- | ---- | -------- |
|        |          |      |          |

**返回数据格式**

```json
{
    data:''
    msg:"已登录",
    code:"0"
}

{
    data:''
    msg:"您还未登录，请登录",
    code:"500"
}
```

### add_wj

| 接口地址   | HTTP方法 | 传入参数类型 | 返回参数类型 | 功能           |
| ---------- | -------- | ------------ | ------------ | -------------- |
| /api/addwj | POST     | json         | json         | 创建或更新问卷 |

**说明**

POST提交json，在payload里

从session中获取用户id

若成功返回0，失败返回500

**传入数据格式**

```json
{
    title:'',//问卷标题
    id:"0",//问卷id，当id为0时创建新问卷，当id存在时修改该问卷
    desc:'',//问卷描述
    ipRestrict:false,//是否ip限制
    org:1//所属组织，org为0或null或其他非法字段时为公开问卷，belong_orgnization设置为0
}
```

**返回数据格式**

```json
{
    data:''
    msg:"问卷创建/修改成功",
    code:"0"
}

{
    data:''
    msg:"问卷创建/修改失败",
    code:"500"
}
```

### del_question

| 接口地址          | HTTP方法 | 传入参数 | 返回参数类型 | 功能                 |
| ----------------- | -------- | -------- | ------------ | -------------------- |
| /api/del_question | GET      | id       | json         | 删除问卷中的一个问题 |

**说明**

必须验证session，与问卷创建者一致才能删除问题。

若成功返回0，失败返回500

**传入参数**

| 参数名 | 参数类型 | 说明   | 对应字段     |
| ------ | -------- | ------ | ------------ |
| id     | int      | 问题id | question表id |

**返回数据格式**

```json
{
    data:''
    msg:"问题删除成功",
    code:"0"
}

{
    data:''
    msg:"问题删除失败",
    code:"500"
}
```

### add_question

| 接口地址          | HTTP方法 | 传入参数类型 | 返回参数类型 | 功能               |
| ----------------- | -------- | ------------ | ------------ | ------------------ |
| /api/add_question | POST     | json         | json         | 添加或更新某一问题 |

**说明**

POST提交json，在payload里

从session中获取用户id

若成功返回0，失败返回500

**传入数据格式**

```json5
{
          wjId:1,//问卷id
          questionId:1,//问题id
          title:'aaa',//question表title
          type:that.willAddQuestion.type,//question表type
          options:[{
              title:'aaa',//选项标题
              id:1//选项id
            },
            {
              title:'bbb',//option表content
              id:2//option表id
            }],
          isRequired:true,//true或false。
          isPrivate:false//true或false
}
```

**返回数据格式**

```json
{
    data:{
        id:3//返回修改/新增问题的id
    }
    msg:"问题创建/修改成功",
    code:"0"
}

{
    data:''
    msg:"问题创建/修改失败",
    code:"500"
}
```

