/**
 * User: Ivaykin Timofey
 * Date: 18.06.14
 */
function openCashModule() {
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
        });
    }
    function getCashByType(cash_type_id, callback) {
        assert(cash_type_id === 0 || cash_type_id === 1 || cash_type_id === 2);

        $.get("/cash", {cash_type_id: cash_type_id}, function(data) {
            current_cash_type = JSON.parse(data);
            callback(current_cash_type);
        });
    }

    function showOperationsPanel() {
        $('.cash-operation-window').fadeIn();
        $('#commit-cash-operation-button').click(commitOperation);
    }


    $('.cash-plus').click(function() {
        getCashByType(0, function(cash) {
            $('.cash-operation-name').text('Приход: ' + cash.name);
            showOperationsPanel();
        });
    });
    $('.cash-minus').click(function() {
        getCashByType(0, function(cash) {
            $('.cash-operation-name').text('Расход: ' + cash.name);
            showOperationsPanel();
        });
    });
}