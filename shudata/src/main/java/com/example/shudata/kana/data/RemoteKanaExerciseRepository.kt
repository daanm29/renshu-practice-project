package com.example.shudata.kana.data

import com.example.shudata.database.converter.EntityConverter.toAlphabetExercise
import com.example.shudata.database.converter.EntityConverter.toHiraganaProgressEntity
import com.example.shudata.database.converter.EntityConverter.toKatakanaProgressEntity
import com.example.shudata.database.dao.HiraganaDao
import com.example.shudata.database.dao.KatakanaDao
import com.example.shudata.database.dao.StreakDao
import com.example.shudata.database.entity.StreakEntity
import com.example.shudata.generic.DateExtension.isDateEqual
import com.example.shudata.generic.DateExtension.isOtherDateYesterday
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import com.example.shudomain.exercise.repository.KanaExerciseRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

class RemoteKanaExerciseRepository @Inject constructor(
    private val hiraganaDao: HiraganaDao,
    private val katakanaDao: KatakanaDao,
    private val streakDao: StreakDao,
) : KanaExerciseRepository {

    override fun getAllHiraganaExercises(): Single<AlphabetExercise> {
        val hiraganaExercises = hiraganaDao.getHiraganaProgress()

        return Single.create { emitter ->
            if (hiraganaExercises.isNullOrEmpty()) {
                val alphabetExercise = AlphabetExercise(
                    currentItem = 0,
                    exercisesTodo = createHiraganaExercises(),
                    exercisesDone = arrayListOf(),
                    completed = false,
                )

                hiraganaDao.insertHiraganaProgress(alphabetExercise.toHiraganaProgressEntity())
                emitter.onSuccess(alphabetExercise)
            } else {
                emitter.onSuccess(hiraganaExercises[0].toAlphabetExercise())
            }
        }
    }

    private fun createHiraganaExercises(): ArrayList<AlphabetExerciseCharacter> {
        return arrayListOf(
            // Vowels
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "a",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "i",
                exerciseAnswers = listOf("e", "i", "mi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "u",
                exerciseAnswers = listOf("tsu", "e", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "e",
                exerciseAnswers = listOf("e", "a", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "o",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            // Ka, Ki, Ku, Ke, Ko
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ka",
                exerciseAnswers = listOf("ka", "ga", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ki",
                exerciseAnswers = listOf("ki", "gi", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ku",
                exerciseAnswers = listOf("ku", "pu", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ke",
                exerciseAnswers = listOf("re", "ke", "pe")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ko",
                exerciseAnswers = listOf("go", "pyo", "ko")
            ),
            // Sa, Shi, Su, Se, So
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "sa",
                exerciseAnswers = listOf("ro", "sa", "za")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "shi",
                exerciseAnswers = listOf("tsu", "ji", "shi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "su",
                exerciseAnswers = listOf("mu", "su", "zu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "se",
                exerciseAnswers = listOf("sa", "se", "ze")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "so",
                exerciseAnswers = listOf("so", "tsu", "shi")
            ),
            // Ta, Chi, Tsu, Te, To
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ta",
                exerciseAnswers = listOf("ta", "na", "da")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "chi",
                exerciseAnswers = listOf("chi", "ra", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "tsu",
                exerciseAnswers = listOf("u", "tsu", "su")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "te",
                exerciseAnswers = listOf("se", "re", "te")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "to",
                exerciseAnswers = listOf("ko", "do", "to")
            ),
            // Na, Ni, Nu, Ne, No
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "na",
                exerciseAnswers = listOf("ra", "na", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ni",
                exerciseAnswers = listOf("ko", "ni", "po")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "nu",
                exerciseAnswers = listOf("nu", "mo", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ne",
                exerciseAnswers = listOf("ne", "nu", "ke")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "no",
                exerciseAnswers = listOf("nu", "mu", "no")
            ),
            // Ha, Hi, Fu, He, Ho
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ha",
                exerciseAnswers = listOf("ha", "ho", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "hi",
                exerciseAnswers = listOf("bi", "hi", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "fu",
                exerciseAnswers = listOf("hi", "pu", "fu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "he",
                exerciseAnswers = listOf("he", "pe", "hi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ho",
                exerciseAnswers = listOf("ho", "ha", "po")
            ),
            // Ma, Mi, Mu, Me, Mo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ma",
                exerciseAnswers = listOf("yo", "ha", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mi",
                exerciseAnswers = listOf("na", "i", "mi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mu",
                exerciseAnswers = listOf("nu", "mu", "mo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "me",
                exerciseAnswers = listOf("no", "me", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mo",
                exerciseAnswers = listOf("mu", "mo", "shi")
            ),
            // Ya, Yu, Yo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ya",
                exerciseAnswers = listOf("ka", "yo", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "yu",
                exerciseAnswers = listOf("mu", "nu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "yo",
                exerciseAnswers = listOf("no", "yo", "po")
            ),
            // Ra, Ri, Ru, Re, Ro
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ra",
                exerciseAnswers = listOf("chi", "sa", "ra")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ri",
                exerciseAnswers = listOf("ni", "i", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ru",
                exerciseAnswers = listOf("so", "ru", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "re",
                exerciseAnswers = listOf("re", "nu", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ro",
                exerciseAnswers = listOf("ro", "ru", "su")
            ),
            // Wa, Wo, N
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "wa",
                exerciseAnswers = listOf("wa", "ne", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "wo",
                exerciseAnswers = listOf("wo", "o", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "n",
                exerciseAnswers = listOf("su", "e", "n")
            ),
            // Ga, Gi, Gu, Ge, Go
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ga",
                exerciseAnswers = listOf("ga", "ka", "pa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "gi",
                exerciseAnswers = listOf("chi", "gi", "ki")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "gu",
                exerciseAnswers = listOf("ku", "be", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ge",
                exerciseAnswers = listOf("ge", "ke", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "go",
                exerciseAnswers = listOf("ko", "go", "ni")
            ),
            // Za, Ji, Zu, Ze, Zo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "za",
                exerciseAnswers = listOf("o", "za", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ji",
                exerciseAnswers = listOf("chi", "ra", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "su", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ze",
                exerciseAnswers = listOf("ze", "sa", "se")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zo",
                exerciseAnswers = listOf("zo", "so", "ne")
            ),
            // Da, Di, Du, De, Do
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "da",
                exerciseAnswers = listOf("da", "ta", "na")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ji",
                exerciseAnswers = listOf("mi", "chi", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "tsu", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "de",
                exerciseAnswers = listOf("te", "de", "so")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "do",
                exerciseAnswers = listOf("to", "do", "yo")
            ),
            // Ba, Bi, Bu, Be, Bo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ba",
                exerciseAnswers = listOf("ha", "ba", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bi",
                exerciseAnswers = listOf("hi", "bi", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bu",
                exerciseAnswers = listOf("fu", "bu", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "be",
                exerciseAnswers = listOf("be", "he", "ku")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bo",
                exerciseAnswers = listOf("bo", "ba", "ho")
            ),
            // Pa, Pi, Pu, Pe, Po
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pa",
                exerciseAnswers = listOf("ha", "pa", "ba")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pi",
                exerciseAnswers = listOf("hi", "pi", "bi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pu",
                exerciseAnswers = listOf("pu", "fu", "bu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pe",
                exerciseAnswers = listOf("he", "pe", "be")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "po",
                exerciseAnswers = listOf("po", "bo", "ho")
            ),
            // Kya, Kyu, Kyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kya",
                exerciseAnswers = listOf("ya", "kya", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kyu",
                exerciseAnswers = listOf("pyu", "kyu", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kyo",
                exerciseAnswers = listOf("ki", "pya", "kyo")
            ),
            // Sha, Shu, Sho
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "sha",
                exerciseAnswers = listOf("no", "sha", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "shu",
                exerciseAnswers = listOf("pya", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "sho",
                exerciseAnswers = listOf("yu", "ja", "sho")
            ),
            // Cha, Chu, Cho
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "cha",
                exerciseAnswers = listOf("cha", "nyu", "gyo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "chu",
                exerciseAnswers = listOf("chu", "pyu", "gyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "cho",
                exerciseAnswers = listOf("cho", "shu", "chi")
            ),
            // Nya, Nyu, Nyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nya",
                exerciseAnswers = listOf("yo", "nya", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nyu",
                exerciseAnswers = listOf("nyu", "shu", "sho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nyo",
                exerciseAnswers = listOf("pyu", "ni", "nyo")
            ),
            // Hya, Hyu, Hyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hya",
                exerciseAnswers = listOf("bya", "hya", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hyu",
                exerciseAnswers = listOf("shu", "hyu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hyo",
                exerciseAnswers = listOf("hyo", "gya", "pya")
            ),
            // Mya, Myu, Myo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "mya",
                exerciseAnswers = listOf("mya", "mi", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "myu",
                exerciseAnswers = listOf("myu", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "myo",
                exerciseAnswers = listOf("gyo", "sho", "myo")
            ),
            // Rya, Ryu, Ryo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "rya",
                exerciseAnswers = listOf("kya", "rya", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ryu",
                exerciseAnswers = listOf("gyu", "ryu", "myu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ryo",
                exerciseAnswers = listOf("ryo", "sho", "ri")
            ),
            // Gya, Gyu, Gyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gya",
                exerciseAnswers = listOf("gya", "gi", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gyu",
                exerciseAnswers = listOf("gyu", "yu", "gi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gyo",
                exerciseAnswers = listOf("yo", "go", "gyo")
            ),
            // Ja, Ju, Jo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ja",
                exerciseAnswers = listOf("ja", "ji", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ju",
                exerciseAnswers = listOf("gyu", "ju", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "jo",
                exerciseAnswers = listOf("sho", "jo", "yo")
            ),
            // Bya, Byu, Byo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "bya",
                exerciseAnswers = listOf("bya", "hya", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "byu",
                exerciseAnswers = listOf("hyu", "byu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "byo",
                exerciseAnswers = listOf("byo", "sho", "hyo")
            ),
            // Pya, Pyu, Pyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pya",
                exerciseAnswers = listOf("pya", "ya", "hya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pyu",
                exerciseAnswers = listOf("yu", "pyu", "hyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pyo",
                exerciseAnswers = listOf("pyo", "gya", "hyo")
            ),
        )
    }

    override fun saveHiraganaExercise(alphabetExercise: AlphabetExercise): Completable {
        return Completable.create { emitter ->
            val currentStreak = streakDao.getCurrentStreak()

            if (currentStreak.isNullOrEmpty()) {
                streakDao.insertStreak(StreakEntity(
                    startDate = Date(System.currentTimeMillis()),
                    streakLength = 1,
                    currentDate = Date(System.currentTimeMillis())
                ))
            } else {
                if (!currentStreak[0].currentDate.isDateEqual(Date()) && Date().isOtherDateYesterday(currentStreak[0].currentDate)) {
                    currentStreak[0].apply {
                        currentDate = Date(System.currentTimeMillis())
                        streakLength += 1
                    }
                    streakDao.insertStreak(currentStreak[0])
                } else {
                    streakDao.insertStreak(StreakEntity(
                        startDate = Date(System.currentTimeMillis()),
                        streakLength = 1,
                        currentDate = Date(System.currentTimeMillis())
                    ))
                }
            }

            if (alphabetExercise.completed) {
                alphabetExercise.apply {
                    exercisesTodo.addAll(alphabetExercise.exercisesDone)
                    exercisesDone.clear()
                    completed = false
                    if (alphabetExercise.mastered < 5) alphabetExercise.mastered += 1
                }
            }

            hiraganaDao.insertHiraganaProgress(alphabetExercise.toHiraganaProgressEntity())
            hiraganaDao.deleteOldHiraganaExercises()
            emitter.onComplete()
        }
    }

    override fun getAllKatakanaExercise(): Single<AlphabetExercise> {
        val katakanaExercise = katakanaDao.getKatakanaProgress()

        return Single.create { emitter ->
            if (katakanaExercise.isNullOrEmpty()) {
                val alphabetExercise = AlphabetExercise(
                    currentItem = 0,
                    exercisesTodo = createKatakanaExercises(),
                    exercisesDone = arrayListOf(),
                    completed = false,
                )

                katakanaDao.insertKatakanaProgress(alphabetExercise.toKatakanaProgressEntity())
                emitter.onSuccess(alphabetExercise)
            } else {
                emitter.onSuccess(katakanaExercise[0].toAlphabetExercise())
            }
        }
    }

    private fun createKatakanaExercises(): ArrayList<AlphabetExerciseCharacter> {
        return arrayListOf(
            // Vowels
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "a",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "i",
                exerciseAnswers = listOf("e", "i", "mi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "u",
                exerciseAnswers = listOf("tsu", "e", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "e",
                exerciseAnswers = listOf("e", "a", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "o",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            // Ka, Ki, Ku, Ke, Ko
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ka",
                exerciseAnswers = listOf("ka", "ga", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ki",
                exerciseAnswers = listOf("ki", "gi", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ku",
                exerciseAnswers = listOf("ku", "pu", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ke",
                exerciseAnswers = listOf("re", "ke", "pe")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ko",
                exerciseAnswers = listOf("go", "pyo", "ko")
            ),
            // Sa, Shi, Su, Se, So
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "sa",
                exerciseAnswers = listOf("ro", "sa", "za")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "shi",
                exerciseAnswers = listOf("tsu", "ji", "shi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "su",
                exerciseAnswers = listOf("mu", "su", "zu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "se",
                exerciseAnswers = listOf("sa", "se", "ze")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "so",
                exerciseAnswers = listOf("so", "tsu", "shi")
            ),
            // Ta, Chi, Tsu, Te, To
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ta",
                exerciseAnswers = listOf("ta", "na", "da")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "chi",
                exerciseAnswers = listOf("chi", "ra", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "tsu",
                exerciseAnswers = listOf("u", "tsu", "su")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "te",
                exerciseAnswers = listOf("se", "re", "te")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "to",
                exerciseAnswers = listOf("ko", "do", "to")
            ),
            // Na, Ni, Nu, Ne, No
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "na",
                exerciseAnswers = listOf("ra", "na", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ni",
                exerciseAnswers = listOf("ko", "ni", "po")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "nu",
                exerciseAnswers = listOf("nu", "mo", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ne",
                exerciseAnswers = listOf("ne", "nu", "ke")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "no",
                exerciseAnswers = listOf("nu", "mu", "no")
            ),
            // Ha, Hi, Fu, He, Ho
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ha",
                exerciseAnswers = listOf("ha", "ho", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "hi",
                exerciseAnswers = listOf("bi", "hi", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "fu",
                exerciseAnswers = listOf("hi", "pu", "fu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "he",
                exerciseAnswers = listOf("he", "pe", "hi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ho",
                exerciseAnswers = listOf("ho", "ha", "po")
            ),
            // Ma, Mi, Mu, Me, Mo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ma",
                exerciseAnswers = listOf("ya", "a", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mi",
                exerciseAnswers = listOf("mi", "mo", "i")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mu",
                exerciseAnswers = listOf("nu", "mu", "mo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "me",
                exerciseAnswers = listOf("no", "me", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "mo",
                exerciseAnswers = listOf("mu", "mo", "shi")
            ),
            // Ya, Yu, Yo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ya",
                exerciseAnswers = listOf("ka", "yo", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "yu",
                exerciseAnswers = listOf("mu", "nu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "yo",
                exerciseAnswers = listOf("no", "yo", "po")
            ),
            // Ra, Ri, Ru, Re, Ro
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ra",
                exerciseAnswers = listOf("chi", "sa", "ra")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ri",
                exerciseAnswers = listOf("ni", "i", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ru",
                exerciseAnswers = listOf("so", "ru", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "re",
                exerciseAnswers = listOf("re", "nu", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ro",
                exerciseAnswers = listOf("ro", "ru", "su")
            ),
            // Wa, Wo, N
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "wa",
                exerciseAnswers = listOf("wa", "ne", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "wo",
                exerciseAnswers = listOf("wo", "o", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "n",
                exerciseAnswers = listOf("su", "e", "n")
            ),
            // Ga, Gi, Gu, Ge, Go
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ga",
                exerciseAnswers = listOf("ga", "ka", "pa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "gi",
                exerciseAnswers = listOf("chi", "gi", "ki")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "gu",
                exerciseAnswers = listOf("ku", "be", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ge",
                exerciseAnswers = listOf("ge", "ke", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "go",
                exerciseAnswers = listOf("ko", "go", "ni")
            ),
            // Za, Ji, Zu, Ze, Zo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "za",
                exerciseAnswers = listOf("o", "za", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ji",
                exerciseAnswers = listOf("chi", "ra", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "su", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ze",
                exerciseAnswers = listOf("ze", "sa", "se")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zo",
                exerciseAnswers = listOf("zo", "so", "ne")
            ),
            // Da, Di, Du, De, Do
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "da",
                exerciseAnswers = listOf("da", "ta", "na")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ji",
                exerciseAnswers = listOf("mi", "chi", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "tsu", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "de",
                exerciseAnswers = listOf("te", "de", "so")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "do",
                exerciseAnswers = listOf("to", "do", "yo")
            ),
            // Ba, Bi, Bu, Be, Bo
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "ba",
                exerciseAnswers = listOf("ha", "ba", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bi",
                exerciseAnswers = listOf("hi", "bi", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bu",
                exerciseAnswers = listOf("fu", "bu", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "be",
                exerciseAnswers = listOf("be", "he", "ku")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "bo",
                exerciseAnswers = listOf("bo", "ba", "ho")
            ),
            // Pa, Pi, Pu, Pe, Po
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pa",
                exerciseAnswers = listOf("ha", "pa", "ba")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pi",
                exerciseAnswers = listOf("hi", "pi", "bi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pu",
                exerciseAnswers = listOf("pu", "fu", "bu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "pe",
                exerciseAnswers = listOf("he", "pe", "be")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "???",
                correctAnswer = "po",
                exerciseAnswers = listOf("po", "bo", "ho")
            ),
            // Kya, Kyu, Kyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kya",
                exerciseAnswers = listOf("ya", "kya", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kyu",
                exerciseAnswers = listOf("pyu", "kyu", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "kyo",
                exerciseAnswers = listOf("ki", "pya", "kyo")
            ),
            // Sha, Shu, Sho
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "sha",
                exerciseAnswers = listOf("no", "sha", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "shu",
                exerciseAnswers = listOf("pya", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "sho",
                exerciseAnswers = listOf("yu", "ja", "sho")
            ),
            // Cha, Chu, Cho
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "cha",
                exerciseAnswers = listOf("cha", "nyu", "gyo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "chu",
                exerciseAnswers = listOf("chu", "pyu", "gyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "cho",
                exerciseAnswers = listOf("cho", "shu", "chi")
            ),
            // Nya, Nyu, Nyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nya",
                exerciseAnswers = listOf("yo", "nya", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nyu",
                exerciseAnswers = listOf("nyu", "shu", "sho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "nyo",
                exerciseAnswers = listOf("pyu", "ni", "nyo")
            ),
            // Hya, Hyu, Hyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hya",
                exerciseAnswers = listOf("bya", "hya", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hyu",
                exerciseAnswers = listOf("shu", "hyu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "hyo",
                exerciseAnswers = listOf("hyo", "gya", "pya")
            ),
            // Mya, Myu, Myo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "mya",
                exerciseAnswers = listOf("mya", "mi", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "myu",
                exerciseAnswers = listOf("myu", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "myo",
                exerciseAnswers = listOf("gyo", "sho", "myo")
            ),
            // Rya, Ryu, Ryo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "rya",
                exerciseAnswers = listOf("kya", "rya", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ryu",
                exerciseAnswers = listOf("gyu", "ryu", "myu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ryo",
                exerciseAnswers = listOf("ryo", "sho", "ri")
            ),
            // Gya, Gyu, Gyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gya",
                exerciseAnswers = listOf("gya", "gi", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gyu",
                exerciseAnswers = listOf("gyu", "yu", "gi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "gyo",
                exerciseAnswers = listOf("yo", "go", "gyo")
            ),
            // Ja, Ju, Jo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ja",
                exerciseAnswers = listOf("ja", "ji", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "ju",
                exerciseAnswers = listOf("gyu", "ju", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "jo",
                exerciseAnswers = listOf("sho", "jo", "yo")
            ),
            // Bya, Byu, Byo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "bya",
                exerciseAnswers = listOf("bya", "hya", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "byu",
                exerciseAnswers = listOf("hyu", "byu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "byo",
                exerciseAnswers = listOf("byo", "sho", "hyo")
            ),
            // Pya, Pyu, Pyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pya",
                exerciseAnswers = listOf("pya", "ya", "hya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pyu",
                exerciseAnswers = listOf("yu", "pyu", "hyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "??????",
                correctAnswer = "pyo",
                exerciseAnswers = listOf("pyo", "gya", "hyo")
            ),
        )
    }

    override fun saveKatakanaExercise(alphabetExercise: AlphabetExercise): Completable {
        return Completable.create { emitter ->
            val currentStreak = streakDao.getCurrentStreak()

            if (currentStreak.isNullOrEmpty()) {
                streakDao.insertStreak(StreakEntity(
                    startDate = Date(System.currentTimeMillis()),
                    streakLength = 1,
                    currentDate = Date(System.currentTimeMillis())
                ))
            } else {
                if (!currentStreak[0].currentDate.isDateEqual(Date()) && Date().isOtherDateYesterday(currentStreak[0].currentDate)) {
                    currentStreak[0].apply {
                        currentDate = Date(System.currentTimeMillis())
                        streakLength += 1
                    }
                    streakDao.insertStreak(currentStreak[0])
                } else {
                    streakDao.insertStreak(StreakEntity(
                        startDate = Date(System.currentTimeMillis()),
                        streakLength = 1,
                        currentDate = Date(System.currentTimeMillis())
                    ))
                }
            }

            if (alphabetExercise.completed) {
                alphabetExercise.apply {
                    exercisesTodo.addAll(alphabetExercise.exercisesDone)
                    exercisesDone.clear()
                    completed = false
                    if (alphabetExercise.mastered < 5) alphabetExercise.mastered += 1
                }
            }

            katakanaDao.insertKatakanaProgress(alphabetExercise.toKatakanaProgressEntity())
            katakanaDao.deleteOldHiraganaExercises()
            emitter.onComplete()
        }
    }
}
