:- dynamic(age/1).
:- dynamic(gender/1).
:- dynamic(dob/1).
:- dynamic(employment/1).
:- dynamic(salary/1).
:- dynamic(collateralValue/1).
:- dynamic(loan_amount/1).
:- dynamic(loan_term/1).
:- dynamic(assets/1).
:- dynamic(cbil_score/1).
:- dynamic(existing_libalities/1).
:- dynamic(monthly_debt_payments/1).
:- dynamic(marital_status/1).
:- dynamic(dependents/1).
:- dynamic(monthlyTransactionAmount/1).
:- dynamic(currentAccountBalance/1).

/* Rules */
ask_age :- 
    write('What is your age? '),
    read(Age),
    assert(age(Age)).

ask_gender :- 
    write('What is your gender? '),
    read(Gender),
    assert(gender(Gender)).

ask_dob :- 
    write('What is your date of birth? '),
    read(DOB),
    assert(dob(DOB)).

ask_employment :- 
    write('What is your employment status? '),
    read(Employment),
    assert(employment(Employment)).

ask_salary :- 
    write('What is your salary? '),
    read(Salary),
    assert(salary(Salary)).

ask_collateral_value :- 
    write('What is the value of your collateral? '),
    read(CollateralValue),
    assert(collateralValue(CollateralValue)).

ask_loan_amount :- 
    write('What is the loan amount you are applying for? '),
    read(LoanAmount),
    assert(loan_amount(LoanAmount)).

ask_loan_term :- 
    write('What is the loan term in months? '),
    read(LoanTerm),
    assert(loan_term(LoanTerm)).

ask_assets :- 
    write('What is the value of your assets? '),
    read(Assets),
    assert(assets(Assets)).

ask_cbil_score :- 
    write('What is your CBIL score? '),
    read(CBIL),
    assert(cbil_score(CBIL)).

ask_existing_liabilities :- 
    write('Do you have any existing liabilities? '),
    read(ExistingLiabilities),
    assert(existing_libalities(ExistingLiabilities)).

ask_monthly_debt_payments :- 
    write('What are your monthly debt payments? '),
    read(MonthlyDebtPayments),
    assert(monthly_debt_payments(MonthlyDebtPayments)).

ask_marital_status :- 
    write('What is your marital status? '),
    read(MaritalStatus),
    assert(marital_status(MaritalStatus)).

ask_dependents :- 
    write('How many dependents do you have? '),
    read(Dependents),
    assert(dependents(Dependents)).

ask_monthly_transaction_amount :- 
    write('What is your average monthly transaction amount? '),
    read(MonthlyTransactionAmount),
    assert(monthlyTransactionAmount(MonthlyTransactionAmount)).

ask_current_account_balance :- 
    write('What is your current account balance? '),
    read(CurrentAccountBalance),
    assert(currentAccountBalance(CurrentAccountBalance)).

check_eligibility :-
    age(A),
    loan_amount(LA),
    collateralValue(CV),
    loan_term(LT),
    cbil_score(CS),
    currentAccountBalance(CB),
    existing_libalities(EL),
    assets(As),
    (AgeEnd is A + LT / 12,
    AgeEnd =< 67,
    A >= 22,
    CV >= 1.3 * LA,
    CS > 685,
    CB >= 0.3 * LA,
    EL =< As
    -> write('Congratulations! You are eligible for the loan.'),
        calculate_emi(LA, LT, EMI),
        format('Your monthly EMI will be ~2f', [EMI])
    ; write('Sorry, you are not eligible for the loan.')
    ).
calculate_emi(LoanAmount, LoanTerm, EMI) :-
    AnnualInterestRate is 10,
    MonthlyInterestRate is AnnualInterestRate / 12 / 100,
    TotalMonths is LoanTerm,
    R is 1 + MonthlyInterestRate,
    Power is R ** TotalMonths,
    EMI is LoanAmount * MonthlyInterestRate * Power / (Power - 1).

collect_user_details :- 
    ask_age,
    ask_gender,
    ask_dob,
    ask_employment,
    ask_salary,
    ask_collateral_value,
    ask_loan_amount,
    ask_loan_term,
    ask_assets,
    ask_cbil_score,
    ask_existing_liabilities,
    ask_monthly_debt_payments,
    ask_marital_status,
    ask_dependents,
    ask_monthly_transaction_amount,
    ask_current_account_balance,
    check_eligibility.