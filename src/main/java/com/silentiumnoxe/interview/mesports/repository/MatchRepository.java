package com.silentiumnoxe.interview.mesports.repository;

import com.silentiumnoxe.interview.mesports.model.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
}
