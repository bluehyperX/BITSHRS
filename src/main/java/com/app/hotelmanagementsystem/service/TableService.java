package com.app.hotelmanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.hotelmanagementsystem.entity.Tables;

public interface TableService {
	 List<Tables> getAllTables();
	    Page<Tables> getAllTablesPage(Integer pageNumber, String sortField, String sortDirection);
	    Tables addNewTable(Tables table);
	    Tables findTableById(Long TableId);
	    Tables updateTable(Tables table);
	    void deleteTableById(Long tableId);

}
