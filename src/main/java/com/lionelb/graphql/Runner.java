package com.lionelb.graphql;

import com.lionelb.graphql.Graphql.GraphQLDataFetchers;
import com.lionelb.graphql.Model.Post;
import com.lionelb.graphql.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Runner implements CommandLineRunner {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Autowired
    PostRepo postRepo;

    @Override
    public void run(String... args) throws Exception {
        postRepo.save(new Post(sdf.parse("2020/03/30"), "hello", "fdffsdf"));
        postRepo.save(new Post(sdf.parse("2020/03/30"), "new post", "fdfsfdfsdfdf"));
        postRepo.save(new Post(sdf.parse("2020/03/30"), "other rambling", "343243242fsdfsdf"));

    }
}
