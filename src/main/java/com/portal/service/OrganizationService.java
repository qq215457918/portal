package com.portal.service;

import com.portal.bean.Organization;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface OrganizationService {

    public int createOrganization(Organization organization);

    public int updateOrganization(Organization organization);

    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> findAll();

    //    Object findAllWithExclude(Organization excludeOraganization);
    //
    //    void move(Organization source, Organization target);
}
