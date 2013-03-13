<%@ page isELIgnored ="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>MyDLP UI AAA - LOGIN</title>
    <style>
      .errorblock {
        color: #ff0000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
      }
    </style>
  </head>
 
  <body onload='document.f.j_username.focus();'>
    <center>
      <a href="http://www.mydlp.com" target="_blank">
        <img src="static/images/logo.png" />
      </a>

      <h3>Login with Username and Password</h3>


        <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
 
        <table>
          <c:if test="${not empty param.authfailed}">
            <tr>
              <td colspan='2'>
                <div class="errorblock">
			    Your login attempt was not successful, try again.<br /> Caused :
			    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
              </td>
            </tr>
          </c:if>
        
          <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value='<c:if test="${not empty param.authfailed}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' /></td>
          </tr>
          <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password' /></td>
          </tr>
          <tr>
          	<td></td>
            <td><input type="submit" value="Login" /></td>
          </tr>
          <tr>
            <td></td>
            <td><input name="reset" type="reset" /></td>
          </tr>
        </table>
      </form>
      <br />
      <div>
     	<strong>Latest MyDLP Endpoint Agents:</strong>
     	<br />
       	<a href="/download?key=latest-windows-agent" target="_blank">MyDLP Endpoint Agent for Microsoft Windows</a>
      </div>
    <center>
  </body>
</html>