package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense-detail")
@RequiredArgsConstructor
public class ExpenseController implements ExpenseApi {
    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;
    private final ExpenseDetailRepository expenseDetailRepository;
    private final ApplicationEventPublisher eventPublisher; 

   
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createExpenseDetail(@RequestBody @Valid List<ExpenseDetail> expense ) { 
        for(ExpenseDetail edDetail:expense){
        expenseDetailRepository.save(edDetail);
        }
        //    return (ResponseEntity<?>) expenseDetailRepository.saveAll(expense);
        return null;

    }



//    @Override
//    public List<ExpenseDetailDto> createExpenseDetail(List<ExpenseDetailDto> expenseDetailDtos, JwtAuthenticationToken token) throws IllegalAccessException {
//        return expenseDetailMapper.toDefendantPlaintiffDto(expenseDetailService.createExpense(expenseDetailMapper.toDefendantPlaintiff(expenseDetailDtos), token));
//
//    }

    @Override
    public ExpenseDto getExpenseDetail(long id) {
        return expenseMapper.toExpenseDetailDto(expenseService.getExpenseDetail(id));
    }

    @Override
    public void deleteExpenseDetail(long id) {
        expenseService.deleteExpenseDetail(id);
    }

    @Override
    public ExpenseDto updateExpenseDetail(long id, ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseMapper.toExpenseDetailDto(expenseService.updateExpenseDetail(id, expenseMapper.toExpenseDetail(expenseDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByLitigation(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.getExpensesDetailByLitigation(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.getExpensesDetailByLitigation(pageable, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PagedModel<ExpenseDto>>
    findExpenseDetailByLitigationId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.findExpenseDetailByLitigationId(pageable, id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.findExpenseDetailByLitigationId(pageable, id, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>>
    findExpenseDetailByForeClosureId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.findExpenseDetailByLitigationId(pageable, id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.findExpenseDetailByForeclosureId(pageable, id, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>>
    findExpenseDetailByExecutionId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.findExpenseDetailByLitigationId(pageable, id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.findExpenseDetailByExecutionId(pageable, id, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByExecution(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.getExpensesDetailByExecution(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.getExpensesDetailByExecution(pageable, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByForeclosure(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.getExpensesDetailByForeclosure(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.getExpensesDetailByForeclosure(pageable, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>>
    findAllByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.findAllByExecutionsAttorneyHandlingTheCase(pageable, attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.findAllByExecutionsAttorneyHandlingTheCase(pageable, attorney, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>>
    findAllByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.findAllByLitigationAttorneyHandlingTheCase(pageable, attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.findAllByLitigationAttorneyHandlingTheCase(pageable, attorney, token).map(expenseMapper::toExpenseDetailDto)), HttpStatus.OK);

    }
}
