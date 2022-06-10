package com.yangjiao.controller;

import com.yangjiao.domain.Organization;
import com.yangjiao.service.OrganizationService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/queryAllByUserId")
    @ResponseBody
    Map<String,Object> doQueryAllByUserId(int userId){
        List<Organization> organizations = organizationService.queryAllByUserId(userId);
        if (organizations == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(organizations).getResponse();
    }


}
