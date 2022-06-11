function renderFramework(teamCount)
{
    for(let i = 0; i < teamCount;i++)
    {
        var canvasHandle = document.getElementById("canvas");
        var teamHandle = document.createElement("div");
        //tmp.style.marginLeft = "1%";
        //tmp.style.marginRight = "1%";
        teamHandle.style.marginTop = "1%";
        //teamHandle.style.background = "#ffff00";
        teamHandle.style.height = "40%";

        var cardHandle = document.createElement("div");
        cardHandle.style.marginLeft = "6%";
        cardHandle.style.height = "100%";
        cardHandle.style.marginRight = "76%";
        cardHandle.style.background = "#000000";
        cardHandle.style.textAlign = "center";
        // cardHandle.style.zIndex = "100";

        var rollListHandle = document.createElement("div");
        rollListHandle.style.background = "#000000";
        rollListHandle.style.marginTop = "1%";
        rollListHandle.style.height = "90%";
        rollListHandle.style.width = "70%";
        rollListHandle.style.marginRight = "6%";
        rollListHandle.style.float = "right";
        
        var teamnameHandle = document.createElement("div");
        teamnameHandle.innerHTML = "NAME_NULL";
        teamnameHandle.style.color = "#fff";
        teamnameHandle.style.fontWeight = "bold";
        teamnameHandle.style.marginLeft = "auto";
        teamnameHandle.style.marginRight = "auto";
        teamnameHandle.style.fontSize = "25px";
        teamnameHandle.style.wordBreak="break-all";

        var teamAvatorHandle = document.createElement("img");
        teamAvatorHandle.src = getTeamAvatorData();
        teamAvatorHandle.style.width = "60%";
        teamAvatorHandle.style.top = "0";
        teamAvatorHandle.style.left = "0";
        
        var teamCaptainNameHandle = document.createElement("div");
        teamCaptainNameHandle.innerHTML = "CNAME_NULL";
        teamCaptainNameHandle.style.marginTop = "1%";
        teamCaptainNameHandle.style.color = "#fff";
        teamCaptainNameHandle.style.fontWeight = "bold";
        teamCaptainNameHandle.style.marginLeft = "auto";
        teamCaptainNameHandle.style.marginRight = "auto";
        teamCaptainNameHandle.style.fontSize = "15px";
        teamCaptainNameHandle.style.wordBreak="break-all";

        var descriptionHandle = document.createElement("div");
        descriptionHandle.innerHTML = "DESCRIPTION_NULL\n"
        +"123213321jidojjeioj12213021j3ejdnsaojdlkwqj  mdqiwj iqrq";
        descriptionHandle.style.marginTop = "10%";
        descriptionHandle.style.color = "#fff";
        descriptionHandle.style.fontWeight = "bold";
        descriptionHandle.style.marginLeft = "10%";
        descriptionHandle.style.marginRight = "10%";
        descriptionHandle.style.fontSize = "15px";
        descriptionHandle.style.wordBreak="break-all";
        
        var trick = document.createElement("div");
        trick.style.height = "10%";

        cardHandle.appendChild(trick);
        cardHandle.appendChild(teamnameHandle);
        cardHandle.appendChild(teamAvatorHandle);
        cardHandle.appendChild(teamCaptainNameHandle);
        cardHandle.appendChild(descriptionHandle);

        var memberRollHandle = document.createElement("div");
        memberRollHandle.style.background = "#000000";
        memberRollHandle.style.height = "90%";
        memberRollHandle.style.width = "30%";
        memberRollHandle.style.marginLeft = "2%";
        memberRollHandle.style.marginTop = "1%";

        var triangle = document.createElement("img");
        triangle.src = "https://s1.328888.xyz/2022/06/09/Ci9iy.png";
        triangle.style.width = "100%";
        triangle.style.height = "100%";
        triangle.onclick = function(){upMember(i)};

        var triangle2 = document.createElement("img");
        triangle2.src = "https://s1.328888.xyz/2022/06/09/CiLVd.png";
        triangle2.style.width = "100%";
        triangle2.style.height = "100%";
        triangle2.onclick = function(){downMember(i)};

        var upHandle = document.createElement("div");
        upHandle.style.height = "10%";
        //upHandle.style.background = "#00ffff";
        upHandle.appendChild(triangle);
        memberRollHandle.appendChild(upHandle);
        for(var j = 0;j < 4;j++)
        {
            var tmp = document.createElement("div");
            //tmp.style.background = "#ff00ff";
            tmp.style.height = "20%";
            tmp.style.color = "#fff";
            tmp.style.textAlign = "center";
            tmp.style.fontSize = "40px";
            tmp.style.marginTop = "1%";


            //tmp.appendChild(delPayload);
            memberRollHandle.appendChild(tmp);
        }
        var downHandle = document.createElement("div");
        //downHandle.style.marginTop = "70%";
        downHandle.style.height = "10%";
        //downHandle.style.background = "#00ffff";
        downHandle.appendChild(triangle2);
        memberRollHandle.appendChild(downHandle);

        var surveyRollHandle = document.createElement("div");
        surveyRollHandle.style.background = "#000000";
        surveyRollHandle.style.height = "90%";  
        surveyRollHandle.style.width = "60%";
        surveyRollHandle.style.marginRight = "5%";   
        surveyRollHandle.style.marginTop = "1%";
        surveyRollHandle.style.float = "right";

        var triangle = document.createElement("img");
        triangle.src = "https://s1.328888.xyz/2022/06/09/Ci9iy.png";
        triangle.style.width = "100%";
        triangle.style.height = "100%";
        triangle.onclick = function(){upSurvey(i)};

        var triangle2 = document.createElement("img");
        triangle2.src = "https://s1.328888.xyz/2022/06/09/CiLVd.png";
        triangle2.style.width = "100%";
        triangle2.style.height = "100%";
        triangle2.onclick = function(){downSurvey(i)};

        var upHandle = document.createElement("div");
        upHandle.style.height = "10%";
        //upHandle.style.background = "#00ffff";
        upHandle.appendChild(triangle);
        surveyRollHandle.appendChild(upHandle);
        for(var j = 0;j < 4;j++)
        {
            var tmp = document.createElement("div");
            //tmp.style.background = "#ff00ff";
            tmp.style.height = "20%";
            tmp.style.color = "#fff";
            tmp.style.textAlign = "center";
            tmp.style.fontSize = "40px";
            tmp.style.marginTop = "1%";


            surveyRollHandle.appendChild(tmp);
        }
        var downHandle = document.createElement("div");
        //downHandle.style.marginTop = "70%";
        downHandle.style.height = "10%";
        //downHandle.style.background = "#00ffff";
        downHandle.appendChild(triangle2);
        surveyRollHandle.appendChild(downHandle);

        rollListHandle.appendChild(surveyRollHandle);
        rollListHandle.appendChild(memberRollHandle);


        teamHandle.appendChild(rollListHandle);
        teamHandle.appendChild(cardHandle);

        canvasHandle.appendChild(teamHandle);
        
    }
    var canvasHandle = document.getElementById("canvas");
    var teamHandle = document.createElement("div");
    teamHandle.style.marginTop = "1%";
    //teamHandle.style.background = "#ffff00";
    teamHandle.style.height = "14%";
    canvasHandle.appendChild(teamHandle);
}
function renderData()
{
    //console.log(teamData);
    var canvasHandle = document.getElementById("canvas");
    var childs = canvasHandle.childNodes;
    //console.log(childs);
    var tot = 0;
    for(var i in childs)
    {
        if(childs[i].nodeName != "DIV") continue;
        if(!childs[i].hasChildNodes()) continue;
        mp[tot] = 0;
        sp[tot] = 0;
        //console.log(childs[i]);
        var chc = childs[i].childNodes[1].childNodes;
        chc[1].innerHTML = teamData[tot].orgName;
        chc[3].innerHTML = teamData[tot].orgOwner;
        chc[4].innerHTML = teamData[tot].orgDesc;
        memberData[tot] = getMemberData(teamData[tot].orgId).data;
        surveyData[tot] = getSurveyData(teamData[tot].orgId).data;
        // console.log(memberData[tot]);
        renderMemberData(i,tot,mp[tot]);
        renderSurveyData(i,tot,sp[tot]);
        tot ++;
    }
    // console.log(surveyData);
}
function renderMemberData(teamIndex,dataIndex,memberPage)
{
    var canvasHandle = document.getElementById("canvas");
    var childs = canvasHandle.childNodes;
    var chc = childs[teamIndex].childNodes[0].childNodes[1];
    //console.log(chc);
    for(var i = 0;i < 4;i++)
    {
        var index = memberPage*4 + i;
        //console.log(index);
        if(index >= memberData[dataIndex].length) 
        {
            chc.childNodes[i +1].innerHTML = null;
            continue;
        }
        chc.childNodes[i + 1].innerHTML = 
        memberData[dataIndex][index].username;
        var delPayload = document.createElement("div");
        delPayload.style.float = "right";
        delPayload.style.height = "100%";
        //delPayload.width = "10%";

        var blood = document.createElement("img");
        blood.src = "https://s1.328888.xyz/2022/06/09/CrHqv.png";
        blood.style.height = "100%";
        blood.style.width = "100%";
        delPayload.appendChild(blood);
        //console.log(index);
        if(index%4 == 0)
        {
            delPayload.onclick = function()
            {
                postMemberDel(memberData[dataIndex][memberPage*4+0].userId
                    ,teamData[dataIndex].orgId);
                console.log(memberData[dataIndex][memberPage*4+0]);
                memberData[dataIndex] = 
                getMemberData(teamData[dataIndex].orgId).data;
                renderMemberData(teamIndex,dataIndex,mp[dataIndex]);
            }
        }
        if(index%4 == 1)
        {
            delPayload.onclick = function()
            {
                postMemberDel(memberData[dataIndex][memberPage*4+1].userId
                    ,teamData[dataIndex].orgId);
                memberData[dataIndex] = 
                getMemberData(teamData[dataIndex].orgId).data;
                renderMemberData(teamIndex,dataIndex,mp[dataIndex]);
            }
        }
        if(index%4 == 2)
        {
            delPayload.onclick = function()
            {
                postMemberDel(memberData[dataIndex][memberPage*4+2].userId
                    ,teamData[dataIndex].orgId);
                memberData[dataIndex] = 
                getMemberData(teamData[dataIndex].orgId).data;
                renderMemberData(teamIndex,dataIndex,mp[dataIndex]);
            }
        }
        if(index%4 == 3)
        {
            delPayload.onclick = function()
            {
                //console.log(index);
                postMemberDel(memberData[dataIndex][memberPage*4+3].userId
                    ,teamData[dataIndex].orgId);
                // console.log(memberData[dataIndex][index]);
                memberData[dataIndex] = 
                getMemberData(teamData[dataIndex].orgId).data;
                renderMemberData(teamIndex,dataIndex,mp[dataIndex]);
            }
        }
        chc.childNodes[i+1].appendChild(delPayload);
        //console.log(memberData[dataIndex][index].username);
    }
}
function renderSurveyData(teamIndex,dataIndex,surveyPage)
{
    var canvasHandle = document.getElementById("canvas");
    var childs = canvasHandle.childNodes;
    var chc = childs[teamIndex].childNodes[0].childNodes[0];
    //console.log(chc);
    for(var i = 0;i < 4;i++)
    {
        var index = surveyPage*4 + i;
        //console.log(index);
        if(index >= surveyData[dataIndex].length) 
        {
            chc.childNodes[i +1].innerHTML = null;
            continue;
        }
        chc.childNodes[i + 1].innerHTML = 
        surveyData[dataIndex][index].title;
        var delPayload = document.createElement("div");
        delPayload.style.float = "right";
        delPayload.style.height = "100%";
        var lastAlarm = document.createElement("div");
        lastAlarm.style.float = "right";
        lastAlarm.style.height = "100%";
        lastAlarm.style.width = "10%";
        var alarm = document.createElement("img");
        alarm.src = "https://s1.328888.xyz/2022/06/09/CrPE0.png";
        alarm.style.height = "100%";
        lastAlarm.appendChild(alarm);

        lastAlarm.onclick = function(){LAS(teamData[dataIndex].orgId)};
        chc.childNodes[i+1].appendChild(lastAlarm);

        var blood = document.createElement("img");
        blood.src = "https://s1.328888.xyz/2022/06/09/CrHqv.png";
        blood.style.height = "100%";
        blood.style.width = "100%";
        delPayload.appendChild(blood);
        if(index%4 ==0)
        {
            delPayload.onclick = function()
            {
                postSurveyDel(surveyData[dataIndex][surveyPage*4+0].id
                    ,teamData[dataIndex].orgId);
                    // console.log(teamData[dataIndex]);
                surveyData[dataIndex] = 
                //getHandleNull().data;
                //console.log(memberData[dataIndex]);
                getSurveyData(teamData[dataIndex].orgId).data;
                renderSurveyData(teamIndex,dataIndex,sp[dataIndex]);
            }
        }
        if(index%4 ==3)
        {
            delPayload.onclick = function()
            {
                postSurveyDel(surveyData[dataIndex][surveyPage*4+3].id,teamData[dataIndex].orgId);
                //console.log(surveyData[dataIndex][index],index);
                surveyData[dataIndex] = 
                //getHandleNull().data;
                //console.log(memberData[dataIndex]);
                getSurveyData(teamData[dataIndex].orgId).data;
                renderSurveyData(teamIndex,dataIndex,sp[dataIndex]);
            }
        }
        if(index%4 ==1)
        {
            delPayload.onclick = function()
            {
                postSurveyDel(surveyData[dataIndex][surveyPage*4+1].id,teamData[dataIndex].orgId);
                //console.log(surveyData[dataIndex][index],index);
                surveyData[dataIndex] = 
                //getHandleNull().data;
                //console.log(memberData[dataIndex]);
                getSurveyData(teamData[dataIndex].orgId).data;
                renderSurveyData(teamIndex,dataIndex,sp[dataIndex]);
            }
        }
        if(index%4 ==2)
        {
            delPayload.onclick = function()
            {
                postSurveyDel(surveyData[dataIndex][surveyPage*4+2].id
                    ,teamData[dataIndex].orgId);
                    // console.log(teamData[dataIndex]);
                //console.log(surveyData[dataIndex][index],index);
                surveyData[dataIndex] = 
                //getHandleNull().data;
                //console.log(memberData[dataIndex]);
                getSurveyData(teamData[dataIndex].orgId).data;
                renderSurveyData(teamIndex,dataIndex,sp[dataIndex]);
            }
        }

        chc.childNodes[i+1].appendChild(delPayload);
    }
}
function renderCreate()
{
    var windowHandle = document.createElement("div");
    windowHandle.style.position = "fixed";
    windowHandle.style.background = "#a0a0a0";
    windowHandle.style.zIndex = "1000";
    windowHandle.style.top = "20%";
    windowHandle.style.left = "40%";
    windowHandle.style.height = "60%";
    windowHandle.style.width = "40%";
    windowHandle.id = "window";

    var innerHandle = document.createElement("div");
    innerHandle.style.height = "90%";
    innerHandle.style.width = "98%";
    innerHandle.style.marginTop = "0%";
    innerHandle.style.marginLeft = "1%";
    innerHandle.style.background = "#fff";

    var closeButton = document.createElement("button");
    closeButton.className = "noImg";
    closeButton.innerHTML = "X";
    // closeButton.onclick = function()
    // {
    //     //alert(document.getElementById("nameHandle").value);
    //     postSurvey(document.getElementById("nameHandle").value
    //     ,document.getElementById("descriptionHandle".value),
    //     document.getElementById("passwordHandle").value);
    // }
    closeButton.style.float = "right";
    closeButton.onclick = function(){destroyWindow()};
    innerHandle.appendChild(closeButton);

    var formHandle = document.createElement("form");
    var title = document.createElement("h3");
    title.innerHTML = "Create Survey";
    title.style.fontSize = "40px";
    innerHandle.appendChild(title);
    var labelHandle = document.createElement("label");
    labelHandle.innerHTML = "Name";
    labelHandle.style.fontSize = "32px";
    formHandle.appendChild(labelHandle);formHandle.innerHTML+="</br>";
    var textHandle = document.createElement("input");
    textHandle.id = "nameHandle";
    textHandle.style.width = "100%";
    textHandle.style.padding = "12px 12px";
    textHandle.style.margin = "8px 0";
    formHandle.appendChild(textHandle);
    formHandle.innerHTML+="</br>";

    var labelHandle = document.createElement("label");
    labelHandle.innerHTML = "Description";
    
    labelHandle.style.fontSize = "32px";
    formHandle.appendChild(labelHandle);formHandle.innerHTML+="</br>";
    var textHandle = document.createElement("input");
    textHandle.style.width = "100%";
    textHandle.style.padding = "12px 12px";
    textHandle.style.margin = "8px 0";
    textHandle.id = "descriptionHandle";
    formHandle.appendChild(textHandle);
    formHandle.innerHTML+="</br>";

    var labelHandle = document.createElement("label");
    labelHandle.innerHTML = "Password";
    
    labelHandle.style.fontSize = "32px";
    formHandle.appendChild(labelHandle);formHandle.innerHTML+="</br>";
    var textHandle = document.createElement("input");
    textHandle.id = "passwordHandle";
    textHandle.style.width = "100%";
    textHandle.style.padding = "12px 12px";
    textHandle.style.margin = "8px 0";
    formHandle.appendChild(textHandle);
    formHandle.innerHTML+="</br>";
    var subHandle = document.createElement("button");
    subHandle.className = "noImg";
    subHandle.innerText = "submit";
    subHandle.onclick = function()
    {
        //alert(document.getElementById("nameHandle").value);
        postSurvey(document.getElementById("nameHandle").value
        ,document.getElementById("descriptionHandle".value),
        document.getElementById("passwordHandle").value);
    }
    formHandle.appendChild(subHandle);

    innerHandle.appendChild(formHandle);

    windowHandle.appendChild(innerHandle);
    document.getElementById("bady").appendChild(windowHandle);
}
function destroyWindow()
{
    var bodyHandle = document.getElementById("bady");
    var c = bodyHandle.childNodes;
    for(var i in c)
    {
        if(c[i].id == "window") c[i].remove();
    }
    console.log(bodyHandle.childNodes);
}