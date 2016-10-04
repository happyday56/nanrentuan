package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.service.URIService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2016/6/10.
 */
@Service
public class URIServiceImpl implements URIService {
    public String getCategoryURI(String one) {
        return "/" + one;
    }

    public String getCategoryURI(String one, String two) {
        if (!StringUtils.isEmpty(one)) {
            if (StringUtils.isEmpty(two)) {
                return "/" + one;
            } else {
                return "/" + one + "/" + two;
            }
        }
        return "/";
    }

    @Override
    public String getArticleURI(Long id) {
        return "/" + id + ".html";
    }


}
