<!--
程序名：问卷设计主页面
功能：对问卷进行设计
-->
<template>
  <div class="home">
    <el-row>
      <el-col :span="6">
        <!--操作栏-->
        <div class="opera">
          <!-- <el-tooltip class="item" effect="dark" content="测试" placement="bottom">
            <el-button icon="el-icon-plus" type="text" class="rightButton" @click="testClick"></el-button>
          </el-tooltip> -->
          <el-tooltip class="item" effect="dark" content="创建问卷" placement="bottom">
            <el-button icon="el-icon-plus" type="text" class="rightButton" @click="addWjButtonClick"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑问卷" placement="bottom">
            <el-button icon="el-icon-edit" type="text" class="rightButton" @click="editWj" :disabled="nowSelect.id==0||nowSelect.id==null"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" :content="nowSelect.status==0?'发布问卷':'暂停问卷'" placement="bottom" >
            <el-button :icon="nowSelect.status==0?'el-icon-video-play':'el-icon-video-pause'"  type="text" class="rightButton" @click="pushWj" :disabled="nowSelect.id==0||nowSelect.id==null"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="预览问卷" placement="bottom">
            <el-button icon="el-icon-view" type="text" class="rightButton" @click="previewWj" :disabled="nowSelect.id==0||nowSelect.id==null"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除问卷" placement="bottom">
            <el-button icon="el-icon-delete" type="text" class="rightButton" @click="deleteWj" :disabled="nowSelect.id==0||nowSelect.id==null"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="分享问卷" placement="bottom">
            <el-button icon="el-icon-share" type="text" class="rightButton" @click="shareWj" :disabled="nowSelect.id==0||nowSelect.id==null"></el-button>
          </el-tooltip>
          <!--<el-tooltip class="item" effect="dark" content="添加模板库" placement="bottom">-->
            <!--<el-button icon="el-icon-upload" type="text"class="rightButton" @click="addTemp"></el-button>-->
          <!--</el-tooltip>-->
        </div>

        <!--左侧导航栏-->
        <el-menu :default-active="defaultActive.toString()" v-loading="loading" class="menu">
          <!--问卷列表-->
          <div style="width:100%;text-align: center;font-size: 15px;line-height: 20px;margin-top: 20px;color: #303133" v-if="nowSelect.id==0||nowSelect.id==null">
            点击上方&nbsp;+&nbsp;创建第一个问卷
          </div>
          <el-menu-item v-for="(item,index) in wjList" :key="index" :index="(index+1).toString()" @click="wjClick(item.id,index)">
            <i class="el-icon-tickets"></i>
            <span slot="title" style="display: inline-block">
              {{item.title}}
              <span style="color: #F56C6C;font-size: 13px;" v-if="item.status==0">
                <i class="el-icon-link" style="margin:0;font-size: 13px;color: #F56C6C;width:10px;"></i>
                未发布
              </span>
              <span style="color: #67C23A;font-size: 13px;" v-if="item.status==1">
                <i class="el-icon-link" style="margin:0;font-size: 13px;color: #67C23A;width:10px;"></i>
                已发布
              </span>
            </span>
          </el-menu-item>
        </el-menu>
      </el-col>

      <el-col :span="18">
        <!--标签页-->
         <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane label="问卷设计" name="wjsj">
              <!--内容区域-->
              <div class="content">
                <div v-show="nowSelect.id==0||nowSelect.id==null">请先选择问卷</div>
                <design ref="design" v-show="nowSelect.id!=0&&nowSelect.id!=null"></design>
              </div>
            </el-tab-pane>

            <el-tab-pane label="回答统计" name="hdtj">
              <div class="content" ref="pdf">
                <div v-show="nowSelect.id==0||nowSelect.id==null">请先选择问卷</div>
                <data-show ref="dataShow" v-show="nowSelect.id!=0&&nowSelect.id!=null"></data-show>
              </div>
            </el-tab-pane>
          </el-tabs>
      </el-col>
    </el-row>

    <!--添加问卷弹窗-->
    <el-dialog title="添加问卷" :visible.sync="dialogShow" :close-on-click-modal="false" class="dialog">
      <el-form ref="form" :model="willAddWj" label-width="80px">
        <el-form-item label="问卷标题" style="width: 100%;" required>
          <el-input v-model="willAddWj.title" placeholder="请输入问卷标题" ></el-input>
        </el-form-item>
        <el-form-item label="问卷描述" style="width: 100%;">
          <el-input v-model="willAddWj.desc" type="textarea" placeholder="请输入问卷描述" rows="5"></el-input>
        </el-form-item>
        <el-form-item label="发布组织" c>
          <el-select v-model="willAddWj.org" placeholder="请选择发布组织" >
            <el-option label="不发布到组织" :value="0" ></el-option>
            <el-option v-for="(item,index) in orgList" :key="index" :label="item.orgName" :value="item.orgId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IP限制" style="width: 100%;">
          <el-switch v-model="willAddWj.ipRestrict"></el-switch>
        </el-form-item>
      </el-form>
      <div style="width: 100%;text-align: right">
        <el-button style="margin-left: 10px;" @click="openTemp">从模板库创建</el-button>
        <el-button style="margin-left: 10px;" @click="dialogShow=false">取消</el-button>
        <el-button type="primary" style="margin-left: 10px;" @click="addWj">确定</el-button>
      </div>
    </el-dialog>



    <!--模板库弹窗-->
    <el-dialog title="模板库" :visible.sync="tempDialog" :close-on-click-modal="false" class="dialog">
      <el-input placeholder="请输入关键词搜索" prefix-icon="el-icon-search" v-model="tempSearchText"></el-input>
     <el-table :data="tempData" style="width: 100%;" v-loading="tempLoading" element-loading-text="加载中...">
      <el-table-column prop="tempname" label="模板名" width="250"></el-table-column>
      <el-table-column prop="username" label="创建者"></el-table-column>
       <el-table-column label="预览">
       <el-link slot-scope="scope" type="primary" @click="lookTempWj(scope.row)">预览</el-link>
     </el-table-column>
     <el-table-column label="操作">
        <el-link slot-scope="scope" type="primary" @click="useTemp(scope.row)">使用此模板</el-link>
     </el-table-column>
    </el-table>
      <el-pagination layout="prev, pager, next" :total="tempDataCount" @current-change="changeTempPage" :page-size	="5"></el-pagination>
    </el-dialog>


    <!--分享问卷弹窗-->
    <el-dialog title="分享问卷" :visible.sync="shareDialogShow" :close-on-click-modal="true" class="dialog" @opened="makeQrcode">
      <el-form ref="form" :model="shareInfo" label-width="80px">
        <el-form-item label="问卷链接" style="width: 100%;">
          <el-row>
            <el-col :span="16">
              <el-input v-model="shareInfo.url" readonly></el-input>
            </el-col>
            <el-col :span="8">
              <el-button style="margin-left: 5px;" v-clipboard:copy="shareInfo.url" v-clipboard:success="copySuccess" v-clipboard:error="copyError">复制</el-button>
              <el-button style="margin-left: 5px;" @click="openShareUrl">打开</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="二维码" style="width: 100%;">
          <canvas id="canvas" style="width: 150px;height: 150px;"></canvas>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
  import {designOpera} from './api'
  import Design from './Design.vue'
  import DataShow from './DataShow.vue'
  import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
  import axios from 'axios';
  import QRCode from 'qrcode';

  export default{
    components:{
      ElButton,
      Design,
      // QRCode,
      DataShow,
    },
    data(){
      return{
        userInfo:{},
        defaultActive:1,//当前激活菜单
        activeName:'wjsj',//标签页当前选择项
        wjList:[],//问卷列表
        loading:false,//是否显示加载中
        dialogShow:false,//添加问卷弹窗是否显示
        shareDialogShow:false,//分享问卷弹窗是否显示
        tempDialog:false,//模板库弹窗是否显示
//        nowTempData:[],//当前模板库页码
        tempData:[],
        tempDataCount:0,
        tempLoading:false,
        tempSearchText:'',
        willAddWj:{
          id:0,
          title:'',
          flag:0,
          desc:'感谢您能抽时间参与本次问卷，您的意见和建议就是我们前行的动力！',
          status:0,
          ipRestrict:false,
          org:0
        },
        shareInfo:{
          url:'',
        },
        orgList:[
          {
            id:'0',
            title:''
          }
        ]

      }
    },
    mounted(){
      this.logincheck();
    },
    computed:{
      //现在选中的问卷信息
      nowSelect(){
        console.log('nowSelect update');
        let now=this.wjList[this.defaultActive-1];
        if(this.wjList.length==0){
          return {
            id:null,
            title:null,
            desc:null,
            status:null
          }
        }
        return{
          id:now.id,
          title:now.title,
          desc:now.desc,
          status:now.status
        }
      },
    },
    methods:{
      //添加模板
      addTemp(){
        designOpera({
          opera_type:'add_temp',
          wjId:107,
        })
          .then(data=>{
            console.log(data);
          })
      },
      //使用问卷模板
      useTemp(item){
        this.tempLoading=true;
        designOpera({
          opera_type:'use_temp',
          wjId:item.tempid,
        })
          .then(data=>{
            console.log(data);
            this.tempLoading=false;
            this.$messageE({
              type: 'success',
              message: '使用模板成功！',
              showClose: true
            });
            this.tempDialog=false;
            this.dialogShow=false;
            this.getWjList();

          })
      },
      //打开问卷库
      openTemp(){
        this.tempDialog=true;
        this.changeTempPage(1);
       this.getTempWjList();
      },
      //改变模板库页码
      changeTempPage(page){
        this.tempLoading=true;
        designOpera({
          opera_type:'get_temp_wj_list',
          page:page
        })
          .then(data=>{
            console.log(data);
            this.tempDataCount=data.count;
            this.tempData=data.detail;
            this.tempLoading=false;
          })
      },
      //预览模板问卷
      lookTempWj(item){
        let url=window.location.origin+"/tempdisplay/"+item.tempid;//问卷链接
        console.log(url);
        window.open(url);
      },
      //检查登录是否过期
      logincheck(){
        const that= this;
        //   designOpera({
        //   opera_type:'logincheck',
        // })
        axios({
          method: 'get',
          url: '/api/user/logincheck',
        })
        .then(data=>{
          // console.log("logincheck data.data",data.data);
          // console.log("logincheck data",data);
          if(data.data.code==0){
            that.userInfo=JSON.parse(localStorage.useInfo);
            console.log(that.userInfo);
            that.getWjList();
            that.getOrgList();
          }else{//如果返回的错误是，跳转到登录页面
            that.$messageE({
              type: 'error',
              message: '您还未登录，请登录',
              showClose: true
            });
            that.$router.push({path:'/user/login'})
          }
        })
      },
      //拉取该用户创建的所有组织
      getOrgList(){
        const that =this;
        axios({
          method: 'get',
          url: 'http://47.110.133.7:8889/api/org/owner/list',
          params:{
            userId:that.userInfo.id
          }
        })
        .then(data=>{
          console.log("getOrgList data.data.data",data.data.data)
          that.orgList=data.data.data;
        })

      },
      //发布问卷/暂停问卷
      pushWj(){
        let status=1;
        if(this.nowSelect.status==1)
            status=0;
        const that = this;
        // designOpera({
        //   opera_type:'push_wj',
        //   username:'test',
        //   wjId:this.nowSelect.id,
        //   status:status
        // })
        axios({
          method: 'post',
          url: '/api/questionnaire/push',
          params:{
            wjId:that.nowSelect.id,
            status:status
          }
        })
          .then(data=>{
            // console.log("push_wj data",data);
            // console.log("push_wj data.data",data.data);
            if(data.data.code==0){
              that.$messageE({
                type: 'success',
                message: status==1?'问卷发布成功！':'问卷暂停成功！'
              });
              that.getWjList();
            }
            else{
              that.$messageE({
                type: 'error',
                message: data.msg
              });
            }
          })
      },
      //分享问卷
      shareWj(){
        if(this.nowSelect.status==0){//问卷未发布
          this.$messageE({
            type: 'error',
            message: '请先发布问卷能分享！'
          });
          return;
        }
        this.shareInfo.url=window.location.origin+"/questionnaire/detail?id="+this.nowSelect.id;//问卷链接
        this.shareDialogShow=true;
      },
      //生成二维码
      makeQrcode(){
        var canvas=document.getElementById('canvas');
        // console.log(canvas);
        // console.log(this.shareInfo.url);
        QRCode.toCanvas(canvas,this.shareInfo.url,function(error){
          if(error){
            console.log(error);
          }else{
            console.log("qrcode successed!")
          }
        });
      },
      //复制分享链接成功
      copySuccess(e){
        console.log(e);
        this.$messageE({
          type: 'success',
          message: '已复制链接到剪切板'
        });
      },
      //复制分享链接失败
      copyError(e){
        console.log(e);
        this.$messageE({
          type: 'error',
          message: '复制失败'
        });
      },
      //打开分享链接
      openShareUrl(){
        window.open(this.shareInfo.url);
      },
      //预览问卷
      previewWj(){
        let url=window.location.origin+"/questionnaire/detail?id="+this.nowSelect.id;//问卷链接
        console.log(url);
        window.open(url);
      },
      //编辑问卷
      editWj(){
        this.dialogShow=true;
        this.willAddWj=this.nowSelect;
        this.getOrgList();
      },
      testClick(){
        // console.log(sessionStorage);

        // axios.get('http://47.110.133.7:8889/api/org/owner/list?ownerId=2',{
        //   headers: {
        //     "Content-Type": "application/octet-stream",
        //     "Access-Control-Allow-Origin": "*",
        //   }
        // })
        // .then(data=>{
        //   console.log("test data:",data);
        // })
        // axios.post("/api/wjlist", {
        //   id:"123",
        //   hid:'aaa',
        //   list:[123,457]
        //   })
        //   .then(res => console.log(res));
      },
      //添加问卷按钮点击
      addWjButtonClick(){
        this.dialogShow=true;
        this.willAddWj={
          id:0,
          title:'',
          flag:0,
          desc:'感谢您能抽时间参与本次问卷，您的意见和建议就是我们前行的动力！',
          ipRestrict:false,
          org:0,
          status:0
        };
        this.getOrgList();
      },
      //添加问卷
      addWj(){
        if (this.willAddWj.title == ''){
          this.$messageE({
            type: 'error',
            message: '标题不能为空'
          });
          return;
        }
        const that = this;
        // designOpera({
        //   opera_type:'add_wj',
        //   username:'test',
        //   title:this.willAddWj.title,
        //   id:this.willAddWj.id,
        //   desc:this.willAddWj.desc,
        //   ipRestrict:this.willAddWj.ipRestrict,
        //   org:this.willAddWj.org
        // })
        axios.post('/api/questionnaire/add',{
          title:that.willAddWj.title,
          id:that.willAddWj.id,
          desc:that.willAddWj.desc,
          ipRestrict:that.willAddWj.ipRestrict,
          org:that.willAddWj.org
        })
          .then(data=>{
            // console.log("add wj data",data);
            console.log("addwj data.data",data.data);
            if(data.data.code==0){
              that.$messageE({
                type: 'success',
                message: '保存成功!'
              });
              that.getWjList();
            }
            else{
              that.$messageE({
                type: 'error',
                message: data.data.msg
              });
            }
          });
        this.dialogShow=false;
        this.willAddWj.title='';
      },
      //获取问卷列表
      getWjList(){
        this.loading=true;
        const that = this;
        axios.get('/api/questionnaire/creater/list')
          .then(data=>{
            // console.log("wjlist data",data);
            that.wjList=data.data.data;
            console.log("wjlist",that.wjList)
            that.loading=false;
            //获取当前选中问卷题目
            that.lookDetail();
            })
      },
      //删除问卷
      deleteWj(){
        this.$confirmE('确定删除'+this.nowSelect.title+'? 删除后不可恢复！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading=true;
          
        //   designOpera({
        //   opera_type:'delete_wj',
        //   username:'test',
        //   id:this.nowSelect.id
        // })
        //   .then(data=>{
        //     console.log(data);
        //     if(data.code==0){
        //       this.$message({
        //         type: 'success',
        //         message: '删除成功!'
        //       });
        const that = this;
        axios({
          method: 'get',
          url: '/api/questionnaire/delete',
          params:{
            wjId:that.nowSelect.id
          }
        })
        .then(data=>{
          // console.log("DELETE DATA:",data);
            // console.log("DELETE data.data:",data.data);
            if(data.data.code==0){
              that.$messageE({
                type: 'success',
                message: '删除成功!'
              });

              that.loading=false;
              that.getWjList();
              that.defaultActive=1;
            }
            else{
              that.$messageE({
                type: 'error',
                message: data.data.msg
              });
            }
          })
        });
      },
      //问卷点击
      wjClick(id,index){
        this.defaultActive=(index+1).toString();
        this.lookDetail();
      },
      //查看问卷详情
      lookDetail(){
        console.log("refs",this.$refs)
        this.$refs.design.init(this.nowSelect.id,this.nowSelect.title,this.nowSelect.desc);
        console.log("lookDetail id=",this.nowSelect.id)
        console.log("lookDeatil refs.dataShow",this.$refs.dataShow);
        this.$refs.dataShow.dataAnalysis(this.nowSelect.id);
      },
    }
  }
</script>

<style scoped>
  .home{
    position: absolute;
    width: 100%;
    height: calc(100vh - 100px);
    text-align: left;

  }
  .home .badgeItem{
    margin-top: 40px;
  }
  .content{
    padding: 20px;
    padding-right: 50px;
    height: calc(100vh - 175px);
    text-align: center;
    overflow-y: scroll;
    overflow-x: hidden;
  }
  .menu{
    background-color: white;
    height: calc(100vh - 100px);
    overflow-x: scroll;
    overflow-y: scroll;
    left: 0;
  }
  .home .opera{
    position: relative;
    background-color: #fafafa;
    text-align: center;
    height: 40px;
  }
  .home .rightButton{
    font-size: 16px;
  }
  .home .addWjDiv{
    height: 50px;line-height: 50px;text-align: center;
    border-bottom: 1px solid #eee
  }
</style>
