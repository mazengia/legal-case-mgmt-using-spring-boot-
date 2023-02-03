package com.enatbanksc.casemanagementsystem.case_management.Expense;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

public interface ExpenseApi {
    //    @PostMapping()
    //    @ResponseStatus(HttpStatus.CREATED)
    //    public ResponseEntity<?> createExpenseDetail(@RequestBody @Valid List<ExpenseDetail> expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException{
    //        return (ResponseEntity<?> )expenseDetailRepository.saveAll(expenseDetail);
    //    }
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    List<ExpenseDetailDto> createExpenseDetail(@RequestBody @Valid List<ExpenseDetailDto> expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExpenseDto getExpenseDetail(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteExpenseDetail(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExpenseDto updateExpenseDetail(@PathVariable("id") long id, @RequestBody @Valid ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/litigation")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByLitigation(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                                  @Valid Pageable pageable,
                                                                            PagedResourcesAssembler assembler,
                                                                            JwtAuthenticationToken token,
                                                                            UriComponentsBuilder uriBuilder,
                                                                            final HttpServletResponse response);

    @GetMapping("/litigation-id/{id}")
    ResponseEntity<PagedModel<ExpenseDto>> findExpenseDetailByLitigationId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                           @PathVariable("id") long id,
                                                                           PagedResourcesAssembler assembler,
                                                                           JwtAuthenticationToken token,
                                                                           UriComponentsBuilder uriBuilder,
                                                                           HttpServletResponse response);

    @GetMapping("/foreclosure-id/{id}")
    ResponseEntity<PagedModel<ExpenseDto>> findExpenseDetailByForeClosureId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                            @PathVariable("id") long id,
                                                                            PagedResourcesAssembler assembler,
                                                                            JwtAuthenticationToken token,
                                                                            UriComponentsBuilder uriBuilder,
                                                                            HttpServletResponse response);


    @GetMapping("/execution-id/{id}")
    ResponseEntity<PagedModel<ExpenseDto>> findExpenseDetailByExecutionId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                          @PathVariable("id") long id,
                                                                          PagedResourcesAssembler assembler,
                                                                          JwtAuthenticationToken token,
                                                                          UriComponentsBuilder uriBuilder,
                                                                          HttpServletResponse response);


    @GetMapping("/foreclosure")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByForeclosure(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                                   @Valid Pageable pageable,
                                                                             PagedResourcesAssembler assembler,
                                                                             JwtAuthenticationToken token,
                                                                             UriComponentsBuilder uriBuilder,
                                                                             final HttpServletResponse response);

    @GetMapping("/execution")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ExpenseDto>> getALLExpensesDetailByExecution(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                                 @Valid Pageable pageable,
                                                                           PagedResourcesAssembler assembler,
                                                                           JwtAuthenticationToken token,
                                                                           UriComponentsBuilder uriBuilder,
                                                                           final HttpServletResponse response);
    @GetMapping("/execution-attorney/{attorney}")
    ResponseEntity<PagedModel<ExpenseDto>>
    findAllByExecutionsAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                               @PathVariable("attorney")   String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
    @GetMapping("/litigation-attorney/{attorney}")
    ResponseEntity<PagedModel<ExpenseDto>>
    findAllByLitigationAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                               @PathVariable("attorney")   String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
}
