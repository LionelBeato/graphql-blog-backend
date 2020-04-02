package com.lionelb.graphql.Graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

// this component can be thought of as a controller
// it takes care of the "routing" of the graphql
// by routing, I mean that this class would take care of managing what would otherwise be RESTful endpoints
@Component
public class GraphQLProvider {

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    // this method points to your schema and wires everything up
    // so that your schema works
    // this annotation refers to when this method is executed; it'll execute once dependency injections are satisfied
    @PostConstruct
    public void init() throws Exception {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) throws Exception {
        // this looks through your schema and helps register your types
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() throws Exception {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("authors", graphQLDataFetchers.getAuthorDataFetcher()))
                .type(newTypeWiring("Query")
                .dataFetcher("posts", graphQLDataFetchers.getPostsDataFetcher()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createPost", graphQLDataFetchers.createPost()))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}