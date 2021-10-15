package tn.sip.ams2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tn.sip.ams2.entities.Article;
@Repository

public interface ArticleRepository extends JpaRepository<Article,Long>{

}
