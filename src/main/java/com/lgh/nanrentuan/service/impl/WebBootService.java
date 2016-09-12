package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.SystemConfig;
import com.lgh.nanrentuan.model.CommonVersion;
import com.lgh.nanrentuan.repository.SystemConfigRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


/**
 * 启动设置
 * Created by lgh on 2016/5/5.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class WebBootService implements ApplicationListener<ContextRefreshedEvent> {

    private static Log log = LogFactory.getLog(WebBootService.class);

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Autowired
    private BaseService baseService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            SystemConfig systemConfig = systemConfigRepository.findOne("DatabaseVersion");
            if (systemConfig == null) {
                SystemConfig databaseVession = new SystemConfig();
                databaseVession.setCode("DatabaseVersion");
                databaseVession.setValueForCode(String.valueOf(CommonVersion.initVersion.ordinal()));
                systemConfigRepository.save(databaseVession);
            }

            //系统升级
            baseService.systemUpgrade("DatabaseVersion", CommonVersion.class, CommonVersion.Version101, (upgrade) -> {
                switch (upgrade) {
                    case Version101:
                        //系统分类
                        try {

                        } catch (Exception e) {
                            log.info("upgrade to " + CommonVersion.Version101.ordinal() + " error", e);
                        }

                        break;
                }
            });

        }
    }
}
