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

    $('#reportsModuleContainer').html(
        '<table>' +
            '<tr>' +
                '<td>123</td>' +
                '<td>321</td>' +
            '</tr>' +
        '</table>');
}