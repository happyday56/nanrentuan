package com.lgh.nanrentuan.repository;

import com.lgh.nanrentuan.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

//    List<Article> findAllByIdAsc();
}
