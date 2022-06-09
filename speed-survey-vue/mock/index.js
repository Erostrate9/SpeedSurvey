const fs = require('fs');
const path = require('path')
const JSON5 = require('json5')
const Mock = require('mockjs')
const bodyParser = require('body-parser')
var jsonParser = bodyParser.json()

//读取json文件
function getJsonFile(filePath){
    //读取指定json文件
    var json=fs.readFileSync(path.join(__dirname,filePath),'utf-8')
    return JSON5.parse(json)
}

module.exports = function(app){
    //监听http请求
    if(process.env.MOCK=='true'){
        //Node.js中的express框架
        app.get('/api/userinfo',function(req,res){
            //每次响应请求时读取mock data的json文件
            //getJsonFile方法定义了如何读取json文件并解析成数据对象

            var json = getJsonFile('./userInfo.json5');
            res.json(Mock.mock(json))
        });
        app.get('/api/a',function(req,res){
            var json = getJsonFile('./userInfo.json5');
            res.json(Mock.mock(json))
        });
        app.post('/api/test',function(req,res){
            // console.log(req.querys);
            var json = getJsonFile('./userInfo.json5');
            res.json(Mock.mock(json));
        })
        app.post('/api/design',jsonParser,function(req,res){
            // console.log(req.body)
            var json = getJsonFile('./userInfo.json5');
            res.json(Mock.mock(json));
        });

        app.get('/api/questionnaire/query',jsonParser,(req,res)=>{
            // var body=req.body;
            // console.log("wj_list query:",req.query);
            var json = getJsonFile('./wjlist.json5');
            res.json(Mock.mock(json));
        });
        app.get('/api/questionnaire/queryDetail',jsonParser,(req,res)=>{
            // var body=req.body;
            // console.log("question_list",req.query);
            var json = getJsonFile('./questionlist.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.get('/api/text_result/list',jsonParser,(req,res)=>{
            // console.log("text_result query:",req.query);
            var json = getJsonFile('./text_answer_detail.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.get('/api/data_analysis',jsonParser,(req,res)=>{
            var body=req.body;
            // console.log("data_analysis query:",req.query);
            var json = getJsonFile('./data_analysis.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        //获取用户创建的组织列表
        app.get('/api/org/owner/list',(req,res)=>{
            // console.log("getOrgList body",req.body);
            // console.log("getOrgList query",req.query);
            var json = getJsonFile('./org_list.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.get('/api/user/logincheck',(req,res)=>{
            var json = getJsonFile('./logincheck.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.get('/api/questionnaire/delete',(req,res)=>{
            // console.log("questionnaire_delete query:",req.query);
            var json = getJsonFile('./delete_wj_success.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.post('/api/questionnaire/push',(req,res)=>{
            // console.log("push_wj query:",req.query);
            var json = getJsonFile('./push_wj_success.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.post('/api/addwj',jsonParser,(req,res)=>{
            // console.log("addwj body:",req.body);
            var json = getJsonFile('./add_wj_success.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.get('/api/question/delete',(req,res)=>{
            // console.log("del_question query:",req.query);
            var json = getJsonFile('./delete_question_success.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
        app.post('/api/question/add',jsonParser,(req,res)=>{
            // console.log("add_question body:",req.body);
            var json = getJsonFile('./add_question_success.json5');
            // console.log(json);
            res.json(Mock.mock(json));
        });
    
    }
}