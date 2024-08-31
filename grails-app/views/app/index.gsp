<%@ page import="com.autobill.webhook.App" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="page-wrapper mdc-toolbar-fixed-adjust">
    <main class="content-wrapper">
        <g:if test="${apps.size() > 0}">
            <div class="mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                        <div class="mdc-card p-0">
                            <h6 class="card-title card-padding pb-0">Clients</h6>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th class="text-left">Name</th>
                                        <th>Client Id</th>
                                        <th>Client Secret</th>
                                        <th>Access Token</th>
                                        <th>Refresh Token</th>
                                        <th>Token Refresh</th>
                                        <th>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${apps}" var="app">
                                        <tr>
                                            <td class="text-left">${app.name}</td>
                                            <td>${app.clientId}</td>
                                            <td>${app.clientSecret}</td>
                                            <td>${app.accessToken}</td>
                                            <td>${app.refreshToken}</td>
                                            <td><g:link controller="app" action="refreshAccessToken" id="${app.id}">Refresh</g:link></td>
                                            <td>
                                                <g:if test="${!app.name.equals(App.PROVISIONING_APP_NAME)}">
                                                    <g:link controller="app" action="delete" id="${app.id}">Delete</g:link>
                                                </g:if>
                                            </td>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </g:if>
    </main>
    <!-- partial:../../partials/_footer.html -->
    <footer></footer>
    <!-- partial -->
</div>

</body>
</html>