package com.power60.utilities.test.services

import com.power60.utilities.services.MongoService
import com.power60.utilities.services.MongoServiceImpl
import org.testng.annotations.Test
import java.util.*

@Test
class MongoServiceTests {

	@Test
	fun mongoCrudTest() {
		val mongoService: MongoService<Widget> = MongoServiceImpl(
			"testing",
			"widgets",
			"mongodb+srv://cluster0.y1ghy8y.mongodb.net/",
			27017,
			"tkaldahl",
			"R0KNG6LbX93QnO45",
			Widget::class.java
		)

		val testWidget = Widget(UUID.randomUUID().toString(), 10, Date())

		//// Write the document and confirm a response. ////
		val savedId = mongoService.save(testWidget)
		assert(savedId == testWidget.id) { "MongoServiceImpl.save() did not save testWidget properly ${testWidget.id}" }

		//// Read the document and check that it is the document that was intended. ////
		val fetchedWidget = mongoService.searchById(testWidget.id)
		assert(fetchedWidget?.id == testWidget.id) { "MongoServiceImpl.searchById() failed to find testWidget: ${testWidget.id}" }
		assert(fetchedWidget?.count == testWidget.count) { "MongoServiceImpl.searchById() failed to find testWidget: ${testWidget.id}" }
		assert(fetchedWidget?.manufactured == testWidget.manufactured) { "Mongo searchById failed to find testWidget: ${testWidget.id}" }

		//// Modify the document and confirm a response. ////
		testWidget.count = 11
		val modifiedDocuments = mongoService.update(testWidget.id, testWidget).modifiedCount
		assert(modifiedDocuments == 1L) { "MongoServiceImpl.update() failed to update test widget: ${testWidget.id}" }

		//// Read the document and confirm it has been updated properly. ////
		val updatedWidget = mongoService.searchById(testWidget.id)
		assert(updatedWidget?.count == 11L) { "Retrieved testWidget was not updated with MongoServiceImpl.update. testWidget: ${testWidget.id}" }

		//// Delete the document and confirm a response. ////
		val deletedCount = mongoService.deleteById(testWidget.id).deletedCount
		assert(deletedCount == 1L) { "MongoServiceImpl.deleteById() failed to delete testWidget: ${testWidget.id}" }

		//// Attempt to look up the document again and fail to find. ////
		val deletedWidget = mongoService.searchById(testWidget.id)
		assert(deletedWidget == null) { "testWidget: ${testWidget.id} still exists after attempting to delete with MongoServiceImpl.deleteById()." }
	}

	data class Widget constructor(
		val id: String,
		var count: Long,
		var manufactured: Date
	)
}
