package org.mpatapenka.issuetracker.api.repository;

import org.mpatapenka.issuetracker.api.entity.ProjectMembership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectMembershipRepository extends CrudRepository<ProjectMembership, Integer> {

    Collection<ProjectMembership> findByEmployeeId(int employeeId);
}