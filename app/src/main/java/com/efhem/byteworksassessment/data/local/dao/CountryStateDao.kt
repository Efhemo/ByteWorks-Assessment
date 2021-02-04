
import androidx.lifecycle.LiveData
import androidx.room.*
import com.efhem.byteworksassessment.data.CountryState

@Dao
interface CountryStateDao {

    @Query("SELECT * FROM countrystate WHERE name = :name")
    suspend fun getCountry(name: String): CountryState

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarms(form: List<CountryState>)

    @Query("SELECT * FROM countrystate")
    fun observeCountries(): LiveData<List<CountryState>>

    @Query("DELETE FROM countrystate")
    suspend fun deleteAllCountries()
}
