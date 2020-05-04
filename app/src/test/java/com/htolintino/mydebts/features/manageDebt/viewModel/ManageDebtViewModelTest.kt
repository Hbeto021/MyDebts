package com.htolintino.mydebts.features.manageDebt.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.features.manageDebt.dataModel.IManageDebtDataModel
import com.htolintino.mydebts.features.manageDebt.dataModel.ManageDebtDataModel
import com.htolintino.mydebts.features.manageDebt.repository.IManageDebtRepository
import com.htolintino.mydebts.mocks.DebtMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ManageDebtViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private val manageRepository = Mockito.mock(IManageDebtRepository::class.java)
    private lateinit var debtDataModel: IManageDebtDataModel
    private lateinit var viewModel: ManageDebtViewModel

    @Mock
    private lateinit var closeManageViewObserver: Observer<Void>
    @Mock
    private lateinit var errorMessageObserver: Observer<Void>
    @Mock
    private lateinit var debtObserver: Observer<Debt>

    @Before
    fun setup() {
        debtDataModel = ManageDebtDataModel()
        viewModel = ManageDebtViewModel(manageRepository, debtDataModel)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun addDebts() {
        //Act
        viewModel.addDebt(DebtMock.description, DebtMock.value, DebtMock.month, DebtMock.dueDate)
        viewModel.closeManageView().observeForever(closeManageViewObserver)

        //Assert
        runBlocking {
            Mockito.verify(manageRepository).insertDebt(
                DebtMock.description, DebtMock.value,
                DebtMock.month, DebtMock.year, DebtMock.dueDate)
        }
        Mockito.verify(closeManageViewObserver).onChanged(null)
    }

    @Test
    fun addInvalidDebts() {
        //Act
        viewModel.addDebt("", "", "", "")
        viewModel.observeErrorMessage().observeForever(errorMessageObserver)

        //Assert
        Mockito.verify(errorMessageObserver).onChanged(null)
    }

    @Test
    fun editDebts() {
        //Arrange
        val debt = DebtMock.debt

        //Act
        viewModel.setDebt(debt)
        viewModel.observeDebt().observeForever(debtObserver)
        viewModel.editDebt(DebtMock.description, DebtMock.value, DebtMock.month, DebtMock.dueDate)
        viewModel.closeManageView().observeForever(closeManageViewObserver)

        //Assert
        runBlocking {
            Mockito.verify(manageRepository).updateDebt(debt)
        }
        Mockito.verify(closeManageViewObserver).onChanged(null)
    }

    @Test
    fun editInvalidDebts() {
        //Act
        viewModel.editDebt("", "", "", "")
        viewModel.observeErrorMessage().observeForever(errorMessageObserver)

        //Assert
        Mockito.verify(errorMessageObserver).onChanged(null)
    }

}