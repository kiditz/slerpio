package org.slerpio.repository;

import org.slerpio.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {


	@Query("SELECT COUNT(a) FROM Article a WHERE a.schoolId.schoolId = :schoolId")
	public Long countArticleFromSchool(@Param("schoolId") Long schoolId);
}