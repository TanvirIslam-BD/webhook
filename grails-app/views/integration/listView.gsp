<%@ page import="com.autobill.webhook.App" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="page-wrapper mdc-toolbar-fixed-adjust">
    <main class="content-wrapper">
            <div class="mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                        <div class="mdc-card p-0">
                            <h6 class="card-title card-padding pb-0">Instances <span><g:link class="mdc-button mdc-button--raised w-10"  controller="integration" action="edit">Create</g:link></span> </h6>
                            <div class="table-responsive">
                            <g:if test="${integrationProperties.size() > 0}">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th class="text-left">Name</th>
                                        <th>Webcommander site url </th>
                                        <th>AutoBill instance id</th>
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${integrationProperties}" var="instance">
                                        <tr>
                                            <td class="text-left">${instance.wcInstanceName}</td>
                                            <td>${instance.wcInstancePath}</td>
                                            <td>${instance.externalInstanceId}</td>
                                            <td><g:link controller="integration" action="edit" id="${instance.id}">Edit</g:link></td>
                                            <td><g:link controller="integration" action="delete" id="${instance.id}">Delete</g:link></td>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </g:if>
                            <g:else>
                                <div>
                                    <h3 class="centered" style="text-align: center">No instance created yet</h3>
                                </div>
                            </g:else>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </main>
    <!-- partial:../../partials/_footer.html -->
    <footer></footer>
    <!-- partial -->
</div>

</body>
</html>