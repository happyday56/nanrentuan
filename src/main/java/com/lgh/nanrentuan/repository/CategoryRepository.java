package com.lgh.nanrentuan.repository;

import com.lgh.nanrentuan.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select category from Category category where category.parent=null order by category.sort")
    List<Category> findParents();

    Category findByPath(String path);

    List<Category> findAllByParent(Category parent);

    @Query("select category from Category category  order by category.sort")
    List<Category> findAllOrderBySortAsc();
}
