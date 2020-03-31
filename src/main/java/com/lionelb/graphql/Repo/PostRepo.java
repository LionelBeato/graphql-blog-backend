package com.lionelb.graphql.Repo;

import com.lionelb.graphql.Model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {
}
