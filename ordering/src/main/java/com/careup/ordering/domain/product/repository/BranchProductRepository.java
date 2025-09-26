package com.careup.ordering.domain.product.repository;

import com.careup.ordering.domain.product.entity.BranchProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchProductRepository extends JpaRepository<BranchProduct,Long> {
    List<BranchProduct> findByBranchId(Long branchId);
}
