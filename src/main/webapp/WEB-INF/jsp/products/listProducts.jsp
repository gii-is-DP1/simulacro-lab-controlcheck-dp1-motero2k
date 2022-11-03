<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="productclinic" tagdir="/WEB-INF/tags" %>

<style>
.horizontal {display:flex;justify-content: space-between; align-items: center;}

</style>
<productclinic:layout pageName="products">
    <div class=horizontal>
	    <h2> All Products</h2>
	    <a href=/product/create2><button class="btn btn-default">Add Product</button></a>
    </div>

    <table class="table table-striped">
        <c:forEach var="product" items="${products}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${product.name}"/></dd>
                        <dt>Price</dt>
                        <dd><c:out value="${product.price}"/></dd>
        <%--                <dt>Date</dt>
                        <dd><productclinic:localDate date="${product.price}" pattern="yyyy-MM-dd"/></dd>      --%> 
                        <dt>Type</dt> 
                        <dd><c:out value="${product.productType.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        
                        
                        <tr>
                            <td>
                                <spring:url value="/product/{productId}/edit" var="productUrl">
                                    <spring:param name="productId" value="${product.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(productUrl)}">Edit product</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>
</productclinic:layout>
