package com.example.Lingyun.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "testEntityApi",
        version = "v1",
        resource = "testEntity",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Lingyun.example.com",
                ownerName = "backend.myapplication.Lingyun.example.com",
                packagePath = ""
        )
)
public class TestEntityEndpoint {

    private static final Logger logger = Logger.getLogger(TestEntityEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(TestEntity.class);
    }

    /**
     * Returns the {@link TestEntity} with the corresponding ID.
     *
     * @param name the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code TestEntity} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "testEntity/{name}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public TestEntity get(@Named("name") String name) throws NotFoundException {
        logger.info("Getting TestEntity with ID: " + name);
        TestEntity testEntity = ofy().load().type(TestEntity.class).id(name).now();
        if (testEntity == null) {
            throw new NotFoundException("Could not find TestEntity with ID: " + name);
        }
        return testEntity;
    }

    /**
     * Inserts a new {@code TestEntity}.
     */
    @ApiMethod(
            name = "insert",
            path = "testEntity",
            httpMethod = ApiMethod.HttpMethod.POST)
    public TestEntity insert(TestEntity testEntity) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that testEntity.name has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(testEntity).now();
        logger.info("Created TestEntity.");

        return ofy().load().entity(testEntity).now();
    }

    /**
     * Updates an existing {@code TestEntity}.
     *
     * @param name       the ID of the entity to be updated
     * @param testEntity the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code name} does not correspond to an existing
     *                           {@code TestEntity}
     */
    @ApiMethod(
            name = "update",
            path = "testEntity/{name}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public TestEntity update(@Named("name") String name, TestEntity testEntity) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(name);
        ofy().save().entity(testEntity).now();
        logger.info("Updated TestEntity: " + testEntity);
        return ofy().load().entity(testEntity).now();
    }

    /**
     * Deletes the specified {@code TestEntity}.
     *
     * @param name the ID of the entity to delete
     * @throws NotFoundException if the {@code name} does not correspond to an existing
     *                           {@code TestEntity}
     */
    @ApiMethod(
            name = "remove",
            path = "testEntity/{name}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("name") String name) throws NotFoundException {
        checkExists(name);
        ofy().delete().type(TestEntity.class).id(name).now();
        logger.info("Deleted TestEntity with ID: " + name);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "testEntity",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<TestEntity> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<TestEntity> query = ofy().load().type(TestEntity.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<TestEntity> queryIterator = query.iterator();
        List<TestEntity> testEntityList = new ArrayList<TestEntity>(limit);
        while (queryIterator.hasNext()) {
            testEntityList.add(queryIterator.next());
        }
        return CollectionResponse.<TestEntity>builder().setItems(testEntityList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String name) throws NotFoundException {
        try {
            ofy().load().type(TestEntity.class).id(name).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find TestEntity with ID: " + name);
        }
    }
}