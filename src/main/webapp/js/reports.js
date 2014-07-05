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
        $.get('/report/cash-operations', function(data) {
            var operations = data.Records;
            var table = "";
            for(var i = 0; i < operations.length; ++i) {
                var operation = operations[i];
                table +=
                    '<tr class="jtable-data-row ' + (i%2 === 0 ? 'jtable-row-even':'') + '">' +
                        '<td>' + operation.operation_date + '</td>' +
                        '<td>-</td>' +
                        '<td>' + operation.employee.name + '</td>' +
                        '<td>' + operation.description + '</td>' +
                        '<td>' + operation.amount + '</td>' +
                        '<td>' + operation.saldo + '</td>' +
                    '</tr>';
            }
            var report =
                    '<div class="jtable-main-container">' +
                        '<table class="jtable">' +
                            '<thead>' +
                                '<tr>' +
                                    '<th class="jtable-column-header">дата и время</th>' +
                                    '<th class="jtable-column-header">наименование товара</th>' +
                                    '<th class="jtable-column-header">сотрудник</th>' +
                                    '<th class="jtable-column-header">описание</th>' +
                                    '<th class="jtable-column-header">цена</th>' +
                                    '<th class="jtable-column-header">остаток</th>' +
                                '</tr>' +
                            '</thead>' +
                         table +
                        '</table>' +
                    '</div>' +
                    '<a class="backButton">Назад</a>' +
                    '</br>' +
                    '</br>' +
                    '</br>' +
                    '</br>';
            $('#reportsModuleContainer').html(report);
            $('.backButton').click(showMenu);
        });

    }

    function showMenu() {
        var reportList =
                "<button class=\"report-button\">Зарплата мастерам</button>" +
                "<br />" +
                "<button class=\"report-button\" id=\"cashReport\">Движение денежных средств</button>" +
                "<br />" +
                "<button class=\"report-button\">Остатки на складе</button>" +
                "<br />" +
                "<button class=\"report-button\">Списание со склада</button>" +
                "<br />" +
                "<button class=\"report-button\">Принятие на склад</button>" +
                "<br />" +
                "<button class=\"report-button\">Валовая прибыль</button>";
        $('#reportsModuleContainer').html(reportList);
        $('#cashReport').click(showCashReport);
    }
    showMenu();
}