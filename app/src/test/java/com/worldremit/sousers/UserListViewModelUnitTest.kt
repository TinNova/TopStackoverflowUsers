package com.worldremit.sousers

import com.nhaarman.mockitokotlin2.whenever
import com.worldremit.sousers.repository.UsersRepository
import com.worldremit.sousers.ui.SanitisedUser
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.worldremit.sousers.sql.UserSql
import com.worldremit.sousers.ui.SqlUserState
import com.worldremit.sousers.ui.ViewStateModel
import com.worldremit.sousers.ui.userlist.UserListViewModel
import io.reactivex.Completable
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserListViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockUserRepo: UsersRepository

    private val mockSanitisedUsers: List<SanitisedUser> = listOf(
        SanitisedUser(
            userId = 1,
            userName = "joey",
            profileImage = "joey.png",
            memberSince = 1,
            location = "joeyville",
            reputation = 1,
            isFollowed = false,
            isBlocked = false
        ),
        SanitisedUser(
            userId = 2,
            userName = "robby",
            profileImage = "robby.png",
            memberSince = 1,
            location = "robbyville",
            reputation = 1,
            isFollowed = false,
            isBlocked = false
        )
    )

    private val mockUser = SanitisedUser(
        userId = 1,
        userName = "joey",
        profileImage = "joey.png",
        memberSince = 1,
        location = "joeyville",
        reputation = 1,
        isFollowed = false,
        isBlocked = false
    )

    private lateinit var target: UserListViewModel

    @Before
    @Throws(Exception::class) //what does this mean?
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = UserListViewModel(
            mockUserRepo
        )
    }

    @Test
    fun onViewLoaded_fetchUsersSuccessful_viewStateValueEquals_sanitisedUsersTrueFalseFalse() {
        // given
        whenever(mockUserRepo.fetchUsers()).thenReturn(Single.just(mockSanitisedUsers))

        // when
        target.onViewLoaded()

        // then
        assertEquals(
            ViewStateModel(
                mockSanitisedUsers,
                isDataReady = true,
                isLoading = false,
                isNetworkError = false
            ), target.viewState.value
        )
    }

    @Test
    fun onViewLoaded_fetchUsersFailed_viewStateValueEquals_FalseFalseTrue() {
        // given
        whenever(mockUserRepo.fetchUsers()).thenReturn(Single.error(Exception()))

        // when
        target.onViewLoaded()

        // then
        assertEquals(
            ViewStateModel(
                emptyList(),
                isDataReady = false,
                isLoading = false,
                isNetworkError = true
            ), target.viewState.value
        )
    }

    // This test doesn't verify anything, we should be setting the text on the button when the Completable returns successfully
    @Test
    fun saveUserFollowStatus_saveUserStatusSuccessful() {
        // given
        val userSql = UserSql(
            userId = 1,
            isFollowed = true,
            isBlocked = false
        )

        whenever(mockUserRepo.insertUser(userSql)).thenReturn(Completable.complete())

        // when
        target.saveUserFollowStatus(mockUser)

        // target
        assertEquals(
            SqlUserState(
                sanitisedUser = mockUser,
                userStateChange = UserListViewModel.UserStateChange.FOLLOW,
                isSqlError = false
            ), target.sqlUserState.value
        )
    }

    @Test
    fun saveUserFollowStatus_saveUserStatusFailure() {
        // given
        val userSql = UserSql(
            userId = 1,
            isFollowed = true,
            isBlocked = false
        )

        whenever(mockUserRepo.insertUser(userSql)).thenReturn(Completable.error(Exception()))

        // when
        target.saveUserFollowStatus(mockUser)

        // target
        assertEquals(
            SqlUserState(
                sanitisedUser = SanitisedUser(),
                userStateChange = UserListViewModel.UserStateChange.FOLLOW,
                isSqlError = true
            ), target.sqlUserState.value
        )

    }

    @Test
    fun saveUserBlockStatus_saveUserStatusSuccessful() {
        // given
        val userSql = UserSql(
            userId = 1,
            isFollowed = false,
            isBlocked = true
        )

        whenever(mockUserRepo.insertUser(userSql)).thenReturn(Completable.complete())

        // when
        target.saveUserBlockStatus(mockUser)

        // target
        assertEquals(
            SqlUserState(
                sanitisedUser = mockUser,
                userStateChange = UserListViewModel.UserStateChange.BLOCK,
                isSqlError = false
            ), target.sqlUserState.value
        )
    }

    @Test
    fun saveUserBlockStatus_saveUserStatusFailure() {
        // given
        val userSql = UserSql(
            userId = 1,
            isFollowed = false,
            isBlocked = true
        )

        whenever(mockUserRepo.insertUser(userSql)).thenReturn(Completable.error(Exception()))

        // when
        target.saveUserBlockStatus(mockUser)

        // target
        assertEquals(
            SqlUserState(
                sanitisedUser = SanitisedUser(),
                userStateChange = UserListViewModel.UserStateChange.BLOCK,
                isSqlError = true
            ), target.sqlUserState.value
        )

    }
}
