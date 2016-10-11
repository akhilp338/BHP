package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.PurchaseOrder;

/**
 * @author BHP_DEV
 * Data repository for candidate entity 
 *
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, DataTablesRepository<PurchaseOrder, Long> {

	PurchaseOrder findById(Long purchaseOrderId);

	List<PurchaseOrder> findByIdIn(List<Long> purchaseOrderIds);

}
