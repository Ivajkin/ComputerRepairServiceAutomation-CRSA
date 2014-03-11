<%--
  User: Ivaykin Timofey
  Date: 2/20/14
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body class="invisibleBackground">

<div class="panel">
    <header>
        <h1>Создать запись отчета</h1>
    </header>

    <main>
        <p>Здесь вы можете добавлять записи в отчет преподавателя кафедры за семестр.</p>

        <form:form method="POST" action="${pageContext.request.contextPath}${processPath}" commandName="reportEntryFullForm">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>

                <tr>
                    <td>Семестр:</td>
                    <td><form:select path="report.sem.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${semesterList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="report.sem" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Кафедра:</td>
                    <td><form:select path="report.dept.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${departmentList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="report.dept" cssClass="error" /></td>
                </tr>


                <tr>
                    <td>Дисциплина:</td>
                    <td><form:select path="reportEntry.disc.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${disciplineList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="reportEntry.disc" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Поток:</td>
                    <td><form:select path="reportEntry.fault.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${flowList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="reportEntry.fault" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Число студ. (план):</td>
                    <td><form:input path="reportEntry.stud_count_plan"></form:input>
                    </td>
                    <td><form:errors path="reportEntry.stud_count_plan" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Лекции (план):</td>
                    <td><form:input path="reportEntry.lection_count_plan"></form:input>
                    </td>
                    <td><form:errors path="reportEntry.lection_count_plan" cssClass="error" /></td>
                </tr>


                <tr>
                    <td>Число студ. (факт):</td>
                    <td><form:input path="reportEntry.stud_count_fact"></form:input>
                    </td>
                    <td><form:errors path="reportEntry.stud_count_fact" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Лекции (факт):</td>
                    <td><form:input path="reportEntry.lection_count_fact"></form:input>
                    </td>
                    <td><form:errors path="reportEntry.lection_count_fact" cssClass="error" /></td>
                </tr>

                <tr>
                    <td colspan="3">Добавить: <input type="submit" /></td>
                </tr>
            </table>
        </form:form>


    </main>
</div>

</body>
</html>