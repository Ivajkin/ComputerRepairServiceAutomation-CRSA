/**
 * User: Ivaykin Timofey
 * Date: 18.06.14
 */
function openCashModule() {

    function updateAmounts() {
        getCashByType(0, function(cash_type) { $('.cash .cash-saldo').text(cash_type.amount + " руб."); });
        getCashByType(1, function(cash_type) { $('.credit .cash-saldo').text(cash_type.amount + " руб."); });
        getCashByType(2, function(cash_type) { $('.wire .cash-saldo').text(cash_type.amount + " руб."); });
    }
    updateAmounts();


    $('#requestsTableContainer').hide();
    $('#settingsContainer').hide();
    $('#warehouseTableContainer').hide();

    $('#cashModuleContainer').show();

    $('.cash-operation-window').hide();

    var current_cash_type;

    function commitOperation() {
        $('.cash-operation-window').hide();
        $.post("/cash/income", {cash_type_id: cash_type_id, amount: 1000}, function(result) {
            assert(result == "OK");
            updateAmounts();
        });
    }
    function getCashByType(cash_type_id, callback) {
        assert(cash_type_id === 0 || cash_type_id === 1 || cash_type_id === 2);

        $.get("/cash", {cash_type_id: cash_type_id}, function(data) {
            current_cash_type = data;//JSON.parse(data);
            callback(current_cash_type);
        });
    }

    var operation_names = {
        income: 'Приход: ',
        outcome: 'Расход: '
    };
    function showOperationsPanel(operation_name, cash_type_id) {
        getCashByType(cash_type_id, function(cash) {
            var cash_operation_name = operation_names[operation_name] + cash.name;
            $('.cash-operation-name').text(cash_operation_name);
            $('.cash-operation-window').fadeIn();
            $('#commit-cash-operation-button').click(commitOperation);
        });
    }


    $('.cash .cash-plus').click(function() { showOperationsPanel(operation_names['income'], 0); });
    $('.credit .cash-plus').click(function() { showOperationsPanel(operation_names['income'], 1); });
    $('.wire .cash-plus').click(function() { showOperationsPanel(operation_names['income'], 2); });

    $('.cash .cash-minus').click(function() { showOperationsPanel(operation_names['outcome'], 0); });
    $('.credit .cash-minus').click(function() { showOperationsPanel(operation_names['outcome'], 1); });
    $('.wire .cash-minus').click(function() { showOperationsPanel(operation_names['outcome'], 2); });

}