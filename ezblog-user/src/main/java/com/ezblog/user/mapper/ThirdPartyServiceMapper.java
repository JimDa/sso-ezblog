package com.ezblog.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import po.ThirdPartyServiceAccess;

@Mapper
public interface ThirdPartyServiceMapper {
    ThirdPartyServiceAccess queryByServiceName(@Param("serviceName") String serviceName);
}
