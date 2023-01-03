package com.app.hotelmanagementsystem.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.hotelmanagementsystem.entity.Tables;
import com.app.hotelmanagementsystem.repository.TableRepository;
import com.app.hotelmanagementsystem.service.TableService;

@Service
public class TableServiceImpl implements TableService {
	private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<Tables> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public Page<Tables> getAllTablesPage(Integer pageNumber, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return tableRepository.findAll(pageable);
    }

    @Override
    public Tables addNewTable(Tables tables) {
        return tableRepository.save(tables);
    }

    @Override
    public Tables findTableById(Long tableId) {
        return tableRepository.findById(tableId).get();
    }

    @Override
    public Tables updateTable(Tables tables) {
        return tableRepository.save(tables);
    }

    @Override
    public void deleteTableById(Long tableId) {
        tableRepository.deleteById(tableId);
    }
}
