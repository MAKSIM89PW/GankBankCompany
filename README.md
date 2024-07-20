# GankBankCompany

##Initial project setup with basic structure and DTO getters/setters

- Created basic project structure using Maven
- Added main class (BankApplication)
- Implemented models (Account, Beneficiary, Transaction)
- Added DTOs (AccountDto, TransactionDto, ErrorResponse) with getters and setters
- Created repositories (AccountRepository, BeneficiaryRepository, TransactionRepository)
- Defined services (AccountService, BeneficiaryService, TransactionService)
- Implemented controllers (AccountController, TransactionController)
- Added custom exceptions (AccountNotFoundException, InvalidPinException, InsufficientFundsException)
- Configured PostgreSQL database in application.properties

Разработайте MVC backend приложение для создания банковских счетов и переводов денег между ними.
Взаимодействие с API будет осуществляться с помощью UI.
Требования
• Счета создаются путем указания имени получателя и четырехзначного PIN-кода.
• Номер счета создается автоматически. У одного бенефициара может быть несколько счетов.
• После создания учетной записи можно вносить, снимать или переводить деньги между счетами.
• Любая операция по списанию средств со счета должна включать правильный PIN-код.
• История транзакций должна сохраняться для всех изменений баланса.
• Конкретный вызов позволит получить все счета, имя получателя и их текущий баланс. Другой вызов будет получать все транзакции для определенной учетной записи.
• Должны быть указаны соответствующие коды ошибок и они должны возвращаться, когда операции не увенчались успехом.
• Использовать базу данных PostgreSQL в качестве резервного хранилища.
• Используйте Maven\Gradle для управление зависимостями.

Работает - постман

Создание нового аккаунта:
Метод: POST
URL: http://localhost:8080/api/accounts
Body:
{
"beneficiaryName": "Jane Doe",
"pinCode": 5678
}

Получение всех аккаунтов:
Метод: GET
URL: http://localhost:8080/api/accounts

Перевод средств между счетами:
Метод: POST
URL: http://localhost:8080/api/accounts/transfer
{
"fromAccountId": 1,
"toAccountId": 2,
"amount": 50.00,
"pinCode": 1234
}

-ToDo

не работает

Внесение средств на счет:
Метод: POST
URL: http://localhost:8080/api/accounts/1/deposit

Снятие средств со счета:
Метод: POST
URL: http://localhost:8080/api/accounts/1/withdraw

Получение всех транзакций для счета:
Метод: GET
URL: http://localhost:8080/api/transactions/account/1