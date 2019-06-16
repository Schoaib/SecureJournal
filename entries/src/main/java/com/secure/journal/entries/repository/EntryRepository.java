package com.secure.journal.entries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.journal.entries.entity.Entry;

/**
 * The Interface EntryRepository.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {

}