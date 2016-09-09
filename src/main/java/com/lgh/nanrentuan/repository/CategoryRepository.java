package com.lgh.nanrentuan.repository;

import com.lgh.nanrentuan.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/9/9.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
