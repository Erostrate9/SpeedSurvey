package com.yangjiao.service.impl;

import com.yangjiao.domain.Organization;
import com.yangjiao.mapper.OrganizationMapper;
import com.yangjiao.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> queryAllByUserId(int userId) {
        return organizationMapper.selectAllByUserId(userId);
    }
}
