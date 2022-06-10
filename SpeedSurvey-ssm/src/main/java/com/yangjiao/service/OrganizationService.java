package com.yangjiao.service;


import com.yangjiao.domain.Organization;

import java.util.List;

public interface OrganizationService {
    List<Organization> queryAllByUserId(int userId);
}
