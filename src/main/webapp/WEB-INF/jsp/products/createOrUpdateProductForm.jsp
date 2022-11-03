<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="products">
    <h2>
        <c:if test="${product['new']}">New </c:if> product
    </h2>
    <form:form modelAttribute="product" class="form-horizontal" id="add-product-form">
        <div class="form-group has-feedback"> 

            
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Price" name="price"/>
        </div>
        <div class="control-group">
                    <petclinic:selectField name="productType" label="Type " names="${types}" size="15"/>
                </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${product['new']}">
                        <button class="btn btn-default" type="submit">Add product</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update product</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
