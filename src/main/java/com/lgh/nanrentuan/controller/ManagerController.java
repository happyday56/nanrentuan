package com.lgh.nanrentuan.controller;


import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.service.CategoryService;
import com.lgh.nanrentuan.service.resource.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lenovo on 2015/7/14.
 */
@Controller
@RequestMapping("/man")
public class ManagerController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StaticResourceService staticResourceService;

    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        return "manager/main";
    }

    @RequestMapping("/articlelist")
    public String articlelist(HttpServletRequest request) {
        return "manager/articlelist";
    }

    @RequestMapping("/articleedit")
    public String articleedit(HttpServletRequest request) {
        return "manager/articleedit";
    }

    @RequestMapping("/category/list")
    public String categoryList() {
        return "manager/categorylist";
    }


    @RequestMapping("/articlelist.do")
    @ResponseBody
    public PageListModel articlelistdo(@RequestBody AdminArticleListRequest data) {

        PageListModel result = new PageListModel();

        StringBuilder hql = new StringBuilder();
        hql.append("select a from Article a");
        if (!StringUtils.isEmpty(data.getKey())) hql.append(" where a.title like :title");

        hql.append(" order by a.id desc");

        Query query = entityManager.createQuery(hql.toString());
        if (!StringUtils.isEmpty(data.getKey())) query.setParameter("title", "%" + data.getKey() + "%");
        query.setFirstResult((data.getCurrent() - 1) * data.getLength());
        query.setMaxResults(data.getLength());
        List resultList = query.getResultList();

        List<AdminArticleModel> list = new ArrayList<>();
        resultList.forEach(x -> {
            Article article = (Article) x;
            list.add(new AdminArticleModel(article.getId(), article.getTitle()
                    , article.getKeywords()
                    , article.getDescription()
                    , article.getUploadTime()
                    , article.getViews()
                    , article.getCategory().getTitle()
                    , article.getPictureUrl()));
        });
        result.setList(list);


        PagingModel page = new PagingModel();
        page.setCurrent(data.getCurrent());
        page.setLength(data.getLength());
        page.setCount(resultList.size());
        result.setPage(page);

        return result;
    }


    @RequestMapping("/articleedit.do")
    public
    @ResponseBody
    Object articleeditdo(Long id) {

        if (id == null) {
            return categoryService.getlist();
        } else {
            AdminArticleEditRequest result = new AdminArticleEditRequest();
            Article article = articleRepository.findOne(id);
            if (article != null) {
                result.setId(article.getId());
                if (article.getCategory() != null)
                    result.setCategoryId(article.getCategory().getId());
                result.setContent(article.getContent());
                result.setSummary(article.getSummary());
                result.setDescription(article.getDescription());
                result.setKeywords(article.getKeywords());
                result.setTitle(article.getTitle());
                result.setUploadTime(article.getUploadTime());
                result.setViews(article.getViews());

                result.setPictureUrl(article.getPictureUrl());
                result.setCategories(categoryService.getlist());
            }
            return result;
        }

    }

    @RequestMapping("/articleedit.save")
    public
    @ResponseBody
    Integer articleeditsave(@RequestBody AdminArticleSaveRequest request) {

        if ("add".equals(request.getOperType())) {
            Article article = new Article();
            article.setContent(request.getContent());
            article.setSummary(request.getSummary());
            article.setDescription(request.getDescription());
            article.setKeywords(request.getKeywords());
            article.setTitle(request.getTitle());
            article.setUploadTime(request.getUploadTime());
            article.setViews(request.getViews());
            article.setCategory(categoryRepository.findOne(request.getCategoryId()));
            article.setPictureUrl(request.getPictureUrl());
            articleRepository.save(article);
        } else {
            Article article = articleRepository.findOne(request.getId());
            article.setContent(request.getContent());
            article.setSummary(request.getSummary());
            article.setDescription(request.getDescription());
            article.setKeywords(request.getKeywords());
            article.setTitle(request.getTitle());
            article.setUploadTime(request.getUploadTime());
            article.setViews(request.getViews());
            article.setCategory(categoryRepository.findOne(request.getCategoryId()));
            article.setPictureUrl(request.getPictureUrl());
            articleRepository.save(article);
        }

        return 1;
    }


    /**
     * 富文本图片上传
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadUeImage", method = RequestMethod.POST)
    @ResponseBody
    public UploadImageModel fileUploadUeImage(MultipartHttpServletRequest request) throws Exception {
        UploadImageModel result = new UploadImageModel();
        MultipartFile file = request.getFile("imgFile");

        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        String fileName = StaticResourceService.article + "/" + UUID.randomUUID().toString() + "." + fileExt;

        URI uri = staticResourceService.uploadResource(fileName, file.getInputStream());
        result.setCode(1);
        result.setError(0);
        result.setMessage("上传成功!");
        result.setUrl(uri.toString());
        return result;
    }

    private static final String[] PIC_EXT = {"BMP", "JPG", "JPEG", "PNG", "GIF"};

    @RequestMapping(value = "/UploadAticleImage")
    @ResponseBody
    public UploadImageModel UploadAticleImage(@RequestParam(value = "fileImage") MultipartFile shareImage
            , HttpServletResponse response) throws Exception {
        UploadImageModel resultModel = new UploadImageModel();

        //文件格式判断
        if (ImageIO.read(shareImage.getInputStream()) == null) {
            resultModel.setCode(0);
            resultModel.setMessage("请上传图片文件！");
            return resultModel;
        }

        if (shareImage.getSize() == 0) {
            resultModel.setCode(0);
            resultModel.setMessage("请上传图片！");
            return resultModel;
        }

        //获取图片后缀名
        String fileName = shareImage.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
        Boolean flag = false;

        for (String s : PIC_EXT) {
            if (ext.equals(s)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            resultModel.setCode(0);
            resultModel.setMessage("文件后缀名非图片后缀名");
            return resultModel;
        }

        //保存图片
        fileName = StaticResourceService.article + "/" + UUID.randomUUID().toString() + "." + ext.toLowerCase();
        URI uri = staticResourceService.uploadResource(fileName, shareImage.getInputStream());
        response.setHeader("X-frame-Options", "SAMEORIGIN");
        resultModel.setCode(1);
        resultModel.setMessage(fileName);
        resultModel.setUrl(uri.toString());
        return resultModel;
    }

}
