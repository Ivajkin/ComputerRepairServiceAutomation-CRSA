/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */
 $(document).ready(function() {

     $('#loading-bar').hide();

     var dictionaries = {
         'disciplines': {
             label: 'Список дисциплин',
             listPath: '/discipline/list.html',
             createPath: '/discipline/create.html',
             icon: 'disciplineIcon.png'
         },
         'teachers': {
             label: 'Список преподавателей',
             listPath: '/teacher/list.html',
             createPath: '/teacher/create.html',
             icon: 'teacherIcon.png'
         },
         'flows': {
             label: 'Список потоков',
             listPath: '/flow/list.html',
             createPath: '/flow/create.html',
             icon: 'flowIcon.png'
         },
         'semesters': {
             label: 'Список семестров',
             listPath: '/semester/list.html',
             createPath: '/semester/create.html',
             icon: 'semesterIcon.png'
         },
         'departments': {
             label: 'Список кафедр',
             listPath: '/department/list.html',
             createPath: '/department/create.html',
             icon: 'departmentIcon.png'
         },
         'reports': {
             label: 'Вывести отчет',
             listPath: '/report/select.html',
             createPath: '/report/create.html',
             icon: 'reportIcon.png'
         }
     };

     function openDictionary(dictionaryID) {
         $('#loading-bar').show();

         $('#listPanel').html('');
         $.ajax(dictionaries[dictionaryID]['listPath']).success(function(data) {
             $('#listPanel').html(data);

             $('#loading-bar').hide();
         });
         $('#actionPanel').html('');
         $.ajax(dictionaries[dictionaryID]['createPath']).success(function(data) {
             $('#actionPanel').html(data);
         });
     }

     for(var dic in dictionaries) {
         $('<a class="tableButton" id="'+ dic + '" href="#">'
             + '<img class="tableIcon" src="/img/' + dictionaries[dic]['icon'] + '" />'
             + dictionaries[dic]['label'] + '</a><br/>').appendTo("nav main");
         $('#' + dic).click(function () {
             openDictionary(this.id);
         });
     }

 });