package com.lionelb.graphql.Graphql;


import com.google.common.collect.ImmutableMap;
import com.lionelb.graphql.Model.Post;
import com.lionelb.graphql.Repo.PostRepo;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// This component acts as a way to fetch and access our data
// You can call this component your service
// its taking care of all of your business logic
@Component
public class GraphQLDataFetchers {

    @Autowired
    PostRepo postRepo;



    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    private List<Post> posts;


    public DataFetcher getPostsDataFetcher(){
        return dataFetchingEnvironment -> {
            posts = StreamSupport
                    .stream(postRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            return posts;
        };
    }

    public DataFetcher getBookByIdDataFetcher() {
        // DataFetchingEnvironment is like an anonymous inner class
        // it's technically a functional interface: this is more succinct...
        // ...specifically calls the get method inside of the interface
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors 
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getPagedPostsDataFetcher(){
        return dataFetchingEnvironment -> {
            int pageNumber = dataFetchingEnvironment.getArgument("pageNumber");
            int pageSize = dataFetchingEnvironment.getArgument("pageSize");


            Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, pageSize);
            Page<Post> allPosts = postRepo.findAll(firstPageWithTwoElements);
            return allPosts;
        };
    }

    public DataFetcher createPost(){
        return dataFetchingEnvironment -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String postTitle = dataFetchingEnvironment.getArgument("title");
            String postBody = dataFetchingEnvironment.getArgument("body");
            Post newPost = new Post(sdf.format(new Date()), postTitle, postBody);
            postRepo.save(newPost);

            posts = StreamSupport
                    .stream(postRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            return newPost;
        };
    }


}


