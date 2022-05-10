package com.example.shudata.kana.data

import com.example.shudata.database.EntityConverter.toAlphabetExercise
import com.example.shudata.database.EntityConverter.toHiraganaProgressEntity
import com.example.shudata.database.EntityConverter.toKatakanaProgressEntity
import com.example.shudata.database.dao.HiraganaDao
import com.example.shudata.database.dao.KatakanaDao
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import com.example.shudomain.exercise.repository.GetKanaExercisesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteGetKanaExercisesRepository @Inject constructor(
    private val hiraganaDao: HiraganaDao,
    private val katakanaDao: KatakanaDao,
) : GetKanaExercisesRepository {

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
                exerciseCharacter = "あ",
                correctAnswer = "a",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "い",
                correctAnswer = "i",
                exerciseAnswers = listOf("e", "i", "mi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "う",
                correctAnswer = "u",
                exerciseAnswers = listOf("tsu", "e", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "え",
                correctAnswer = "e",
                exerciseAnswers = listOf("e", "a", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "お",
                correctAnswer = "o",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            // Ka, Ki, Ku, Ke, Ko
            AlphabetExerciseCharacter(
                exerciseCharacter = "か",
                correctAnswer = "ka",
                exerciseAnswers = listOf("ka", "ga", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "き",
                correctAnswer = "ki",
                exerciseAnswers = listOf("ki", "gi", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "く",
                correctAnswer = "ku",
                exerciseAnswers = listOf("ku", "pu", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "け",
                correctAnswer = "ke",
                exerciseAnswers = listOf("re", "ke", "pe")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "こ",
                correctAnswer = "ko",
                exerciseAnswers = listOf("go", "pyo", "ko")
            ),
            // Sa, Shi, Su, Se, So
            AlphabetExerciseCharacter(
                exerciseCharacter = "さ",
                correctAnswer = "sa",
                exerciseAnswers = listOf("ro", "sa", "za")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "し",
                correctAnswer = "shi",
                exerciseAnswers = listOf("tsu", "ji", "shi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "す",
                correctAnswer = "su",
                exerciseAnswers = listOf("mu", "su", "zu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "せ",
                correctAnswer = "se",
                exerciseAnswers = listOf("sa", "se", "ze")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "そ",
                correctAnswer = "so",
                exerciseAnswers = listOf("so", "tsu", "shi")
            ),
            // Ta, Chi, Tsu, Te, To
            AlphabetExerciseCharacter(
                exerciseCharacter = "た",
                correctAnswer = "ta",
                exerciseAnswers = listOf("ta", "na", "da")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ち",
                correctAnswer = "chi",
                exerciseAnswers = listOf("chi", "ra", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "つ",
                correctAnswer = "tsu",
                exerciseAnswers = listOf("u", "tsu", "su")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "て",
                correctAnswer = "te",
                exerciseAnswers = listOf("se", "re", "te")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "と",
                correctAnswer = "to",
                exerciseAnswers = listOf("ko", "do", "to")
            ),
            // Na, Ni, Nu, Ne, No
            AlphabetExerciseCharacter(
                exerciseCharacter = "な",
                correctAnswer = "na",
                exerciseAnswers = listOf("ra", "na", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "に",
                correctAnswer = "ni",
                exerciseAnswers = listOf("ko", "ni", "po")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぬ",
                correctAnswer = "nu",
                exerciseAnswers = listOf("nu", "mo", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ね",
                correctAnswer = "ne",
                exerciseAnswers = listOf("ne", "nu", "ke")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "の",
                correctAnswer = "no",
                exerciseAnswers = listOf("nu", "mu", "no")
            ),
            // Ha, Hi, Fu, He, Ho
            AlphabetExerciseCharacter(
                exerciseCharacter = "は",
                correctAnswer = "ha",
                exerciseAnswers = listOf("ha", "ho", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ひ",
                correctAnswer = "hi",
                exerciseAnswers = listOf("bi", "hi", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ふ",
                correctAnswer = "fu",
                exerciseAnswers = listOf("hi", "pu", "fu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "へ",
                correctAnswer = "he",
                exerciseAnswers = listOf("he", "pe", "hi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ほ",
                correctAnswer = "ho",
                exerciseAnswers = listOf("ho", "ha", "po")
            ),
            // Ma, Mi, Mu, Me, Mo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ま",
                correctAnswer = "ma",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "み",
                correctAnswer = "mi",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "む",
                correctAnswer = "mu",
                exerciseAnswers = listOf("nu", "mu", "mo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "め",
                correctAnswer = "me",
                exerciseAnswers = listOf("no", "me", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "も",
                correctAnswer = "mo",
                exerciseAnswers = listOf("mu", "mo", "shi")
            ),
            // Ya, Yu, Yo
            AlphabetExerciseCharacter(
                exerciseCharacter = "や",
                correctAnswer = "ya",
                exerciseAnswers = listOf("ka", "yo", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ゆ",
                correctAnswer = "yu",
                exerciseAnswers = listOf("mu", "nu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "よ",
                correctAnswer = "yo",
                exerciseAnswers = listOf("no", "yo", "po")
            ),
            // Ra, Ri, Ru, Re, Ro
            AlphabetExerciseCharacter(
                exerciseCharacter = "ら",
                correctAnswer = "ra",
                exerciseAnswers = listOf("chi", "sa", "ra")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "り",
                correctAnswer = "ri",
                exerciseAnswers = listOf("ni", "i", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "る",
                correctAnswer = "ru",
                exerciseAnswers = listOf("so", "ru", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "れ",
                correctAnswer = "re",
                exerciseAnswers = listOf("re", "nu", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ろ",
                correctAnswer = "ro",
                exerciseAnswers = listOf("ro", "ru", "su")
            ),
            // Wa, Wo, N
            AlphabetExerciseCharacter(
                exerciseCharacter = "わ",
                correctAnswer = "wa",
                exerciseAnswers = listOf("wa", "ne", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "を",
                correctAnswer = "wo",
                exerciseAnswers = listOf("wo", "o", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ん",
                correctAnswer = "n",
                exerciseAnswers = listOf("su", "e", "n")
            ),
            // Ga, Gi, Gu, Ge, Go
            AlphabetExerciseCharacter(
                exerciseCharacter = "が",
                correctAnswer = "ga",
                exerciseAnswers = listOf("ga", "ka", "pa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぎ",
                correctAnswer = "gi",
                exerciseAnswers = listOf("chi", "gi", "ki")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぐ",
                correctAnswer = "gu",
                exerciseAnswers = listOf("ku", "be", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "げ",
                correctAnswer = "ge",
                exerciseAnswers = listOf("ge", "ke", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ご",
                correctAnswer = "go",
                exerciseAnswers = listOf("ko", "go", "ni")
            ),
            // Za, Ji, Zu, Ze, Zo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ざ",
                correctAnswer = "za",
                exerciseAnswers = listOf("o", "za", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "じ",
                correctAnswer = "ji",
                exerciseAnswers = listOf("chi", "ra", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ず",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "su", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぜ",
                correctAnswer = "ze",
                exerciseAnswers = listOf("ze", "sa", "se")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぞ",
                correctAnswer = "zo",
                exerciseAnswers = listOf("zo", "so", "ne")
            ),
            // Da, Di, Du, De, Do
            AlphabetExerciseCharacter(
                exerciseCharacter = "だ",
                correctAnswer = "da",
                exerciseAnswers = listOf("da", "ta", "na")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぢ",
                correctAnswer = "ji",
                exerciseAnswers = listOf("mi", "chi", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "づ",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "tsu", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "で",
                correctAnswer = "de",
                exerciseAnswers = listOf("te", "de", "so")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ど",
                correctAnswer = "do",
                exerciseAnswers = listOf("to", "do", "yo")
            ),
            // Ba, Bi, Bu, Be, Bo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ば",
                correctAnswer = "ba",
                exerciseAnswers = listOf("ha", "ba", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "び",
                correctAnswer = "bi",
                exerciseAnswers = listOf("hi", "bi", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぶ",
                correctAnswer = "bu",
                exerciseAnswers = listOf("fu", "bu", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "べ",
                correctAnswer = "be",
                exerciseAnswers = listOf("be", "he", "ku")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぼ",
                correctAnswer = "bo",
                exerciseAnswers = listOf("bo", "ba", "ho")
            ),
            // Pa, Pi, Pu, Pe, Po
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぱ",
                correctAnswer = "pa",
                exerciseAnswers = listOf("ha", "pa", "ba")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぴ",
                correctAnswer = "pi",
                exerciseAnswers = listOf("hi", "pi", "bi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぷ",
                correctAnswer = "pu",
                exerciseAnswers = listOf("pu", "fu", "bu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぺ",
                correctAnswer = "pe",
                exerciseAnswers = listOf("he", "pe", "be")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぽ",
                correctAnswer = "po",
                exerciseAnswers = listOf("po", "bo", "ho")
            ),
            // Kya, Kyu, Kyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "きゃ",
                correctAnswer = "kya",
                exerciseAnswers = listOf("ya", "kya", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "きゅ",
                correctAnswer = "kyu",
                exerciseAnswers = listOf("pyu", "kyu", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "きょ",
                correctAnswer = "kyo",
                exerciseAnswers = listOf("ki", "pya", "kyo")
            ),
            // Sha, Shu, Sho
            AlphabetExerciseCharacter(
                exerciseCharacter = "しゃ",
                correctAnswer = "sha",
                exerciseAnswers = listOf("no", "sha", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "しゅ",
                correctAnswer = "shu",
                exerciseAnswers = listOf("pya", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "しょ",
                correctAnswer = "sho",
                exerciseAnswers = listOf("yu", "ja", "sho")
            ),
            // Cha, Chu, Cho
            AlphabetExerciseCharacter(
                exerciseCharacter = "ちゃ",
                correctAnswer = "cha",
                exerciseAnswers = listOf("cha", "nyu", "gyo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ちゅ",
                correctAnswer = "chu",
                exerciseAnswers = listOf("chu", "pyu", "gyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ちょ",
                correctAnswer = "cho",
                exerciseAnswers = listOf("cho", "shu", "chi")
            ),
            // Nya, Nyu, Nyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "にゃ",
                correctAnswer = "nya",
                exerciseAnswers = listOf("yo", "nya", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "にゅ",
                correctAnswer = "nyu",
                exerciseAnswers = listOf("nyu", "shu", "sho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "にょ",
                correctAnswer = "にょ",
                exerciseAnswers = listOf("pyu", "ni", "nyo")
            ),
            // Hya, Hyu, Hyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ひゃ",
                correctAnswer = "hya",
                exerciseAnswers = listOf("bya", "hya", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ひゅ",
                correctAnswer = "hyu",
                exerciseAnswers = listOf("shu", "hyu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ひょ",
                correctAnswer = "hyo",
                exerciseAnswers = listOf("hyo", "gya", "pya")
            ),
            // Mya, Myu, Myo
            AlphabetExerciseCharacter(
                exerciseCharacter = "みゃ",
                correctAnswer = "mya",
                exerciseAnswers = listOf("mya", "mi", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "みゅ",
                correctAnswer = "myu",
                exerciseAnswers = listOf("みゅ", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "みょ",
                correctAnswer = "myo",
                exerciseAnswers = listOf("gyo", "sho", "myo")
            ),
            // Rya, Ryu, Ryo
            AlphabetExerciseCharacter(
                exerciseCharacter = "りゃ",
                correctAnswer = "rya",
                exerciseAnswers = listOf("kya", "rya", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "りゅ",
                correctAnswer = "ryu",
                exerciseAnswers = listOf("gyu", "ryu", "myu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "りょ",
                correctAnswer = "ryo",
                exerciseAnswers = listOf("ryo", "sho", "ri")
            ),
            // Gya, Gyu, Gyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぎゃ",
                correctAnswer = "gya",
                exerciseAnswers = listOf("gya", "gi", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぎゅ",
                correctAnswer = "gyu",
                exerciseAnswers = listOf("gyu", "yu", "gi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぎょ",
                correctAnswer = "gyo",
                exerciseAnswers = listOf("yo", "go", "gyo")
            ),
            // Ja, Ju, Jo
            AlphabetExerciseCharacter(
                exerciseCharacter = "じゃ",
                correctAnswer = "ja",
                exerciseAnswers = listOf("ja", "ji", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "じゅ",
                correctAnswer = "ju",
                exerciseAnswers = listOf("gyu", "ju", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "じょ",
                correctAnswer = "jo",
                exerciseAnswers = listOf("sho", "jo", "yo")
            ),
            // Bya, Byu, Byo
            AlphabetExerciseCharacter(
                exerciseCharacter = "びゃ",
                correctAnswer = "bya",
                exerciseAnswers = listOf("bya", "hya", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "びゅ",
                correctAnswer = "byu",
                exerciseAnswers = listOf("hyu", "byu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "びょ",
                correctAnswer = "byo",
                exerciseAnswers = listOf("byo", "sho", "hyo")
            ),
            // Pya, Pyu, Pyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぴゃ",
                correctAnswer = "pya",
                exerciseAnswers = listOf("bya", "ya", "hya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぴゅ",
                correctAnswer = "pyu",
                exerciseAnswers = listOf("yu", "pyu", "hyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ぴょ",
                correctAnswer = "pyo",
                exerciseAnswers = listOf("pyo", "gya", "hyo")
            ),
        )
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
                exerciseCharacter = "ア",
                correctAnswer = "a",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "イ",
                correctAnswer = "i",
                exerciseAnswers = listOf("e", "i", "mi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ウ",
                correctAnswer = "u",
                exerciseAnswers = listOf("tsu", "e", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "エ",
                correctAnswer = "e",
                exerciseAnswers = listOf("e", "a", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "オ",
                correctAnswer = "o",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            // Ka, Ki, Ku, Ke, Ko
            AlphabetExerciseCharacter(
                exerciseCharacter = "カ",
                correctAnswer = "ka",
                exerciseAnswers = listOf("ka", "ga", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "キ",
                correctAnswer = "ki",
                exerciseAnswers = listOf("ki", "gi", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ク",
                correctAnswer = "ku",
                exerciseAnswers = listOf("ku", "pu", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ク",
                correctAnswer = "ke",
                exerciseAnswers = listOf("re", "ke", "pe")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "コ",
                correctAnswer = "ko",
                exerciseAnswers = listOf("go", "pyo", "ko")
            ),
            // Sa, Shi, Su, Se, So
            AlphabetExerciseCharacter(
                exerciseCharacter = "サ",
                correctAnswer = "sa",
                exerciseAnswers = listOf("ro", "sa", "za")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "シ",
                correctAnswer = "shi",
                exerciseAnswers = listOf("tsu", "ji", "shi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ス",
                correctAnswer = "su",
                exerciseAnswers = listOf("mu", "su", "zu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "セ",
                correctAnswer = "se",
                exerciseAnswers = listOf("sa", "se", "ze")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ソ",
                correctAnswer = "so",
                exerciseAnswers = listOf("so", "tsu", "shi")
            ),
            // Ta, Chi, Tsu, Te, To
            AlphabetExerciseCharacter(
                exerciseCharacter = "タ",
                correctAnswer = "ta",
                exerciseAnswers = listOf("ta", "na", "da")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "チ",
                correctAnswer = "chi",
                exerciseAnswers = listOf("chi", "ra", "sa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ツ",
                correctAnswer = "tsu",
                exerciseAnswers = listOf("u", "tsu", "su")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "テ",
                correctAnswer = "te",
                exerciseAnswers = listOf("se", "re", "te")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ト",
                correctAnswer = "to",
                exerciseAnswers = listOf("ko", "do", "to")
            ),
            // Na, Ni, Nu, Ne, No
            AlphabetExerciseCharacter(
                exerciseCharacter = "ナ",
                correctAnswer = "na",
                exerciseAnswers = listOf("ra", "na", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ニ",
                correctAnswer = "ni",
                exerciseAnswers = listOf("ko", "ni", "po")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヌ",
                correctAnswer = "nu",
                exerciseAnswers = listOf("nu", "mo", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ネ",
                correctAnswer = "ne",
                exerciseAnswers = listOf("ne", "nu", "ke")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ノ",
                correctAnswer = "no",
                exerciseAnswers = listOf("nu", "mu", "no")
            ),
            // Ha, Hi, Fu, He, Ho
            AlphabetExerciseCharacter(
                exerciseCharacter = "ハ",
                correctAnswer = "ha",
                exerciseAnswers = listOf("ha", "ho", "ma")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヒ",
                correctAnswer = "hi",
                exerciseAnswers = listOf("bi", "hi", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "フ",
                correctAnswer = "fu",
                exerciseAnswers = listOf("hi", "pu", "fu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヘ",
                correctAnswer = "he",
                exerciseAnswers = listOf("he", "pe", "hi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ホ",
                correctAnswer = "ho",
                exerciseAnswers = listOf("ho", "ha", "po")
            ),
            // Ma, Mi, Mu, Me, Mo
            AlphabetExerciseCharacter(
                exerciseCharacter = "マ",
                correctAnswer = "ma",
                exerciseAnswers = listOf("o", "i", "a")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ミ",
                correctAnswer = "mi",
                exerciseAnswers = listOf("o", "a", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ム",
                correctAnswer = "mu",
                exerciseAnswers = listOf("nu", "mu", "mo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "メ",
                correctAnswer = "me",
                exerciseAnswers = listOf("no", "me", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "モ",
                correctAnswer = "mo",
                exerciseAnswers = listOf("mu", "mo", "shi")
            ),
            // Ya, Yu, Yo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヤ",
                correctAnswer = "ya",
                exerciseAnswers = listOf("ka", "yo", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ユ",
                correctAnswer = "yu",
                exerciseAnswers = listOf("mu", "nu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヨ",
                correctAnswer = "yo",
                exerciseAnswers = listOf("no", "yo", "po")
            ),
            // Ra, Ri, Ru, Re, Ro
            AlphabetExerciseCharacter(
                exerciseCharacter = "ラ",
                correctAnswer = "ra",
                exerciseAnswers = listOf("chi", "sa", "ra")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "リ",
                correctAnswer = "ri",
                exerciseAnswers = listOf("ni", "i", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ル",
                correctAnswer = "ru",
                exerciseAnswers = listOf("so", "ru", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "レ",
                correctAnswer = "re",
                exerciseAnswers = listOf("re", "nu", "ne")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ロ",
                correctAnswer = "ro",
                exerciseAnswers = listOf("ro", "ru", "su")
            ),
            // Wa, Wo, N
            AlphabetExerciseCharacter(
                exerciseCharacter = "ワ",
                correctAnswer = "wa",
                exerciseAnswers = listOf("wa", "ne", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヲ",
                correctAnswer = "wo",
                exerciseAnswers = listOf("wo", "o", "mu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ン",
                correctAnswer = "n",
                exerciseAnswers = listOf("su", "e", "n")
            ),
            // Ga, Gi, Gu, Ge, Go
            AlphabetExerciseCharacter(
                exerciseCharacter = "ガ",
                correctAnswer = "ga",
                exerciseAnswers = listOf("ga", "ka", "pa")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ギ",
                correctAnswer = "gi",
                exerciseAnswers = listOf("chi", "gi", "ki")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "グ",
                correctAnswer = "gu",
                exerciseAnswers = listOf("ku", "be", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ゲ",
                correctAnswer = "ge",
                exerciseAnswers = listOf("ge", "ke", "ri")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ゴ",
                correctAnswer = "go",
                exerciseAnswers = listOf("ko", "go", "ni")
            ),
            // Za, Ji, Zu, Ze, Zo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ザ",
                correctAnswer = "za",
                exerciseAnswers = listOf("o", "za", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ジ",
                correctAnswer = "ji",
                exerciseAnswers = listOf("chi", "ra", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ズ",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "su", "nu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ゼ",
                correctAnswer = "ze",
                exerciseAnswers = listOf("ze", "sa", "se")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ゾ",
                correctAnswer = "zo",
                exerciseAnswers = listOf("zo", "so", "ne")
            ),
            // Da, Di, Du, De, Do
            AlphabetExerciseCharacter(
                exerciseCharacter = "ダ",
                correctAnswer = "da",
                exerciseAnswers = listOf("da", "ta", "na")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヂ",
                correctAnswer = "ji",
                exerciseAnswers = listOf("mi", "chi", "ji")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヅ",
                correctAnswer = "zu",
                exerciseAnswers = listOf("zu", "tsu", "u")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "デ",
                correctAnswer = "de",
                exerciseAnswers = listOf("te", "de", "so")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ド",
                correctAnswer = "do",
                exerciseAnswers = listOf("to", "do", "yo")
            ),
            // Ba, Bi, Bu, Be, Bo
            AlphabetExerciseCharacter(
                exerciseCharacter = "バ",
                correctAnswer = "ba",
                exerciseAnswers = listOf("ha", "ba", "ho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ビ",
                correctAnswer = "bi",
                exerciseAnswers = listOf("hi", "bi", "ro")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ブ",
                correctAnswer = "bu",
                exerciseAnswers = listOf("fu", "bu", "re")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ベ",
                correctAnswer = "be",
                exerciseAnswers = listOf("be", "he", "ku")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ボ",
                correctAnswer = "bo",
                exerciseAnswers = listOf("bo", "ba", "ho")
            ),
            // Pa, Pi, Pu, Pe, Po
            AlphabetExerciseCharacter(
                exerciseCharacter = "パ",
                correctAnswer = "pa",
                exerciseAnswers = listOf("ha", "pa", "ba")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ピ",
                correctAnswer = "pi",
                exerciseAnswers = listOf("hi", "pi", "bi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "プ",
                correctAnswer = "pu",
                exerciseAnswers = listOf("pu", "fu", "bu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ペ",
                correctAnswer = "pe",
                exerciseAnswers = listOf("he", "pe", "be")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ポ",
                correctAnswer = "po",
                exerciseAnswers = listOf("po", "bo", "ho")
            ),
            // Kya, Kyu, Kyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "キャ",
                correctAnswer = "kya",
                exerciseAnswers = listOf("ya", "kya", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "キュ",
                correctAnswer = "kyu",
                exerciseAnswers = listOf("pyu", "kyu", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "キョ",
                correctAnswer = "kyo",
                exerciseAnswers = listOf("ki", "pya", "kyo")
            ),
            // Sha, Shu, Sho
            AlphabetExerciseCharacter(
                exerciseCharacter = "シャ",
                correctAnswer = "sha",
                exerciseAnswers = listOf("no", "sha", "gu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "シュ",
                correctAnswer = "shu",
                exerciseAnswers = listOf("pya", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ショ",
                correctAnswer = "sho",
                exerciseAnswers = listOf("yu", "ja", "sho")
            ),
            // Cha, Chu, Cho
            AlphabetExerciseCharacter(
                exerciseCharacter = "チャ",
                correctAnswer = "cha",
                exerciseAnswers = listOf("cha", "nyu", "gyo")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "チュ",
                correctAnswer = "chu",
                exerciseAnswers = listOf("chu", "pyu", "gyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "チョ",
                correctAnswer = "cho",
                exerciseAnswers = listOf("cho", "shu", "chi")
            ),
            // Nya, Nyu, Nyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ニャ",
                correctAnswer = "nya",
                exerciseAnswers = listOf("yo", "nya", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ニュ",
                correctAnswer = "nyu",
                exerciseAnswers = listOf("nyu", "shu", "sho")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ニョ",
                correctAnswer = "にょ",
                exerciseAnswers = listOf("pyu", "ni", "nyo")
            ),
            // Hya, Hyu, Hyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヒャ",
                correctAnswer = "hya",
                exerciseAnswers = listOf("bya", "hya", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヒュ",
                correctAnswer = "hyu",
                exerciseAnswers = listOf("shu", "hyu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ヒョ",
                correctAnswer = "hyo",
                exerciseAnswers = listOf("hyo", "gya", "pya")
            ),
            // Mya, Myu, Myo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ミャ",
                correctAnswer = "mya",
                exerciseAnswers = listOf("mya", "mi", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ミュ",
                correctAnswer = "myu",
                exerciseAnswers = listOf("みゅ", "yu", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ミョ",
                correctAnswer = "myo",
                exerciseAnswers = listOf("gyo", "sho", "myo")
            ),
            // Rya, Ryu, Ryo
            AlphabetExerciseCharacter(
                exerciseCharacter = "リャ",
                correctAnswer = "rya",
                exerciseAnswers = listOf("kya", "rya", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "リュ",
                correctAnswer = "ryu",
                exerciseAnswers = listOf("gyu", "ryu", "myu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "リョ",
                correctAnswer = "ryo",
                exerciseAnswers = listOf("ryo", "sho", "ri")
            ),
            // Gya, Gyu, Gyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ギャ",
                correctAnswer = "gya",
                exerciseAnswers = listOf("gya", "gi", "ya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ギュ",
                correctAnswer = "gyu",
                exerciseAnswers = listOf("gyu", "yu", "gi")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ギョ",
                correctAnswer = "gyo",
                exerciseAnswers = listOf("yo", "go", "gyo")
            ),
            // Ja, Ju, Jo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ジャ",
                correctAnswer = "ja",
                exerciseAnswers = listOf("ja", "ji", "sha")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ジュ",
                correctAnswer = "ju",
                exerciseAnswers = listOf("gyu", "ju", "shu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ジョ",
                correctAnswer = "jo",
                exerciseAnswers = listOf("sho", "jo", "yo")
            ),
            // Bya, Byu, Byo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ビャ",
                correctAnswer = "bya",
                exerciseAnswers = listOf("bya", "hya", "gya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ビュ",
                correctAnswer = "byu",
                exerciseAnswers = listOf("hyu", "byu", "yu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ビョ",
                correctAnswer = "byo",
                exerciseAnswers = listOf("byo", "sho", "hyo")
            ),
            // Pya, Pyu, Pyo
            AlphabetExerciseCharacter(
                exerciseCharacter = "ピャ",
                correctAnswer = "pya",
                exerciseAnswers = listOf("bya", "ya", "hya")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ピュ",
                correctAnswer = "pyu",
                exerciseAnswers = listOf("yu", "pyu", "hyu")
            ),
            AlphabetExerciseCharacter(
                exerciseCharacter = "ピョ",
                correctAnswer = "pyo",
                exerciseAnswers = listOf("pyo", "gya", "hyo")
            ),
        )
    }
}
