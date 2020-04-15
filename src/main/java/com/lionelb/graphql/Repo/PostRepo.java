package com.lionelb.graphql.Repo;

import com.lionelb.graphql.Model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepo extends PagingAndSortingRepository<Post, Long> {
}
