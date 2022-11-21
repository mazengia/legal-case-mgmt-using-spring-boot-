package com.enatbanksc.casemanagementsystem.case_management.settings.Expense;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import com.enatbanksc.casemanagementsystem.case_management.exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getNullPropertyNames;


@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{
    private final ExpenseRepository expenseRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    @Override
    public Expense createExpense(Expense expense, JwtAuthenticationToken token) throws IllegalAccessException {
        if(expenseRepository.existsByExpenseName(expense.getExpenseName())){
            throw new IllegalAccessException("Expense with name " + expense.getExpenseName() + " already exists!");
        }
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        expense.setMaintained_by(maintainer);
        expense.setExpenseTypeColor(Util.getRandomColor());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpense(long expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(()-> new EntityNotFoundException(Expense.class, "Expense Type with an id: " + expenseId + " was not found!"));
    }

    @Override
    public Page<Expense> getExpenses(Pageable pageable, JwtAuthenticationToken token) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Expense updateExpense(long expenseId, Expense expenseType, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getExpense(expenseId);
        var employeeId = getEmployeeID(token);
        if(!et.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(expenseType, et, getNullPropertyNames(expenseType));
        return expenseRepository.save(et);
    }

    @Override
    public void deleteExpense(long expenseId, JwtAuthenticationToken token) {
        expenseRepository.deleteById(expenseId);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
