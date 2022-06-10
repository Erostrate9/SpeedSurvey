package com.yangjiao.mapper;

import com.yangjiao.domain.Organization;

import java.util.List;

public interface OrganizationMapper {
    List<Organization> selectAllByUserId(int userId);
}
