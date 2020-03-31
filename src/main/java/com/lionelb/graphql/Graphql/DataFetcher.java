package com.lionelb.graphql.Graphql;

import graphql.schema.DataFetchingEnvironment;

public interface DataFetcher <T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}
