var teamData;
var mp = new Array();
var sp = new Array();
var myId = 2;
var condition = 0;
var memberData = new Array();
var surveyData = new Array();     
function entry()
{
    var avatorHandle = document.getElementById("avator");
    avatorHandle.src = getAvatorUrl();
    teamData = getTeamData().data;
    let teamCount = teamData.length;

    var searchHandle = document.createElement("input");
    searchHandle.style.marginLeft = "30%";
    searchHandle.style.padding = "12px 20px";
    document.getElementById("canvas").appendChild(searchHandle);

    var oHandle = document.createElement("button");
    oHandle.className = "noImg";
    oHandle.innerHTML = "O";
    oHandle.style.marginLeft = "2%";
    oHandle.onclick = function()
    {
        document.getElementById("");
    }
    document.getElementById("canvas").appendChild(oHandle);

    renderFramework(teamCount);
    console.log(document.getElementById("canvas").childNodes);
    renderData();
}
function reload()
{
    document.getElementById("canvas").remove();
    var canvasC = document.createElement("div");
    canvasC.id = "canvas";
    canvasC.style.top = "10%";
    canvasC.style.left = "0%";
    canvasC.style.position = "fixed";
    canvasC.style.width = "100%";
    canvasC.style.background = "#fff";
    canvasC.style.height = "100%";
    canvasC.style.overflow = "auto";
    document.getElementById("bady").appendChild(canvasC);
    entry();
}
function createTeam()
{
    console.log("createRender");
    renderCreate();
}
function downMember(index)
{
    console.log(index,mp[index]);
    if((mp[index]*4 + 4) >= memberData[index].length) return;
    mp[index]++;
    renderMemberData(index+5,index,mp[index]);
    console.log(mp[index]);
}
function upMember(index)
{
    console.log(index,mp[index]);
    if(mp[index] < 1) return;
    mp[index] --;
    renderMemberData(index+5,index,mp[index]);
    console.log(mp[index]);
}
function downSurvey(index)
{
    if(sp[index] * 4 + 4 >= surveyData[index].length) return ;
    sp[index]++;
    renderSurveyData(index+5,index,sp[index]);
    console.log(sp[index]);
}
function upSurvey(index)
{
    if(sp[index] < 1) return;
    sp[index] --;
    renderSurveyData(index+5,index,sp[index]);
    console.log(sp[index]);
}