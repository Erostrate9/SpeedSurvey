var DELETE_POST = "好似喵";
var urlTeam = ["http://47.110.133.7:8889/api/org/owner/list","http://47.110.133.7:8889/api/org/user/list"];
function getTeamData()
{
    var ans;
    $.ajax({
        url: urlTeam[condition],
        type: "get",
        
        async : false,
        data:{
            "userId":myId
        },
        success:function (data) {
            //console.log(data);
            ans = data;
        }})
    if(ans.code == "0") return ans;
    return {
        "msg": "操作成功",
        "code": 0,
        "data": [
            {
                "orgOwner": "杨蛟",
                "orgName": " 哈工大威海",
                "orgDesc": "jqioeqiejqeiwjqioejqiwjwqjiejqioejiqweowqjeqeq",
                "orgId": 1
            },
            {
                "orgOwner": "杨蛟",
                "orgName": "哈工大深圳",
                "orgDesc": null,
                "orgId": 2
            },
            {
                "orgOwner": "杨蛟",
                "orgName": " 哈工大威海",
                "orgDesc": null,
                "orgId": 3
            },
            {
                "orgOwner": "杨蛟",
                "orgName": "哈工大深圳",
                "orgDesc": null,
                "orgId": 4
            },
            {
                "orgOwner": "杨蛟",
                "orgName": " 哈工大威海",
                "orgDesc": null,
                "orgId": 5
            },
            {
                "orgOwner": "杨蛟",
                "orgName": "哈工大深圳",
                "orgDesc": null,
                "orgId": 6
            },
            {
                "orgOwner": "杨蛟",
                "orgName": " 哈工大威海",
                "orgDesc": null,
                "orgId": 7
            },
            {
                "orgOwner": "杨蛟",
                "orgName": "哈工大深圳",
                "orgDesc": null,
                "orgId": 8
            },
            {
                "orgOwner": "杨蛟",
                "orgName": " 哈工大威海",
                "orgDesc": null,
                "orgId": 9
            },
            {
                "orgOwner": "杨蛟",
                "orgName": "哈工大深圳",
                "orgDesc": null,
                "orgId": 10
            }
        ]
    };
}
function getAvatorUrl()
{
    return "https://s1.328888.xyz/2022/06/09/CLcg3.png";
}
function getTeamAvatorData()
{
    return "https://s3.bmp.ovh/imgs/2022/06/09/3f641bce026e6b19.jpg";
}
function getMemberData(id)
{
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/org/users",
        type: "get",
        
        async : false,
        data:{
            "orgId":id
        },
        success:function (data) {
           // console.log(data);
            ans = data;
        }})
    if(ans.code == "0") return ans;
    return {
        "msg": "操作成功",
        "code": 0,
        "data": [
            {
                "authority": "0",
                "remark": null,
                "avatar": null,
                "userId": 123131,
                "email": "zhangsan@example.com",
                "username": "张三"
            },
            {
                "authority": "0",
                "remark": null,
                "avatar": null,
                "userId": 4,
                "email": "lisi@example.com",
                "username": "李四"
            },
            {
                "authority": "0",
                "remark": null,
                "avatar": null,
                "userId": 5,
                "email": "wangwu@example.com",
                "username": "王五"
            },
            {
                "authority": "0",
                "remark": null,
                "avatar": null,
                "userId": 5,
                "email": "wangwu@example.com",
                "username": "王五"
            },
            {
                "authority": "0",
                "remark": null,
                "avatar": null,
                "userId": 5,
                "email": "wangwu@example.com",
                "username": "王五"
            }
        ]
    };
}
function getSurveyData(id)
{
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/org/questionnaires",
        type: "get",
        
        async : false,
        data:{
            "orgId":id
        },
        success:function (data) {
           // console.log(data);
            ans = data;
        }})
    if(ans.code == "0") return ans;
    return {
        "msg": "操作成功",
        "code": 0,
        "data": [
            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            }
        ]
    };
}
function postSurvey(name,des,passwd)
{
    //alert(name,des,passwd);
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/org/add",
        type: "get",
        
        async : true,
        data:{
            "name":name,
            "description":des,
            "inviteCode":passwd,
            "ownerId":myId,
            "logo":"weqwqe"
        },
        success:function (data) {
            console.log(data);
            ans = data;
            //alert("最后通牒！");
        }})
    //console.log(name,des,passwd);
}
function LAS(id)
{
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/orgmail/send",
        type: "get",
        
        async : true,
        data:{
            "orgId":id
        },
        success:function (data) {
           // console.log(data);
            ans = data;
            alert("最后通牒！");
        }})
    console.log(ans);
    console.log("LAS!");
}
function getHandleNull()
{
    return {
        "msg": "操作成功",
        "code": 0,
        "data": [
            {
                "id": 1,
                "userId": 2,
                "title": "个人信息统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            },
            {
                "id": 2,
                "userId": 3,
                "title": "毕业意向统计",
                "description": null,
                "passwordRequired": null,
                "password": null,
                "ipLimit": 0,
                "belongOrganization": 2,
                "url": null,
                "state": null,
                "createTime": null,
                "startTime": null,
                "endTime": null
            }
        ]
    };
}
function postMemberDel(id,_orgId)
{
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/userorg/deleteuser",
        type: "get",
        
        async : false,
        data:{
            "orgId":_orgId,"userId" : id
        },
        success:function (data) {
           // console.log(data);
            ans = data;
            alert(DELETE_POST);
        }})
    console.log(id);
}
function postSurveyDel(id,_orgId)
{
    var ans;
    $.ajax({
        url: "http://47.110.133.7:8889/api/org/deletequestionnaire",
        type: "get",
        
        async : false,
        data:{
            "orgId":_orgId,"questionnaireId" : id
        },
        success:function (data) {
           // console.log(data);
            ans = data;
            alert("好似喵");
        }})
    console.log(id);
}