package com.api.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.model.CustomerReview;
import com.api.ecommerce.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	/**
	 * Returns list of customer review by product id
	 *
	 * @param productId - Product Id
	 * @return - List<CustomerReview>
	 */
	@Query("SELECT new com.api.ecommerce.model.CustomerReview("
			+ "c.name as customerName, r.review as review,"
			+ "r.rating as rating,r.createdOn as createdOn"
			+ ") "
			+"FROM Customer c join Review r "
			+ "on c.customerId=r.customerId "
			+ "where r.productId= ?1")
    List<CustomerReview> findByProductId(Long productId);

}
