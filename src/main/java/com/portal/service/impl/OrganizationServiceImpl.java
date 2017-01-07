package com.portal.service.impl;

import com.portal.bean.Organization;
import com.portal.dao.impl.OrganizationMapper;
import com.portal.service.OrganizationService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Resource
    private OrganizationMapper organizationDao;

    @Override
    public int createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public int updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public Organization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId).get(0);
    }

    @Override
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }
    //
    //    @Override
    //    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
    //        return organizationDao.findAllWithExclude(excludeOraganization);
    //    }
    //
    //    @Override
    //    public void move(Organization source, Organization target) {
    //        organizationDao.move(source, target);
    //    }
}
