<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="page-wrapper mdc-toolbar-fixed-adjust">
    <main class="content-wrapper">
        <g:if test="${settings.size() > 0}">
            <div class="mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                        <div class="mdc-card p-0">
                            <h6 class="card-title card-padding pb-0">Settings</h6>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th class="text-left">Name</th>
                                        <th>Edit</th>
                                        <th class="text-left">Value</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${settings}" var="setting">
                                        <tr>
                                            <td class="text-left">${setting.name}</td>
                                            <td><g:link controller="setting" action="edit" id="${setting.id}">Edit</g:link></td>
                                            <td class="text-left">${setting.value}</td>
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