package power60.services.app.ident.services

import com.mongodb.client.result.DeleteResult
import com.power60.ident.models.Account
import com.power60.ident.models.AccountFactory
import com.power60.utilities.services.MongoService
import com.power60.utilities.services.MongoServiceImpl

class IdentMongoService {
    private val mongoService: MongoService<Account> = MongoServiceImpl(
        "ident",
        "accounts",
        "mongodb+srv://cluster0.y1ghy8y.mongodb.net/",
        27017,
        "tkaldahl",
        "R0KNG6LbX93QnO45",
        Account::class.java
    )

    // TODO: Implement connectToCollection() usage so we can use this service for orgs, accounts, etc.

//    fun getAllAccounts(): List<Accounts> {
//        val getAllQuery = MatchAllQuery()
//        return mongoService.search(getAllQuery).items
//    }

    fun save(account: Account): String? {
        return if (account.id.isNullOrBlank()) {
            val newAccount = AccountFactory().createNewAccount(
                account.firstName,
                account.lastName,
                account.email,
                account.phoneNumber
            )
            mongoService.save(newAccount)
        } else {
            mongoService.update(account.id!!, account).upsertedId.toString()
        }
    }

    fun getAccountById(accountId: String): Account? {
        return mongoService.searchById(accountId)
    }

//    fun searchAccounts(searchQuery: Query): List<Account> {
//        return mongoService.search(searchQuery)
//    }

    fun deleteAccount(accountId: String): DeleteResult {
        return mongoService.deleteById(accountId)
    }
}
