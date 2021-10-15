package tn.sip.ams2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.sip.ams2.entities.Article;
import tn.sip.ams2.entities.Provider;
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
	@Query("FROM Article a WHERE a.provider.id = ?1")
	List<Article> findArticlesByProvider(long id);
	
	
	@Query("FROM Provider p WHERE p.name like %?1%")
	List<Provider> ListProviderName(String nom);
	
	@Query("select p from Provider p where p.name like %:name%")
	List<Provider> findProviderByNameLike(@Param("name") String name);


}
 