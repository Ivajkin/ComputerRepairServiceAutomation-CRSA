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


    function commitOperation() {
        $('.cash-operation-window').hide();
    }

    $('.cash-plus, .cash-minus').click(function() {
        $('.cash-operation-window').fadeIn();
        $('#commit-cash-operation-button').click(commitOperation);
        if($(this).hasClass('cash-plus')) {
            alert('+');
        } else {
            alert('-');
        }
    });
}