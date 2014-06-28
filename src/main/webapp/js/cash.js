/**
 * User: Ivaykin Timofey
 * Date: 18.06.14
 */
function openCashModule() {

    function updateAmounts() {
        getCashByType(1, function(cash_type) {
            $('.cash .cash-saldo').text(cash_type.saldo + " руб.");
        });
        getCashByType(2, function(cash_type) {
            $('.credit .cash-saldo').text(cash_type.saldo + " руб.");
        });
        getCashByType(3, function(cash_type) {
            $('.wire .cash-saldo').text(cash_type.saldo + " руб.");
        });
    }
    updateAmounts();


    $('#requestsTableContainer').hide();
    $('#settingsContainer').hide();
    $('#warehouseTableContainer').hide();
    $('#reportsModuleContainer').hide();

    $('#cashModuleContainer').show();

    $('.cash-operation-window').hide();

    var current_cash_type;

    function commitOperation(operation_type, cash_type_id, amount) {

        $('.cash-operation-window').hide();
        $.post("/cash/" + operation_type, {cash_type_id: cash_type_id, amount: amount}, function(result) {
            assert(result === "OK", result);
            if(result === "OK") {
                assert(operation_names[operation_type], "Название типа операции не определено");
                assert(current_cash_type, "Данные о последнем виде платежа не сохранены");
                assert(current_cash_type, "Данные о последнем виде платежа не сохранены");
                messagebox.success(operation_names[operation_type] + current_cash_type.name, "Операция проведена успешно");
            }
            updateAmounts();
        });
    }
    function getCashByType(cash_type_id, callback) {
        assert(cash_type_id === 1 || cash_type_id === 2 || cash_type_id === 3, "Выбран несуществующий тип кассы под номером " + cash_type_id);

        $.get("/cash", {cash_type_id: cash_type_id}, function(data) {
            current_cash_type = data;//JSON.parse(data);
            assert(current_cash_type.saldo >= 0 && current_cash_type.name && current_cash_type.id, "Объект кассы неверный при передаче данных от сервера: " + JSON.stringify(current_cash_type));
            callback(current_cash_type);
        });
    }

    var operation_names = {
        income: 'Приход: ',
        outcome: 'Расход: '
    };
    function showOperationsPanel(operation_type, cash_type_id) {
        getCashByType(cash_type_id, function(cash) {
            var cash_operation_name = operation_names[operation_type] + cash.name.toLowerCase();
            $('.cash-operation-name').text(cash_operation_name);
            $('.cash-operation-window').fadeIn();
            $('#commit-cash-operation-button').click(function() {
                var amount = $('#cash-operation-amount').val();
                assert(amount && amount >= 0 && amount <= 9999999, "Сумма должна быть целым числом: " + amount);
                commitOperation(operation_type, cash.id, amount);
            });
        });
    }


    $('.cash .cash-plus').click(function() { showOperationsPanel('income', 1); });
    $('.credit .cash-plus').click(function() { showOperationsPanel('income', 2); });
    $('.wire .cash-plus').click(function() { showOperationsPanel('income', 3); });

    $('.cash .cash-minus').click(function() { showOperationsPanel('outcome', 1); });
    $('.credit .cash-minus').click(function() { showOperationsPanel('outcome', 2); });
    $('.wire .cash-minus').click(function() { showOperationsPanel('outcome', 3); });

}