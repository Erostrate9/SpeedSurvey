const fs = require('fs');
const path = require('path')
const JSON5 = require('json5')
const Mock = require('mockjs')

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
            var json = getJsonFile('./a.json5');
            res.json(Mock.mock(json))
        });
        app.post('/api/design',function(req,res){
            console.log(req.query);
            var json = getJsonFile('./a.json5');
            res.json(Mock.mock(json))
        })
    }
}