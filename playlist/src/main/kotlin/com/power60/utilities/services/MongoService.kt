package com.power60.utilities.services

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoDriverInformation
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.internal.MongoClientImpl
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.mongodb.connection.ClusterSettings
import com.mongodb.connection.SslSettings
import org.bson.conversions.Bson

interface MongoService<T> {
    var client: MongoClient
    var collection: MongoCollection<T>

    fun save(document: T): String?
    fun update(id: String, document: T): UpdateResult
    fun search(query: Map<String, Any>): List<T> // SearchResult<T> // TODO: Make the type generic so that the implementation can determine the type and get SearchResult nmodel from em2m-java-sdk
    fun searchById(id: String): T?
    fun deleteById(id: String): DeleteResult
}

class MongoServiceImpl<T : Any> constructor(
    dbName: String,
    collectionName: String,
    private val host: String = "mongodb+srv://cluster0.y1ghy8y.mongodb.net/",
    private val port: Int = 27017,
    private val username: String = "tkaldahl",
    private val password: String = "R0KNG6LbX93QnO45",
    private val documentClass: Class<T>
) : MongoService<T> {

    override var client = connectToClient()
    override var collection: MongoCollection<T> = connectToCollection(dbName, collectionName)

    fun connectToClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://$username:$password@$host:$port")

        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .applyToClusterSettings {
                ClusterSettings
                    .builder()
                    .hosts(listOf(ServerAddress(host, port)))
                    .build()
            }
            .applyToSslSettings {
                SslSettings
                    .builder()
                    .enabled(true)
                    .build()
            }
            .build()

        val driverInformation = MongoDriverInformation.builder()
            .build()


        return MongoClientImpl(settings, driverInformation)
    }

    fun connectToCollection(dbName: String, collectionName: String): MongoCollection<T> {
        println("Connecting to collection $dbName.$collectionName")
        return client
            .getDatabase(dbName)
            .getCollection(collectionName, documentClass)
    }

    private fun disconnect() {
        client.close()
        println("Disconnected from MongoDB")
    }

    override fun save(document: T): String? {
        return collection.insertOne(document).insertedId?.toString()
    }

    override fun update(id: String, document: T): UpdateResult {
        val idFilter: Bson = Filters.eq("_id", id)
        return collection
            .replaceOne(idFilter, document)
    }

    override fun search(query: Map<String, Any>): List<T> /* SearchResult<T> */{
        TODO("Not yet implemented")
    }

    override fun searchById(id: String): T? {
        val idFilter: Bson = Filters.eq("_id", id)
        return collection
            .find(idFilter)
            .firstOrNull()
    }

    override fun deleteById(id: String): DeleteResult {
        val idFilter: Bson = Filters.eq("_id", id)
        return collection
            .deleteOne(idFilter)
    }
}


