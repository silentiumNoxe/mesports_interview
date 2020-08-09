package com.silentiumnoxe.interview.mesports.repository;

import com.silentiumnoxe.interview.mesports.model.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface TournamentRepository extends PagingAndSortingRepository<Tournament, Long> {

}
