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
        <h1>Изменить</h1>
    </header>

    <main>
        <p>Здесь вы можете изменять записи.</p>

        <form:form method="POST" action="${pageContext.request.contextPath}edit/" commandName="reportEntryForm">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>

                <tr>
                    <td>Семестр:</td>
                    <td><form:select path="sem.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${semesterList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="sem" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Кафедра:</td>
                    <td><form:select path="dept.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${departmentList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="dept" cssClass="error" /></td>
                </tr>


                <tr>
                    <td>Дисциплина:</td>
                    <td><form:select path="disc.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${disciplineList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="disc" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Поток:</td>
                    <td><form:select path="fault.id">
                        <c:if test="${!processPath.contains(\"edit\")}"> <form:option value="NONE" label="--- выберите ---" /> </c:if>
                        <form:options items="${flowList}" itemValue="id" itemLabel="name" />
                    </form:select>
                    </td>
                    <td><form:errors path="fault" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>Число студ.:</td>
                    <td><form:input path="stud_count"></form:input>
                    </td>
                    <td><form:errors path="stud_count" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Лекции:</td>
                    <td><form:input path="lection_count"></form:input>
                    </td>
                    <td><form:errors path="lection_count" cssClass="error" /></td>
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