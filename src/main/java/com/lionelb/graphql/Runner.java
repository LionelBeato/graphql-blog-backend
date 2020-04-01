package com.lionelb.graphql;

import com.lionelb.graphql.Graphql.GraphQLDataFetchers;
import com.lionelb.graphql.Model.Post;
import com.lionelb.graphql.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Runner implements CommandLineRunner {

    // this object allows me to format the Date object however I see
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    File fileOne = ResourceUtils.getFile("classpath:static/first.txt");
    File fileTwo = ResourceUtils.getFile("classpath:static/second.txt");
    File fileThree = ResourceUtils.getFile("classpath:static/third.txt");

    String contentOne = new String(Files.readAllBytes(fileOne.toPath()));
    String contentTwo = new String(Files.readAllBytes(fileTwo.toPath()));
    String contentThree = new String(Files.readAllBytes(fileThree.toPath()));



    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Autowired
    PostRepo postRepo;

    public Runner() throws IOException {
    }

    @Override
    public void run(String... args) throws Exception {



        postRepo.save(new Post(sdf.format(new Date()), "hello", contentOne));
        postRepo.save(new Post(sdf.format(new Date()), "new post", contentTwo));
        postRepo.save(new Post(sdf.format(new Date()), "other rambling", contentThree));


    }
}
