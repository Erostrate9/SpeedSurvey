const fs = require('fs');
const path = require('path')
const JSON5 = require('json5')
const Mock = require('mockjs')

var json=fs.readFileSync(path.join(__dirname,'./data_analysis.json5'),'utf-8')
var obj=JSON5.parse(json)
console.log(obj)
console.log(Mock.mock(obj))