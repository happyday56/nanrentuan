package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.service.CommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/10.
 */
@Service
public class CommonConfigServiceImpl implements CommonConfigService {

    @Autowired
    Environment env;

    @Override
    public String getResourcesUri() {
        return env.getProperty("lgh.resourcesUri", "http://localhost:8080");
    }

    @Override
    public String getResourcesHome() {
        return env.getProperty("lgh.resourcesHome", "D:/nanrentuan/target/ROOT");// "D:/java_project/huanlebian/target/ROOT/_resources"
    }

}
