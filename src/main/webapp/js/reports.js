/**
 * User: Ivaykin Timofey
 * Date: 28.06.14
 */


function openReportsModule() {
    $('#requestsTableContainer').hide();
    $('#settingsContainer').hide();
    $('#warehouseTableContainer').hide();
    $('#cashModuleContainer').hide();

    $('#reportsModuleContainer').show();



    function showCashReport() {
        var report =
        '<table>' +
            '<tr>' +
                '<th>дата и время</th>' +
                '<th>наименование товара</th>' +
                '<th>сотрудник</th>' +
                '<th>цена</th>' +
                '<th>остаток</th>' +
            '</tr>' +
            '<tr>' +
                '<td>123</td>' +
                '<td>321</td>' +
                '<td>321</td>' +
                '<td>321</td>' +
                '<td>321</td>' +
            '</tr>' +
        '</table>' +
            '<a class="backButton">Назад</a>' +
            '</br>' +
            '</br>' +
            '</br>' +
            '</br>';
        $('#reportsModuleContainer').html(report);
        $('.backButton').click(showMenu);
    }

    function showMenu() {
        var reportList =
            "<ul>" +
                "<li>Зарплата мастерам</li>" +
                "<li id=\"cashReport\">Движение денежных средств</li>" +
                "<li>Остатки на складе</li>" +
                "<li>Списание со склада</li>" +
                "<li>Принятие на склад</li>" +
                "<li>Валовая прибыль</li>" +
            "</ul>";
        $('#reportsModuleContainer').html(reportList);
        $('#cashReport').click(showCashReport);
    }
    showMenu();
}