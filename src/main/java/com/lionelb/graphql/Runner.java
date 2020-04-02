package com.lionelb.graphql;

import com.lionelb.graphql.Graphql.GraphQLDataFetchers;
import com.lionelb.graphql.Model.Post;
import com.lionelb.graphql.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.google.common.io.CharStreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    // this object allows me to format the Date object however I see
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

   // File fileOne = ResourceUtils.getFile("classpath:first.txt");
  //  File fileTwo = ResourceUtils.getFile("classpath:second.txt");
 //   File fileThree = ResourceUtils.getFile("classpath:third.txt");

    ClassLoader cl = this.getClass().getClassLoader();
    InputStream inputStreamOne = cl.getResourceAsStream("posts/first.txt");
    InputStream inputStreamTwo = cl.getResourceAsStream("posts/second.txt");
    InputStream inputStreamThree = cl.getResourceAsStream("posts/third.txt");


    Scanner scannerOne = new Scanner(inputStreamOne);
    Scanner scannerTwo = new Scanner(inputStreamTwo);
    Scanner scannerThree = new Scanner(inputStreamThree);



    // String contentOne = new BufferedInputStream(inputStreamOne);
   // String contentTwo = String.valueOf(inputStreamTwo);
   // String contentThree = String.valueOf(inputStreamThree);


    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Autowired
    PostRepo postRepo;

    public Runner() throws IOException {
    }



    @Override
    public void run(String... args) throws Exception {
        String textOne = scannerOne.useDelimiter("\\A").next();
        String textTwo = scannerTwo.useDelimiter("\\A").next();
        String textThree = scannerThree.useDelimiter("\\A").next();




        postRepo.save(new Post(sdf.format(new Date()), "hello", textOne));
        postRepo.save(new Post(sdf.format(new Date()), "new post", textTwo));
        postRepo.save(new Post(sdf.format(new Date()), "other rambling", textThree));


        System.out.println(textOne);
        System.out.println(textTwo);


    }
}
